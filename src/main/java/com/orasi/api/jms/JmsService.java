package com.orasi.api.jms;

import java.util.Queue;

import org.apache.commons.lang3.time.StopWatch;
import org.w3c.dom.Document;

import com.orasi.api.soapServices.SoapService;
import com.orasi.utils.TestReporter;
import com.orasi.utils.XMLTools;

@SuppressWarnings("unused")
public class JmsService extends SoapService {
    private String server = "";
    private String environment = "";
    private String location = "";
    private String operation = "";
    private AbstractQueue queueInfo = null;
    private JMSException parentException = null;
    private String strOperationName;
    private String strService;
    private String strServiceURL;
    private String executionTime;

    public JmsService(AbstractQueue queueInfo) {
        this.queueInfo = queueInfo;
        this.environment = queueInfo.getEnvironment();
    }

    @Override
    public void sendRequest() {
        TestReporter.logDebug("Entering JmsService#sendRequest");
        QueueSender queueSender = null;
        QueueReceiver queueReceiver = null;
        QueueSession queueSession = null;
        QueueConnection queueConnection = null;
        try {
            /* MQ Configuration */
            TestReporter.logDebug("Building Queue Connection with following information");
            TestReporter.logDebug("Configuration Name: " + queueInfo.getConfigName());
            TestReporter.logDebug("Environment: " + queueInfo.getEnvironment());
            TestReporter.logDebug("Host name: " + queueInfo.getHostName());
            TestReporter.logDebug("Port: " + queueInfo.getPort());
            TestReporter.logDebug("Channel: " + queueInfo.getChannel());
            TestReporter.logDebug("Queue Manager: " + queueInfo.getQueueManager());
            TestReporter.logDebug("Transport Type: " + queueInfo.getTransportType());
            TestReporter.logDebug("Request Name: " + queueInfo.getRequest());
            TestReporter.logDebug("Response Name: " + queueInfo.getResponse());
            TestReporter.logDebug("Request to Send: " + getRequest());

            strService = "MQ";
            // strOperationName = " "+queueInfo.getConfigName() + ": " + strOperationName;
            strServiceURL = queueInfo.getHostName() + ":" + queueInfo.getPort() + "(" + queueInfo.getRequest() + " | " + queueInfo.getResponse() + ")";

            MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
            mqQueueConnectionFactory.setHostName(queueInfo.getHostName());
            mqQueueConnectionFactory.setChannel(queueInfo.getChannel());// communications link
            mqQueueConnectionFactory.setPort(queueInfo.getPort());
            mqQueueConnectionFactory.setQueueManager(queueInfo.getQueueManager());// service provider
            mqQueueConnectionFactory.setTransportType(queueInfo.getTransportType());

            /* Create Connection */
            TestReporter.logDebug("Attempt to create Queue Connection");
            queueConnection = mqQueueConnectionFactory.createQueueConnection();
            TestReporter.logDebug("Successfully created Queue Connection");
            TestReporter.logDebug("Attempt to start Queue Connection");
            queueConnection.start();
            TestReporter.logDebug("Successfully started Queue Connection");

            /* Create session */
            TestReporter.logDebug("Attempt to create Queue Session");
            queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            TestReporter.logDebug("Successfully created Queue Session");

            /* Create response queue */
            TestReporter.logDebug("Attempt to create Queue");
            Queue queue = queueSession.createQueue(queueInfo.getResponse());
            TestReporter.logDebug("Successfully created Queue");

            /* Create text message */
            TestReporter.logDebug("Attempt to create Queue Message");
            TextMessage textMessage = queueSession.createTextMessage(getRequest());
            textMessage.setJMSReplyTo(queue);
            textMessage.setJMSType("mcd://xmlns");// message type
            textMessage.setJMSExpiration(2 * 1000);// message expiration
            textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT); // message delivery mode either persistent or non-persistemnt
            TestReporter.logDebug("Successfully created Queue Message");

            /* Create sender queue */
            TestReporter.logDebug("Attempt to create Queue Sender");
            queueSender = queueSession.createSender(queueSession.createQueue(queueInfo.getRequest()));
            queueSender.setTimeToLive(2 * 1000);
            TestReporter.logDebug("Successfully created Queue Sender");

            TestReporter.logDebug("Attempt to send Queue Message");
            StopWatch execution = new StopWatch();
            execution.start();
            queueSender.send(textMessage);
            execution.stop();
            executionTime = execution.toString();
            TestReporter.logDebug("Received Response from Queue after [ " + execution + " ]");
            TestReporter.logDebug("Successfully sent Queue Message");

            /* After sending a message we get message id */
            TestReporter.logDebug("Generate Correlation ID from Queue Message that will find the Response message");
            String jmsCorrelationID = " JMSCorrelationID = '" + textMessage.getJMSMessageID() + "'";
            TestReporter.logDebug("Correlation ID is " + jmsCorrelationID);

            /* Within the session we have to create queue receiver */
            TestReporter.logDebug("Attempt to create Queue Reciever");
            queueReceiver = queueSession.createReceiver(queue, jmsCorrelationID);
            TestReporter.logDebug("Successfully created Queue Reciever");

            /* Receive the message from */
            TestReporter.logDebug("Wait for response to return from Queue for 120 seconds");
            Message message = queueReceiver.receive(120 * 1000);
            if (message == null) {
                TestReporter.logDebug("No response from Queue after 120 seconds");
                TestReporter.logFailure("No response from Queue after 120 seconds");
                throw new JmsAutomationException("No response from queue after 120 seconds");
            }
            TestReporter.logDebug("Response returned from Queue");
            TestReporter.logDebug("Attempt to transform Queue Message to String");
            String responseMsg = ((TextMessage) message).getText();
            TestReporter.logDebug("Successfully transformed message");
            TestReporter.logDebug("Response Returned: " + responseMsg);

            // Covert Soap Response to XML and set it as Response in memory
            TestReporter.logDebug("Attempt to transform response to XML Document");
            Document doc = XMLTools.makeXMLDocument(responseMsg);
            doc.normalize();
            TestReporter.logDebug("Successfully transformed response to XML Document");
            TestReporter.logDebug("Storing XML Document");
            setResponseDocument(doc);
        } catch (JMSException e) {
            parentException = e;
            throw new JmsAutomationException("General Failure", e);
        } finally {
            try {
                TestReporter.logDebug("Attempting to close Queue Sender");
                queueSender.close();
                TestReporter.logDebug("Successfully closed Queue Sender");

                TestReporter.logDebug("Attempting to close Queue Receiver");
                queueReceiver.close();
                TestReporter.logDebug("Successfully closed Queue Receiver");

                TestReporter.logDebug("Attempting to close Queue Session");
                queueSession.close();
                TestReporter.logDebug("Successfully closed Queue Session");

                TestReporter.logDebug("Attempting to close Queue Connection");
                queueConnection.close();
                TestReporter.logDebug("Successfully closed Queue Connection");

            } catch (JMSException e1) {
                throw new JmsAutomationException("Failed to close Queue", parentException);
            } catch (Exception e2) {
                throw new JmsAutomationException(parentException.getMessage(), parentException);
            }

            TestReporter.logDebug("Exiting JmsService#sendRequest");
        }
    }

    /**
     * @summary Return the URL of the service under test
     * @precondition The Service URL needs to be set by
     *               {@link #setServiceURL(String)}
     * @author Justin Phlegar
     * @version Created: 08/28/2014
     * @return Returns the Service URL as a String
     */
    @Override
    public String getServiceURL() {
        return strServiceURL;
    }

    @Override
    public String getServiceName() {
        return strService;
    }

    @Override
    public String getOperationName() {
        return strOperationName;
    }

    @Override
    public String getExecutionTime() {
        return executionTime;
    }
}
package com.chameleon.api.soapServices;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.chameleon.api.APIBaseTest;
import com.chameleon.api.soapServices.SoapServiceCommands;
import com.chameleon.api.soapServices.exceptions.HeaderNotFoundException;
import com.chameleon.api.soapServices.exceptions.MissingFunctionParameterValueException;
import com.chameleon.api.soapServices.exceptions.SoapException;
import com.chameleon.api.soapServices.helpers.GetActorsById;
import com.chameleon.api.soapServices.helpers.SoapTraining;
import com.chameleon.utils.exception.XPathInvalidExpression;
import com.chameleon.utils.exception.XPathNotFoundException;
import com.chameleon.utils.exception.XPathNullNodeValueException;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class TestSoapService extends APIBaseTest {
    @Features("API")
    @Stories("SoapServices")
    @Title("createService")
    @Test
    public void createService() {
        SoapTraining usZip = new SoapTraining();
        Assert.assertNotNull(usZip);
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("createOperation")
    @Test(dependsOnMethods = "createService")
    public void createOperation() {
        GetActorsById getInfo = new GetActorsById();
        Assert.assertNotNull(getInfo);
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("createOperation_WithCSVData")
    @Test(dependsOnMethods = "createOperation")
    public void createOperation_WithCSVData() {
        GetActorsById getInfo = new GetActorsById("Main", "CSV");
        Assert.assertTrue(getInfo.getRequestActorId().equals("1"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("createOperation_WithXLSData")
    @Test(dependsOnMethods = "createOperation")
    public void createOperation_WithXLSData() {
        GetActorsById getInfo = new GetActorsById("Main", "XLS");
        Assert.assertTrue(getInfo.getRequestActorId().equals("1"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("createOperation_WithXLSXData")
    @Test(dependsOnMethods = "createOperation")
    public void createOperation_WithXLSXData() {
        GetActorsById getInfo = new GetActorsById("Main", "XLSX");
        Assert.assertTrue(getInfo.getRequestActorId().equals("1"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("addRequestHeader")
    @Test(dependsOnMethods = "createOperation")
    public void addRequestHeader() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.addRequestHeader("blah", "blah");
        Assert.assertNotNull(getInfo);
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("getNumberOfRequestNodesByXPath")
    @Test(dependsOnMethods = "createOperation")
    public void getNumberOfRequestNodesByXPath() {
        GetActorsById getInfo = new GetActorsById();
        Assert.assertTrue(getInfo.getNumberOfRequestNodesByXPath("/Envelope/Body/getActorsByIdRequest/actor_id") == 1);
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setSoapVersion")
    @Test(dependsOnMethods = "createOperation")
    public void setSoapVersion() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setSoapVersion();
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("getServiceURL")
    @Test(dependsOnMethods = "createOperation")
    public void getServiceURL() {
        GetActorsById getInfo = new GetActorsById();
        Assert.assertTrue(getInfo.getServiceURL().equals("https://training-server.herokuapp.com:443/soap"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("getServiceName")
    @Test(dependsOnMethods = "createOperation")
    public void getServiceName() {
        GetActorsById getInfo = new GetActorsById();
        Assert.assertTrue(getInfo.getServiceName().equals("SoapTraining"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("getOperationName")
    @Test(dependsOnMethods = "createOperation")
    public void getOperationName() {
        GetActorsById getInfo = new GetActorsById();
        Assert.assertTrue(getInfo.getOperationName().equals("getActorsById"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("getRequest")
    @Test(dependsOnMethods = "createOperation")
    public void getRequest() {
        GetActorsById getInfo = new GetActorsById();
        Assert.assertTrue(!getInfo.getRequest().isEmpty());
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_AddAttribute")
    @Test(dependsOnMethods = "createOperation")
    public void setRequestNodeValueByXPath_HandleValueFunctions_AddAttribute() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:addattribute;blah");
        Assert.assertTrue(getInfo.getRequest().contains("<my:actor_id blah=\"\""));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_AddAttribute_MissingParameter")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = MissingFunctionParameterValueException.class)
    public void setRequestNodeValueByXPath_HandleValueFunctions_AddAttribute_MissingParameter() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:addattribute");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_AddNode")
    @Test(dependsOnMethods = "createOperation")
    public void setRequestNodeValueByXPath_HandleValueFunctions_AddNode() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest", "fx:addnode;blah");
        Assert.assertTrue(getInfo.getRequest().contains("<blah/>"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_AddNode_MissingParameter")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = MissingFunctionParameterValueException.class)
    public void setRequestNodeValueByXPath_HandleValueFunctions_AddNode_MissingParameter() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:addnode");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_AddNodes")
    @Test(dependsOnMethods = "createOperation")
    public void setRequestNodeValueByXPath_HandleValueFunctions_AddNodes() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest", "fx:addnodes;blah/blah2/blah3");
        Assert.assertTrue(getInfo.getRequest().replace(System.getProperty("line.separator"), "").replaceAll(" ", "").contains("<blah><blah2><blah3/></blah2></blah>"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_AddNodes_MissingParameter")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = MissingFunctionParameterValueException.class)
    public void setRequestNodeValueByXPath_HandleValueFunctions_AddNodes_MissingParameter() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:addnodes");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_AddNamespace")
    @Test(dependsOnMethods = "createOperation")
    public void setRequestNodeValueByXPath_HandleValueFunctions_AddNamespace() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope", "fx:addnamespace;xmlns:web2,http://www.webserviceXnew.NET");
        Assert.assertTrue(getInfo.getRequest().contains("xmlns:web2=\"http://www.webserviceXnew.NET\""));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_AddNamespace_MissingParameter")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = MissingFunctionParameterValueException.class)
    public void setRequestNodeValueByXPath_HandleValueFunctions_AddNamespace_MissingParameter() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:addnamespace");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_AddNamespace_MissingURLParameter")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = MissingFunctionParameterValueException.class)
    public void setRequestNodeValueByXPath_HandleValueFunctions_AddNamespace_MissingURLParameter() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:addnamespace;xmlns:web2");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_GetDateTime")
    @Test(dependsOnMethods = "createOperation")
    public void setRequestNodeValueByXPath_HandleValueFunctions_GetDateTime() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:getdatetime;0");
        Assert.assertTrue(getInfo.getRequestActorId().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_GetDateTime_MissingParameter")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = MissingFunctionParameterValueException.class)
    public void setRequestNodeValueByXPath_HandleValueFunctions_GetDateTime_MissingParameter() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:getdatetime");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_GetDate")
    @Test(dependsOnMethods = "createOperation")
    public void setRequestNodeValueByXPath_HandleValueFunctions_GetDate() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:getdate;0");
        Assert.assertTrue(getInfo.getRequestActorId().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_GetDate_MissingParameter")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = MissingFunctionParameterValueException.class)
    public void setRequestNodeValueByXPath_HandleValueFunctions_GetDate_MissingParameter() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:getdate");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_RemoveNode")
    @Test(dependsOnMethods = "createOperation")
    public void setRequestNodeValueByXPath_HandleValueFunctions_RemoveAttribute() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("//Envelope/Body/getActorsByIdRequest/actor_id", "fx:addAttribute;blah");
        getInfo.setRequestNodeValueByXPath("//Envelope/Body/getActorsByIdRequest/actor_id/@blah", "fx:removeattribute");
        Assert.assertTrue(getInfo.getRequest().contains("<my:actor_id>1</my:actor_id>"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_RemoveNode")
    @Test(dependsOnMethods = "createOperation")
    public void setRequestNodeValueByXPath_HandleValueFunctions_RemoveNode() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("//Envelope/Body/getActorsByIdRequest/actor_id", "fx:removenode");
        Assert.assertTrue(getInfo.getRequest().contains("<my:getActorsByIdRequest/>"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_RandomAlphaNumeric")
    @Test(dependsOnMethods = "createOperation")
    public void setRequestNodeValueByXPath_HandleValueFunctions_RandomAlphaNumeric() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:randomalphanumeric;2");
        Assert.assertTrue(getInfo.getRequestActorId().matches("[0-9 a-z A-Z]{2}"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_RandomAlphaNumeric_MissingParameter")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = MissingFunctionParameterValueException.class)
    public void setRequestNodeValueByXPath_HandleValueFunctions_RandomAlphaNumeric_MissingParameter() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:randomalphanumeric");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_RandomNumber")
    @Test(dependsOnMethods = "createOperation")
    public void setRequestNodeValueByXPath_HandleValueFunctions_RandomNumber() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:randomnumber;2");
        Assert.assertTrue(getInfo.getRequestActorId().matches("[0-9]{2}"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_RandomNumber_MissingParameter")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = MissingFunctionParameterValueException.class)
    public void setRequestNodeValueByXPath_HandleValueFunctions_RandomNumber_MissingParameter() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:RandomNumber");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_RandomString")
    @Test(dependsOnMethods = "createOperation")
    public void setRequestNodeValueByXPath_HandleValueFunctions_RandomString() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:RandomString;5");
        Assert.assertTrue(getInfo.getRequestActorId().matches("[a-z A-Z]{5}"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_RandomNumber_MissingParameter")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = SoapException.class)
    public void setRequestNodeValueByXPath_HandleValueFunctions_InvalidCommand() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:blah");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_HandleValueFunctions_RandomString_MissingParameter")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = MissingFunctionParameterValueException.class)
    public void setRequestNodeValueByXPath_HandleValueFunctions_RandomString_MissingParameter() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "fx:RandomString");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_XPathNotFoundException")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = XPathNotFoundException.class)
    public void setRequestNodeValueByXPath_XPathNotFoundException() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/blah", "blah");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_InvalidXPathExpression")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = XPathInvalidExpression.class)
    public void setRequestNodeValueByXPath_InvalidXPathExpression() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/blah\"", "blah");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("setRequestNodeValueByXPath_NullValueExpression")
    @Test(dependsOnMethods = "createOperation", expectedExceptions = XPathNullNodeValueException.class)
    public void setRequestNodeValueByXPath_NullValueExpression() {
        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", "");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("sendRequest")
    @Test(dependsOnMethods = "createOperation")
    public void sendRequest() {

        GetActorsById getInfo = new GetActorsById();
        getInfo.sendRequest();
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("sendRequestExpectFault")
    @Test(dependsOnMethods = "sendRequest")
    public void sendRequestExpectFault() {

        GetActorsById getInfo = new GetActorsById();
        getInfo.setRequestNodeValueByXPath("/Envelope/Body", SoapServiceCommands.removeNode());
        getInfo.sendRequest();
        Assert.assertTrue(getInfo.getResponseStatusCode().equals("SOAP-ENV:Server"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("sendRequest")
    @Test(dependsOnMethods = "createOperation")
    public void sendRequestWithHeaders() {

        GetActorsById getInfo = new GetActorsById();
        getInfo.addRequestHeader("encoding", "UTF-8");
        getInfo.sendRequest();
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("getResponse")
    @Test(dependsOnMethods = "sendRequest")
    public void getResponse() {

        GetActorsById getInfo = new GetActorsById();
        getInfo.sendRequest();
        Assert.assertTrue(!getInfo.getResponse().isEmpty());
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("getNumberOfResponseNodesByXPath")
    @Test(dependsOnMethods = "sendRequest")
    public void getNumberOfResponseNodesByXPath() {

        GetActorsById getInfo = new GetActorsById();
        getInfo.sendRequest();
        Assert.assertTrue(getInfo.getNumberOfResponseNodesByXPath("/Envelope/Body/getActorsByIdResponse/actor") == 1);
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("getResponseNodeValueByXPath")
    @Test(dependsOnMethods = "sendRequest")
    public void getResponseNodeValueByXPath() {

        GetActorsById getInfo = new GetActorsById();
        getInfo.sendRequest();
        Assert.assertTrue(getInfo.getResponseNodeValueByXPath("/Envelope/Body/getActorsByIdResponse/actor/actor_id").equals("1"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("getResponseHeaders")
    @Test(dependsOnMethods = "sendRequest")
    public void getResponseHeaders() {

        GetActorsById getInfo = new GetActorsById();
        getInfo.sendRequest();
        Assert.assertTrue(getInfo.getResponseHeader("Content-Type").equals("text/xml; charset=utf-8"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("getResponseHeaders_NoneFound")
    @Test(dependsOnMethods = "sendRequest", expectedExceptions = HeaderNotFoundException.class)
    public void getResponseHeaders_NoneFound() {

        GetActorsById getInfo = new GetActorsById();
        getInfo.sendRequest();
        getInfo.getResponseHeader("blah");
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("validateResponse_WithCSVData")
    @Test(dependsOnMethods = "sendRequest")
    public void validateResponse_WithCSVData() {

        GetActorsById getInfo = new GetActorsById();
        getInfo.sendRequest();
        Assert.assertTrue(getInfo.validateResponse("/excelsheets/GetActorsByIdResponse_csv.csv", "Main"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("validateResponse_WithXLSData")
    @Test(dependsOnMethods = "sendRequest")
    public void validateResponse_WithXLSData() {

        GetActorsById getInfo = new GetActorsById();
        getInfo.sendRequest();
        Assert.assertTrue(getInfo.validateResponse("/excelsheets/GetActorsByIdResponse_xls.xls", "Main"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("validateResponse_WithXLSXData")
    @Test(dependsOnMethods = "sendRequest")
    public void validateResponse_WithXLSXData() {

        GetActorsById getInfo = new GetActorsById();
        getInfo.sendRequest();
        Assert.assertTrue(getInfo.validateResponse("/excelsheets/GetActorsByIdResponse_xlsx.xlsx", "Main"));
    }

    @Features("API")
    @Stories("SoapServices")
    @Title("validateResponse_WithError")
    @Test(dependsOnMethods = "sendRequest")
    public void validateResponse_WithErrors() {

        GetActorsById getInfo = new GetActorsById();
        getInfo.sendRequest();
        Assert.assertFalse(getInfo.validateResponse("/excelsheets/GetActorsByIdResponse_ExpectErrors.csv", "Main"));
    }

}

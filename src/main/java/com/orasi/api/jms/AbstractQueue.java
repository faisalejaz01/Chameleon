package com.orasi.api.jms;

public class AbstractQueue {

    private String configName;
    private String environment;
    private String hostName = "";
    private String channel = "";
    private int port = 0;
    private String queueManager = "";
    private int transportType = 0;
    private String request = "";
    private String response = "";

    public AbstractQueue(String configName, String environment, String hostName, String channel, int port, String queueManager, int transportType, String request, String response) {
        this.configName = configName;
        this.environment = environment;
        this.hostName = hostName;
        this.channel = channel;
        this.port = port;
        this.queueManager = queueManager;
        this.transportType = transportType;
        this.request = request;
        this.response = response;
    }

    public String getConfigName() {
        return configName;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getHostName() {
        return hostName;
    }

    public String getChannel() {
        return channel;
    }

    public int getPort() {
        return port;
    }

    public String getQueueManager() {
        return queueManager;
    }

    public int getTransportType() {
        return transportType;
    }

    public String getRequest() {
        return request;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return String.format("Host Name: '%s' \nChannel: '%s' \nPort: '%s' \nQueue Manager: '%s' \nTransport Type: '%s' \nRequest: '%s' \nResponse: '%s'",
                hostName, channel, port, queueManager, transportType, request, response);
    }
}
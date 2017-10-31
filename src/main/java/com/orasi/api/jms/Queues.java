package com.orasi.api.jms;

public class Queues {
    private static String serverTipcoQA = "hostname";

    private static String getEaiHost(String environment) {
        switch (environment.toLowerCase()) {
            case "qa":
                return serverTipcoQA;

            default:
                throw new JmsAutomationException("Failed to match environment");
        }

    }

    public static final AbstractQueue SERVER1(String environment) {
        String host = getEaiHost(environment);
        return new AbstractQueue("SERVER1", // Metadata- Config Name
                environment, // Metadata- Environment name
                host + ".mgm.com", // Server
                "ChannelName", // Channel
                1414, // Port
                host.toUpperCase(), // Queue
                1, // Transport type
                "REQUEST", // Request Name
                "RESPONSE" + host.substring(9));// Response Name

    }

    public static final AbstractQueue SERVER2(String environment) {
        String host = getEaiHost(environment);
        return new AbstractQueue("SERVER2", // Metadata- Config Name
                environment, // Metadata- Environment name
                host + ".mgm.com", // Server
                "ChannelName", // Channel
                1414, // Port
                host.toUpperCase(), // Queue
                1, // Transport type
                "REQUEST", // Request Name
                "RESPONSE" + host.substring(9));// Response Name

    }
}

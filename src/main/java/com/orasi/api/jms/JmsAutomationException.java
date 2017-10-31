package com.orasi.api.jms;

import com.orasi.api.WebServiceException;

public class JmsAutomationException extends WebServiceException {
    private static final long serialVersionUID = -8710980695994382082L;

    public JmsAutomationException(String message) {
        super(message);
    }

    public JmsAutomationException(String message, Throwable cause) {
        super(message, cause);
    }

}
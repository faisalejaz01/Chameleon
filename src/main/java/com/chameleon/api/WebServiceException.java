package com.chameleon.api;

import com.chameleon.AutomationException;

public class WebServiceException extends AutomationException {
    private static final long serialVersionUID = -8710980695994382082L;

    public WebServiceException(String message) {
        super(message);
    }

    public WebServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}

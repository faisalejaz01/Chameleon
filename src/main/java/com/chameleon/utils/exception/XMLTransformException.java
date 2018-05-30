package com.chameleon.utils.exception;

import com.chameleon.AutomationException;

public class XMLTransformException extends AutomationException {
    private static final long serialVersionUID = 3407361723082329697L;

    public XMLTransformException(String message) {
        super(message);
    }

    public XMLTransformException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.chameleon.utils.exception;

import com.chameleon.AutomationException;

public class XPathNullNodeValueException extends AutomationException {
    private static final long serialVersionUID = 3407361723082329697L;

    public XPathNullNodeValueException(String message) {
        super("Node value to set in [ " + message + " ] was null");
    }
}

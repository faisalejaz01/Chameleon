package com.chameleon.web.exceptions;

import com.chameleon.web.ExtendedWebDriver;
import com.chameleon.web.WebException;

public class SelectElementNotFoundException extends WebException {
    private static final long serialVersionUID = 3407361723082329697L;

    public SelectElementNotFoundException(String message) {
        super(message);
    }

    public SelectElementNotFoundException(String message, ExtendedWebDriver driver) {
        super(message, driver);
    }
}

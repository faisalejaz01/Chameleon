package com.chameleon.web.exceptions;

import com.chameleon.web.ExtendedWebDriver;
import com.chameleon.web.WebException;

public class ElementNotEnabledException extends WebException {

    private static final long serialVersionUID = 6579447002670243452L;

    public ElementNotEnabledException(String message) {
        super(message);
    }

    public ElementNotEnabledException(String message, ExtendedWebDriver driver) {
        super(message, driver);
    }
}

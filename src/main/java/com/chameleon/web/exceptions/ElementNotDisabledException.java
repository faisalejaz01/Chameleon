package com.chameleon.web.exceptions;

import com.chameleon.web.ExtendedWebDriver;
import com.chameleon.web.WebException;

public class ElementNotDisabledException extends WebException {
    private static final long serialVersionUID = 624614577584686540L;

    public ElementNotDisabledException(String message) {
        super(message);
    }

    public ElementNotDisabledException(String message, ExtendedWebDriver driver) {
        super(message, driver);
    }
}

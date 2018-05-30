package com.chameleon.web.exceptions;

import com.chameleon.web.ExtendedWebDriver;
import com.chameleon.web.WebException;

public class ElementNotHiddenException extends WebException {

    private static final long serialVersionUID = 1865273000586352087L;

    public ElementNotHiddenException(String message) {
        super(message);
    }

    public ElementNotHiddenException(String message, ExtendedWebDriver driver) {
        super(message, driver);
    }
}

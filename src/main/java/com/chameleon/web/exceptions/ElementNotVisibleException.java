package com.chameleon.web.exceptions;

import com.chameleon.web.ExtendedWebDriver;
import com.chameleon.web.WebException;

public class ElementNotVisibleException extends WebException {
    private static final long serialVersionUID = 7724792038612608062L;

    public ElementNotVisibleException(String message) {
        super(message);
    }

    public ElementNotVisibleException(String message, ExtendedWebDriver driver) {
        super(message, driver);
    }
}

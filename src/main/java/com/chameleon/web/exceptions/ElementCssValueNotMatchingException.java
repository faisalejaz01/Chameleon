package com.chameleon.web.exceptions;

import com.chameleon.web.ExtendedWebDriver;
import com.chameleon.web.WebException;

public class ElementCssValueNotMatchingException extends WebException {
    private static final long serialVersionUID = 3407361723082329697L;

    public ElementCssValueNotMatchingException(String message) {
        super(message);
    }

    public ElementCssValueNotMatchingException(String message, ExtendedWebDriver driver) {
        super(message, driver);
    }
}

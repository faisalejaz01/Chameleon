package com.chameleon.web.exceptions;

import com.chameleon.web.ExtendedWebDriver;
import com.chameleon.web.WebException;

public class TextInElementNotPresentException extends WebException {
    private static final long serialVersionUID = 3407361723082329697L;

    public TextInElementNotPresentException(String message) {
        super(message);
    }

    public TextInElementNotPresentException(String message, ExtendedWebDriver driver) {
        super(message, driver);
    }
}

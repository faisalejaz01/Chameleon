package com.chameleon.web.exceptions;

import com.chameleon.web.ExtendedWebDriver;
import com.chameleon.web.WebException;

public class ElementNotFoundInFrameException extends WebException {
    private static final long serialVersionUID = 624614577584686540L;

    public ElementNotFoundInFrameException(String message) {
        super(message);
    }

    public ElementNotFoundInFrameException(String message, ExtendedWebDriver driver) {
        super(message, driver);
    }
}

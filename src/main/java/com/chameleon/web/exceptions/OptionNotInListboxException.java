package com.chameleon.web.exceptions;

import com.chameleon.web.ExtendedWebDriver;
import com.chameleon.web.WebException;

public class OptionNotInListboxException extends WebException {

    private static final long serialVersionUID = 4926417034544326093L;

    public OptionNotInListboxException(String message) {
        super(message);
    }

    public OptionNotInListboxException(String message, ExtendedWebDriver driver) {
        super(message, driver);
    }
}

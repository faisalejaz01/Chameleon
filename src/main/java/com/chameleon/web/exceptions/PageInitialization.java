package com.chameleon.web.exceptions;

import org.openqa.selenium.WebDriver;

import com.chameleon.web.WebException;

public class PageInitialization extends WebException {
    private static final long serialVersionUID = 3407361723082329697L;

    public PageInitialization(String message, WebDriver driver) {
        super(message, driver);
    }

}

package com.chameleon.web.webelements.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.chameleon.web.ExtendedWebDriver;
import com.chameleon.web.webelements.Label;

/**
 * Wraps a label on a html form with some behavior.
 */
public class LabelImpl extends ElementImpl implements Label {
    /**
     * Creates an Element for a given WebElement.
     *
     * @param element
     *            element to wrap up
     */

    public LabelImpl(ExtendedWebDriver driver, By by) {
        super(driver, by);
    }

    public LabelImpl(ExtendedWebDriver driver, By by, WebElement element) {
        super(driver, by, element);
    }

    @Override
    public String getFor() {
        return getWrappedElement().getAttribute("for");
    }
}
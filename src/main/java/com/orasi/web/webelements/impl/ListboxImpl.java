package com.orasi.web.webelements.impl;

import static com.orasi.utils.TestReporter.interfaceLog;
import static com.orasi.utils.TestReporter.logTrace;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

import com.orasi.AutomationException;
import com.orasi.web.OrasiDriver;
import com.orasi.web.exceptions.OptionNotInListboxException;
import com.orasi.web.webelements.Element;
import com.orasi.web.webelements.Listbox;

/**
 * Wrapper around a WebElement for the Select class in Selenium.
 */
public class ListboxImpl extends ElementImpl implements Listbox {
    private String optionTag = "";

    /**
     * @summary - Wraps a WebElement with listbox functionality.
     * @param element
     *            - element to wrap up
     */

    public ListboxImpl(OrasiDriver driver, By by) {
        super(driver, by);

        logTrace("Entering ListboxImpl#init");

        if (element != null) {
            optionTag = determineOptionTag();
        }

        logTrace("Exiting ListboxImpl#init");
    }

    private boolean determineIsMulti() {
        String value = getWrappedElement().getAttribute("multiple");

        // The atoms normalize the returned value, but check for "false"
        return (value != null && !"false".equals(value));
    }

    private String determineOptionTag() {
        String tagName = getWrappedElement().getTagName();

        switch (tagName.toLowerCase()) {
            case "ul":
                return "li";

            case "div":
                return "div";

            case "select":
            case "datalist":
            default:
                return "option";
        }
    }

    public void overrideOptionTag(String tag) {
        if (tag.isEmpty()) {
            throw new AutomationException("Custom tag cannot be null or empty");
        }

        optionTag = tag;
    }

    /**
     * @summary - Wraps Selenium's method.
     * @param text
     *            - visible text to select
     * @see org.openqa.selenium.support.ui.Select#selectByVisibleText(String)
     */
    @Override
    public void select(String text) {
        logTrace("Entering ListboxImpl#select");
        if (!text.isEmpty()) {

            if (optionTag.isEmpty()) {
                optionTag = determineOptionTag();
            }

            List<Element> options = getWrappedElement().findElements(By.xpath(
                    ".//" + optionTag + "[normalize-space(.) = " + Quotes.escape(text) + "]"));

            // If no options found for requested text, collect all option values and report out
            if (options.size() == 0) {
                String optionList = getOptions().stream().map(e -> e.getText()).collect(Collectors.joining(" | "));

                interfaceLog(" The value of <b>[ " + text + "</b> ] was not found in Listbox [  <b>"
                        + getElementLocatorInfo() + " </b>]. Acceptable values are " + optionList + " ]");
                logTrace("Exiting ListboxImpl#select");
                throw new OptionNotInListboxException("The value of [ " + text + " ] was not found in Listbox [  "
                        + getElementLocatorInfo() + " ]. Acceptable values are " + optionList, driver);
            }

            // for each matching element, set to true
            for (Element option : options) {
                setSelected(option, true);
                if (!determineIsMulti()) {
                    return;
                }
            }

        } else {
            interfaceLog("Skipping input to Textbox [ <b>" + getElementLocatorInfo() + " </b> ]");
        }

        logTrace("Exiting ListboxImpl#select");
    }

    /**
     * @summary - Wraps Selenium's method.
     * @param value
     *            - option value to select
     * @see org.openqa.selenium.support.ui.Select#selectByVisibleText(String)
     */
    @Override
    public void selectValue(String value) {
        logTrace("Entering ListboxImpl#selectValue");
        if (!value.isEmpty()) {
            if (optionTag.isEmpty()) {
                optionTag = determineOptionTag();
            }

            if (optionTag.isEmpty()) {
                optionTag = determineOptionTag();
            }

            List<Element> options = getWrappedElement().findElements(By.xpath(
                    ".//" + optionTag + "[@value = " + Quotes.escape(value) + "]"));

            // If no options found for requested text, collect all option values and report out
            if (options.size() == 0) {
                String optionList = getOptions().stream().map(e -> e.getAttribute("value")).collect(Collectors.joining(" | "));

                interfaceLog(" The value of <b>[ " + value + "</b> ] was not found in Listbox [  <b>"
                        + getElementLocatorInfo() + " </b>]. Acceptable values are " + optionList + " ]");
                logTrace("Exiting ListboxImpl#select");
                throw new OptionNotInListboxException("The value of [ " + value + " ] was not found in Listbox [  "
                        + getElementLocatorInfo() + " ]. Acceptable values are " + optionList, driver);
            }

            // for each matching element, set to true
            for (Element option : options) {
                setSelected(option, true);
                if (!determineIsMulti()) {
                    return;
                }
            }

        } else {
            interfaceLog("Skipping input to Textbox [ <b>" + getElementLocatorInfo() + " </b> ]");
        }
        logTrace("Exiting ListboxImpl#selectValue");
    }

    /**
     * @summary - Wraps Selenium's method.
     * @see org.openqa.selenium.support.ui.Select#deselectAll()
     */
    @Override
    public void deselectAll() {
        logTrace("Entering ListboxImpl#deselectAll");
        if (!isMultiple()) {
            throw new UnsupportedOperationException(
                    "You may only deselect all options of a multi-select");
        }

        for (Element option : getOptions()) {
            setSelected(option, false);
        }
        logTrace("Exiting ListboxImpl#deselectAll");
    }

    /**
     * @summary - Wraps Selenium's method.
     * @return list of all options in the select.
     * @see org.openqa.selenium.support.ui.Select#getOptions()
     */
    @Override
    public List<Element> getOptions() {
        logTrace("Entering ListboxImpl#getOptions");
        List<Element> options = getWrappedElement().findElements(By.xpath(".//" + optionTag));
        logTrace("Exiting ListboxImpl#getOptions");
        return options;
    }

    /**
     * @summary - Wraps Selenium's method.
     * @return list of all option values in the select.
     */
    @Override
    public List<String> getOptionValues() {
        logTrace("Entering ListboxImpl#getOptionValues");
        List<String> values = getOptions().stream().map(WebElement::getText).map(String::trim).collect(toList());
        logTrace("Exiting ListboxImpl#getOptionValues");
        return values;
    }

    /**
     * @summary - Wraps Selenium's method.
     * @param text
     *            text to deselect by visible text
     * @see org.openqa.selenium.support.ui.Select#deselectByVisibleText(String)
     */
    @Override
    public void deselectByVisibleText(String text) {
        logTrace("Entering ListboxImpl#deselectByVisibleText");
        if (!determineIsMulti()) {
            throw new UnsupportedOperationException(
                    "You may only deselect options of a multi-select");
        }

        List<Element> options = getWrappedElement().findElements(By.xpath(
                ".//" + optionTag + "[normalize-space(.) =" + Quotes.escape(text) + "]"));

        for (Element option : options) {
            setSelected(option, false);
        }
        logTrace("Exiting ListboxImpl#deselectByVisibleText");
    }

    /**
     * @summary - Wraps Selenium's method.
     * @return WebElement of the first selected option.
     * @see org.openqa.selenium.support.ui.Select#getFirstSelectedOption()
     */
    @Override
    public Element getFirstSelectedOption() {
        logTrace("Entering ListboxImpl#getFirstSelectedOption");
        Element option = getAllSelectedOptions().stream().findFirst().orElse(null);
        logTrace("Exiting ListboxImpl#getFirstSelectedOption");
        return option;
    }

    /**
     * @see org.openqa.selenium.WebElement#isSelected()
     */
    @Override
    public boolean isSelected(String option) {
        logTrace("Entering ListboxImpl#isSelected");
        boolean selected = getAllSelectedOptions().stream().anyMatch(selectedOption -> selectedOption.getText().equals(option));
        logTrace("Exiting ListboxImpl#isSelected");
        return selected;
    }

    @Override
    public List<Element> getAllSelectedOptions() {
        logTrace("Entering ListboxImpl#getAllSelectedOptions");
        List<Element> options = getOptions().stream().filter(element -> element.isSelected()).collect(toList());
        logTrace("Exiting ListboxImpl#getAllSelectedOptions");
        return options;
    }

    @Override
    public boolean isMultiple() {
        logTrace("Entering ListboxImpl#isMultiple");
        boolean multiple = determineIsMulti();
        logTrace("Exiting ListboxImpl#isMultiple");
        return multiple;
    }

    /**
     * Select or deselect specified option
     *
     * @param option
     *            The option which state needs to be changed
     * @param select
     *            Indicates whether the option needs to be selected (true) or
     *            deselected (false)
     */
    private void setSelected(Element option, boolean select) {
        boolean isSelected = option.isSelected();
        if ((!isSelected && select) || (isSelected && !select)) {
            option.click();
        }
    }
}
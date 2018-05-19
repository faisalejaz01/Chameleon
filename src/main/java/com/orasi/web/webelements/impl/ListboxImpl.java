package com.orasi.web.webelements.impl;

import static com.orasi.utils.TestReporter.interfaceLog;
import static com.orasi.utils.TestReporter.logTrace;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

import com.orasi.web.OrasiDriver;
import com.orasi.web.WebException;
import com.orasi.web.exceptions.OptionNotInListboxException;
import com.orasi.web.webelements.Element;
import com.orasi.web.webelements.Listbox;

/**
 * Wrapper around a WebElement for the Select class in Selenium.
 */
@SuppressWarnings("unchecked")
public class ListboxImpl extends ElementImpl implements Listbox {
    private String optionTag = "";
    private String clickableTag = "";
    private Boolean multiple;

    /**
     * Wraps a WebElement with listbox functionality.
     *
     * @param element
     *            - element to wrap up
     */

    public ListboxImpl(OrasiDriver driver, By by) {
        super(driver, by);

        logTrace("Entering ListboxImpl#init");

        if (element != null) {
            optionTag = determineOptionTag();
            multiple = isMultiple();
        }

        logTrace("Exiting ListboxImpl#init");
    }

    /**
     * Allows user to override the default element tag of the container for Listbox items. By default,
     * Listbox will attempt to find child "li" elements for a parent "ul" Listbox. "select" and "datalist"
     * type Listboxes will attempt to find child "option" elements. Anything other than "li" or
     * "option" can be defined here
     *
     * @param tag
     *            - tag of the child element to search
     */
    @Override
    public void overrideOptionTag(String tag) {
        logTrace("Entering ListboxImpl#overrideOptionTag");
        if (isBlank(tag)) {
            throw new WebException("Option tag cannot be null or empty", driver);
        }
        optionTag = tag.trim();
        interfaceLog("Overriding Listbox option tag to be [ " + tag + " ]");
        logTrace("Exiting ListboxImpl#overrideOptionTag");
    }

    /**
     * Allows user to override the default element tag to click in the container for Listbox items in the
     * case the element with the serached value does not contain the click event. By default,
     * Listbox will attempt to click child "li" elements for a parent "ul" Listbox. "select" and "datalist"
     * type Listboxes will attempt to click child "option" elements. Anything other than "li" or
     * "option" can be defined here
     *
     * @param tag
     *            - tag of the child element to click
     */
    @Override
    public void overrideClickableTag(String tag) {
        logTrace("Entering ListboxImpl#overrideClickableTag");
        if (isBlank(tag)) {
            throw new WebException("Clickable tag cannot be null or empty", driver);
        }
        clickableTag = tag.trim();
        interfaceLog("Overriding Listbox element to click tag to be [ " + clickableTag + " ]");
        logTrace("Exiting ListboxImpl#overrideClickableTag");
    }

    /**
     * Click option with text
     *
     * @param text
     *            - visible text to select
     */
    @Override
    public void select(String text) {
        logTrace("Entering ListboxImpl#select");

        if (isNotBlank(text)) {
            // In the case when the Listbox was created, but element was not found, then optionTag is not set
            // Ensure optionTag is set
            if (isBlank(optionTag)) {
                logTrace("Tag was empty or null. Determine tag");
                optionTag = determineOptionTag();
            }

            // Use normalize-space on the element itself (.) to limit text search to just the element
            // Using text() would get all text in child elements as well
            logTrace("Finding child elements in Listbox");
            List<WebElement> elements = getWrappedElement().findElements(By.xpath(
                    ".//" + optionTag + "[normalize-space(.) = " + Quotes.escape(text) + "]"));
            List<Element> options = new ArrayList<>();
            elements.forEach(option -> options.add(new ElementImpl(driver, by, option)));

            // If no options found for requested text, collect all option values and report out
            if (options.isEmpty()) {
                String optionList = getOptionTextValues().stream().collect(Collectors.joining(" | "));

                interfaceLog(" The value of <b>[ " + text + "</b> ] was not found in Listbox [  <b>"
                        + getElementLocatorInfo() + " </b>]. Acceptable values are " + optionList + " ]");
                logTrace("Exiting ListboxImpl#select");
                throw new OptionNotInListboxException("The value of [ " + text + " ] was not found in Listbox [  "
                        + getElementLocatorInfo() + " ]. Acceptable values are " + optionList, driver);
            }
            logTrace("Successfully found child elements");

            // for each matching element, set to true
            logTrace("Selecting all matching options");
            for (Element option : options) {
                setSelected(option, true);
                if (!isMultiple()) {
                    logTrace("Listbox is not multiple, only selected first option");
                    return;
                }
            }

        } else {
            interfaceLog("Skipping selection of option in Listbox [ <b>" + getElementLocatorInfo() + " </b> ]");
        }

        logTrace("Exiting ListboxImpl#select");
    }

    /**
     * Click option with attribute with specific value
     *
     * @param text
     *            - value option to select
     */
    @Override
    public void selectValue(String value) {
        logTrace("Entering ListboxImpl#selectValue");
        if (isNotBlank(value)) {

            // In the case when the Listbox was created, but element was not found, then optionTag is not set
            // Ensure optionTag is set
            if (isBlank(optionTag)) {
                logTrace("Tag was empty or null. Determine tag");
                optionTag = determineOptionTag();
            }

            logTrace("Finding child elements in Listbox");
            List<WebElement> elements = getWrappedElement().findElements(By.xpath(
                    ".//" + optionTag + "[@value = " + Quotes.escape(value) + "]"));
            List<Element> options = new ArrayList<>();
            elements.forEach(option -> options.add(new ElementImpl(driver, by, option)));

            // If no options found for requested value, collect all option values and report out
            if (options.isEmpty()) {
                String optionList = getOptionValues().stream().collect(Collectors.joining(" | "));

                interfaceLog(" The value of <b>[ " + value + "</b> ] was not found in Listbox [  <b>"
                        + getElementLocatorInfo() + " </b>]. Acceptable values are " + optionList + " ]");
                logTrace("Exiting ListboxImpl#selectValue");
                throw new OptionNotInListboxException("The value of [ " + value + " ] was not found in Listbox [  "
                        + getElementLocatorInfo() + " ]. Acceptable values are " + optionList, driver);
            }

            logTrace("Successfully found child elements");

            // for each matching element, set to true
            for (Element option : options) {
                setSelected(option, true);
                if (!isMultiple()) {
                    logTrace("Listbox is not multiple, only selected first option");
                    return;
                }
            }

        } else {
            interfaceLog("Skipping input to Textbox [ <b>" + getElementLocatorInfo() + " </b> ]");
        }
        logTrace("Exiting ListboxImpl#selectValue");
    }

    /**
     * Deselect all selection options only if multi-select Listbox
     */
    @Override
    public void deselectAll() {
        logTrace("Entering ListboxImpl#deselectAll");
        if (!isMultiple()) {
            throw new WebException("You may only deselect all options of a multi-select");
        }

        logTrace("Deselecting all options");
        for (Element option : getOptions()) {
            setSelected(option, false);
        }
        logTrace("Exiting ListboxImpl#deselectAll");
    }

    /**
     * Click option with text
     *
     * @param text
     *            - visible text to select
     */
    @Override
    public void deselectByVisibleText(String text) {
        logTrace("Entering ListboxImpl#deselectByVisibleText");
        if (!isMultiple()) {
            throw new WebException("You may only deselect options of a multi-select");
        }

        logTrace("Finding child elements in Listbox");
        List<WebElement> elements = getWrappedElement().findElements(By.xpath(
                ".//" + optionTag + "[normalize-space(.) =" + Quotes.escape(text) + "]"));
        List<Element> options = new ArrayList<>();
        elements.forEach(option -> options.add(new ElementImpl(driver, by, option)));

        for (Element option : options) {
            setSelected(option, false);
        }
        logTrace("Exiting ListboxImpl#deselectByVisibleText");
    }

    @Override
    public List<Element> getAllSelectedOptions() {
        logTrace("Entering ListboxImpl#getAllSelectedOptions");
        List<Element> options = getOptions().stream().filter(Element::isSelected).collect(toList());
        logTrace("Exiting ListboxImpl#getAllSelectedOptions");
        return options;
    }

    /**
     * return first option that is select in list
     *
     * @return first option that is select in list
     */
    @Override
    public Element getFirstSelectedOption() {
        logTrace("Entering ListboxImpl#getFirstSelectedOption");
        Element option = getOptions().stream().filter(Element::isSelected).findFirst().orElse(null);
        logTrace("Exiting ListboxImpl#getFirstSelectedOption");
        return option;
    }

    /**
     * return list of all options in the select
     *
     * @return list of all options in the select.
     */
    @Override
    public List<Element> getOptions() {
        logTrace("Entering ListboxImpl#getOptions");
        if (getWrappedElement() != null) {
            optionTag = determineOptionTag();
            multiple = isMultiple();
        }
        List<WebElement> elements = getWrappedElement().findElements(By.xpath(".//" + optionTag));
        List<Element> options = new ArrayList<>();
        elements.forEach(option -> options.add(new ElementImpl(driver, by, option)));
        logTrace("Exiting ListboxImpl#getOptions");
        return options;
    }

    /**
     * list of all option values in the select.
     *
     * @return list of all option values in the select.
     */
    @Override
    public List<String> getOptionValues() {
        logTrace("Entering ListboxImpl#getOptionValues");
        // Get attribute of value from each option and return in a List
        List<String> values = getOptions().stream().map(e -> e.getAttribute("value")).map(String::trim).collect(toList());
        logTrace("Exiting ListboxImpl#getOptionValues");
        return values;
    }

    /**
     * Wraps Selenium's method.
     *
     * @return list of all option values in the select.
     */
    @Override
    public List<String> getOptionTextValues() {
        logTrace("Entering ListboxImpl#getOptionTextValues");
        // Get text value from each option and return in a List
        List<String> values = getOptions().stream().map(WebElement::getText).map(String::trim).collect(toList());
        logTrace("Exiting ListboxImpl#getOptionTextValues");
        return values;
    }

    @Override
    public boolean isSelected(String option) {
        logTrace("Entering ListboxImpl#isSelected");
        boolean selected = getAllSelectedOptions().stream().anyMatch(selectedOption -> selectedOption.getText().equals(option));
        logTrace("Exiting ListboxImpl#isSelected");
        return selected;
    }

    @Override
    public boolean isMultiple() {
        logTrace("Entering ListboxImpl#isMultiple");
        if (multiple == null) {
            String value = getWrappedElement().getAttribute("multiple");

            // The atoms normalize the returned value, but check for "false"
            multiple = (value != null && !"false".equals(value));
        }
        logTrace("Exiting ListboxImpl#isMultiple");
        return multiple;
    }

    private String determineOptionTag() {
        logTrace("Entering ListboxImpl#determineOptionTag");
        String tagName = getWrappedElement().getTagName();

        switch (tagName.toLowerCase()) {
            case "ul":
                logTrace("Determined option tag for [ " + tagName + " ] to be [ li ]");
                logTrace("Exiting ListboxImpl#determineOptionTag");
                return "li";

            case "select":
            case "datalist":
            default:
                logTrace("Determined option tag for [ " + tagName + " ] to be [ option ]");
                logTrace("Exiting ListboxImpl#determineOptionTag");
                return "option";
        }
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
    private void setSelected(final Element option, boolean select) {
        boolean isSelected = option.isSelected();
        if ((!isSelected && select) || (isSelected && !select)) {
            if (isBlank(clickableTag)) {
                option.click();
            } else {
                WebElement el = option.findWebElement(By.xpath(".//" + clickableTag));
                new ElementImpl(driver, by, el).click();
            }

        }
    }
}
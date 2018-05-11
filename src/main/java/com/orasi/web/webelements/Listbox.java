package com.orasi.web.webelements;

import java.util.List;

import com.orasi.web.webelements.impl.ListboxImpl;
import com.orasi.web.webelements.impl.internal.ImplementedBy;

/**
 * Interface for a select element.
 */
@ImplementedBy(ListboxImpl.class)
public interface Listbox extends Element {

    /**
     * Deselect all selection options only if multi-select Listbox
     */
    void deselectAll();

    /**
     * Click option with text
     * 
     * @param text
     *            - visible text to select
     */
    void deselectByVisibleText(String text);

    /**
     * return first option that is select in list
     * 
     * @return first option that is select in list
     */
    Element getFirstSelectedOption();

    /**
     * return list of all options in the select
     * 
     * @return list of all options in the select.
     */
    List<Element> getOptions();

    /**
     * list of all option values in the select.
     * 
     * @return list of all option values in the select.
     */
    List<String> getOptionValues();

    /**
     * Wraps Selenium's method.
     * 
     * @return list of all option values in the select.
     */
    List<String> getOptionTextValues();

    /**
     * returns list of all selected options in a given listbox
     * 
     * @return list of all selected options in a given listbox
     */
    List<Element> getAllSelectedOptions();

    /**
     * Checks Listbox for multiple attribute
     * 
     * @return boolean based on if multiple attribute is found
     */
    boolean isMultiple();

    /**
     * @return TRUE if element is currently selected using Selenium isSelected
     * @see org.openqa.selenium.WebElement#isSelected()
     */
    boolean isSelected(String option);

    /**
     *
     * Allows user to override the default element tag of the container for Listbox items. By default,
     * Listbox will attempt to find child "li" elements for a parent "ul" Listbox. "select" and "datalist"
     * type Listboxes will attempt to find child "option" elements. Anything other than "li" or
     * "option" can be defined here
     * 
     * @param tag
     *            - xpath tag of element that code should search for option text or value attribute
     */
    public void overrideOptionTag(String tag);

    /**
     * Allows user to override the default element tag to click in the container for Listbox items in the
     * case the element with the serached value does not contain the click event. By default,
     * Listbox will attempt to click child "li" elements for a parent "ul" Listbox. "select" and "datalist"
     * type Listboxes will attempt to click child "option" elements. Anything other than "li" or
     * "option" can be defined here
     * 
     * @param tag
     *            - xpath tag of element that code should click for found options
     */
    public void overrideClickableTag(String tag);

    /**
     * Click option with text
     * 
     * @param text
     *            - visible text to select
     */
    void select(String value);

    /**
     * Click option with attribute with specific value
     * 
     * @param text
     *            - value option to select
     */
    void selectValue(String value);
}
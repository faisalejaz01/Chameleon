package com.orasi.bluesource.commons;

import org.openqa.selenium.By;

import com.orasi.DriverManager;

/**
 * @author justin.phlegar@orasi.com
 * 
 * @doc.description
 *                  Class for handling the common Pagination element (page selector) seen on the Bluesource site
 */
public class Pagination {
    /**
     * 
     * @return String - Current page number
     * @doc.description The Pagination button element with the class "active" is the current page.
     *                  Return the text of the element.
     */

    public static String getCurrentPage() {
        return DriverManager.getDriver().findElement(By.className("pagination")).findElement(By.className("active")).getText();
    }

    /**
     * 
     * @return Boolean
     * @doc.description Returns true if successful page move, false if it stays on the same page
     */
    public static boolean moveNext() {
        if (!DriverManager.getDriver().findElement(By.className("pagination")).isDisplayed())
            return false;
        String currentPage = getCurrentPage();
        DriverManager.getDriver().findElement(By.className("pagination")).findElement(By.cssSelector("li:last-child > a")).click();
        String nextPage = getCurrentPage();
        if (currentPage.equals(nextPage))
            return false;
        return true;
    }

    /**
     * 
     * @return Boolean
     * @doc.description Returns true if successful page move, false if it stays on the same page
     */
    public static boolean movePrevious() {
        if (!DriverManager.getDriver().findElement(By.className("pagination")).isDisplayed())
            return false;
        String currentPage = getCurrentPage();
        DriverManager.getDriver().findButton(By.cssSelector("li:last-child > a")).click();
        String nextPage = getCurrentPage();
        if (currentPage.equals(nextPage))
            return false;
        return true;
    }
}

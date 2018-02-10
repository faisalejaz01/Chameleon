package com.orasi.bluesource.titlesPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orasi.DriverManager;
import com.orasi.web.AlertHandler;
import com.orasi.web.OrasiDriver;
import com.orasi.web.exceptions.ElementNotVisibleException;
import com.orasi.web.webelements.Label;
import com.orasi.web.webelements.Link;
import com.orasi.web.webelements.Webtable;
import com.orasi.web.webelements.impl.internal.ElementFactory;

import ru.yandex.qatools.allure.annotations.Step;

public class ListingTitlesPage {
    private OrasiDriver driver = null;

    // All the page elements
    @FindBy(linkText = "New Title")
    private Link lnkNewTitle;
    @FindBy(xpath = "//h1[text() = 'Listing titles']")
    private Label lblTitle;
    @FindBy(css = ".alert-success.alert-dismissable")
    private Label lblSuccessMsg;
    @FindBy(className = "table")
    private Webtable tabTitles;

    private By editIcon = By.cssSelector("div:nth-child(1) > a:nth-child(1)");
    private By deleteIcon = By.cssSelector("div:nth-child(1) > a:nth-child(2)");

    // *********************
    // ** Build page area **
    // *********************
    public ListingTitlesPage() {
        this.driver = DriverManager.getDriver();
        ;
        ElementFactory.initElements(driver, this);
    }

    public boolean pageLoaded() {
        return driver.page().isElementLoaded(this.getClass(), driver, lnkNewTitle);
    }

    // *****************************************
    // ***Page Interactions ***
    // *****************************************

    @Step("Click the \"New Title\" link")
    public void clickNewTitle() {
        lnkNewTitle.click();
    }

    public boolean isTitleHeaderDisplayed() {
        return lblTitle.isDisplayed();
    }

    @Step("Click the \"Edit Title\" icon on the row for title \"{0}\"")
    public void clickModifyTitle(String title) {
        driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'" + title + "')]/div/a/span[contains(@class,'glyphicon-pencil')]")).click();
    }

    @Step("An alert should appear for confirmation")
    public boolean isSuccessMsgDisplayed() {
        boolean displayed;
        try {
            displayed = lblSuccessMsg.syncVisible();
            return displayed;

        } catch (ElementNotVisibleException e) {
            return false;
        }

    }

    @Step("The title \"{0}\" should be found on the Titles table")
    public boolean searchTableByTitle(String title) {
        if (getTitleRowPosition(title) > 0)
            return true;
        return false;
    }

    @Step("Delete the title from the table")
    public void deleteTitle(String title, String browser) {
        // 8/15/2016 Safari driver does not currently handle modal alerts. This is a work around to accept the alert
        // see issue in github for details: https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/3862
        if (browser.equalsIgnoreCase("safari")) {
            driver.executeJavaScript("confirm = function(message){return true;};");
            driver.executeJavaScript("alert = function(message){return true;};");
            driver.executeJavaScript("prompt = function(message){return true;}");
            driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'" + title + "')]/div/a/span[contains(@class,'glyphicon-trash')]")).click();
        } else {
            driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'" + title + "')]/div/a/span[contains(@class,'glyphicon-trash')]")).click();
            AlertHandler.handleAllAlerts(driver, 2);
        }
    }

    /*
     * public void ensureNoExistingTitle(String title) {
     * if (searchTableByTitle(title)) {
     * BlueSource blueSource = new BlueSource("Company.admin");
     * List<Title> titles = blueSource.titles().getAllTitles();
     * Title tempTitle = null;
     * Iterator<Title> titleIterator = titles.iterator();
     * while (titleIterator.hasNext()) {
     * tempTitle = titleIterator.next();
     * if (tempTitle.getName().equals(title))
     * blueSource.titles().deleteTitle(tempTitle);
     * }
     * TestReporter.log("The title of \"" + title + "\" previously existed. Deleting previous title");
     * 
     * }
     * }
     */

    private WebElement getTitleRowElement(String title) {
        int titleRow = getTitleRowPosition(title);
        return tabTitles.getCell(titleRow, 1);
    }

    private int getTitleRowPosition(String title) {
        return tabTitles.getRowWithCellText(title, 1, 1, false);
    }
}

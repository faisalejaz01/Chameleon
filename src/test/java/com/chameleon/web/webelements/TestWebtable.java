package com.chameleon.web.webelements;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.chameleon.DriverManager;
import com.chameleon.web.ExtendedWebDriver;
import com.chameleon.web.WebBaseTest;
import com.chameleon.web.webelements.Webtable;
import com.chameleon.web.webelements.impl.WebtableImpl;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class TestWebtable extends WebBaseTest {
    ExtendedWebDriver driver = null;

    private String xpath = "//table";

    @BeforeClass(groups = { "regression", "interfaces", "webtable", "dev" })
    public void setup() {
        setPageURL("http://orasi.github.io/Chameleon/sites/unitTests/orasi/core/interfaces/webtable.html");
    }

    @Override
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResults) {
    }

    @Override
    @AfterClass(alwaysRun = true)
    public void afterClass(ITestContext testResults) {
        DriverManager.setDriver(driver);
        endTest(getTestName(), testResults);
    }

    @AfterClass(groups = { "regression", "interfaces", "webtable", "dev" })
    public void close(ITestContext testResults) {
        DriverManager.setDriver(driver);
        endTest("TestAlert", testResults);
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("constructor")
    @Test(groups = { "regression", "interfaces", "webtable" })
    public void constructorWithElement() {
        driver = testStart("TestWebtable");
        Assert.assertNotNull((new WebtableImpl(driver, (By.xpath(xpath)))));
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("clickCell")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void clickCell() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        webtable.clickCell(1, 1);
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("getCell")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void getCell() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getCell(2, 2).getText().equals("Sylvester"));

    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("getCellDate")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void getCellData() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getCellData(2, 2).equals("Sylvester"));
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("getColumnCount")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void getColumnCount() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getColumnCount(1) == 5);
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("getColumnWithCellText")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void getColumnWithCellText() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getColumnWithCellText("Last Name") == 3);
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("getColumnWithCellTextUsingRow")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void getColumnWithCellTextUsingRow() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getColumnWithCellText("Agassi", 5) == 3);
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("getColumnWithCellTextNothingFound")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void getColumnWithCellTextNothingFound() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getColumnWithCellText("Blah") == 0);
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("getRowCount")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void getRowCount() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getRowCount() == 7);
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("getRowWithCellText")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void getRowWithCellText() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getRowWithCellText("Schwarzenegger") == 6);
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("getRowWithCellTextSpecifiedColumn")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void getRowWithCellTextSpecifiedColumn() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getRowWithCellText("Movie", 5) == 2);
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("getRowWithCellTextSpecifiedRowAndColumn")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void getRowWithCellTextSpecifiedRowAndColumn() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getRowWithCellText("Male", 4, 4) == 5);
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("getRowWithCellTextNotExact")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void getRowWithCellTextNotExact() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getRowWithCellText("SpOrT", 5, 3, false) == 5);
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("getRowWithCellTextNothingFound")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void getRowWithCellTextNothingFound() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getRowWithCellText("NOTHING!", 2, 2, true) == 0);
    }

    @Features("Element Interfaces")
    @Stories("Webtable")
    @Title("getRowWithCellTextNoColumn")
    @Test(groups = { "regression", "interfaces", "webtable" }, dependsOnMethods = "constructorWithElement")
    public void getRowWithCellTextNoColumn() {
        Webtable webtable = driver.findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getRowWithCellText("Science", -1, 2, false) == 4);
    }
}

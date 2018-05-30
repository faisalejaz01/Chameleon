package com.chameleon.web.webelements;

import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
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
import com.chameleon.web.WebException;
import com.chameleon.web.exceptions.OptionNotInListboxException;
import com.chameleon.web.webelements.Button;
import com.chameleon.web.webelements.Listbox;
import com.chameleon.web.webelements.impl.ListboxImpl;
import com.chameleon.web.webelements.impl.internal.ElementFactory;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class TestListbox extends WebBaseTest {
    @FindBy(id = "wrongID")
    Listbox badSelect;
    @FindBy(id = "singleSelect")
    Listbox listbox;

    ExtendedWebDriver driver = null;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        setApplicationUnderTest("Test Site");
        setPageURL("http://orasi.github.io/Chameleon/sites/unitTests/orasi/core/interfaces/listbox.html");
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

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("constructor")
    @Test(groups = { "regression", "interfaces", "listbox" })
    public void constructorWithElement() {
        driver = testStart("TestListbox");
        Assert.assertNotNull((new ListboxImpl(driver, (By.id("singleSelect")))));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("isMultiple")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "constructorWithElement")
    public void isMultiple() {
        Listbox listbox = driver.findListbox(By.id("multiSelect"));
        Assert.assertTrue(listbox.isMultiple());
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("select")
    @Test(groups = { "regression", "interfaces", "listbox", "mustard" }, dependsOnMethods = "constructorWithElement")
    public void select() {
        Listbox listbox = driver.findListbox(By.id("singleSelect"));
        listbox.select("Sports");
        Assert.assertTrue(listbox.getFirstSelectedOption().getText().equals("Sports"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("selectFromMultiple")
    @Test(groups = { "regression", "interfaces", "listbox", "mustard" }, dependsOnMethods = "constructorWithElement")
    public void selectFromMultiple() {
        Listbox listbox = driver.findListbox(By.id("multiSelectFalse"));
        listbox.select("Tennis");
        Assert.assertTrue(listbox.getFirstSelectedOption().getText().equals("Tennis"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("selectValueFromMultiple")
    @Test(groups = { "regression", "interfaces", "listbox", "mustard" }, dependsOnMethods = "selectFromMultiple")
    public void selectValueFromMultiple() {
        Listbox listbox = driver.findListbox(By.id("multiSelectFalse"));
        listbox.selectValue("Badminton");
        Assert.assertTrue(listbox.getFirstSelectedOption().getText().equals("Badminton"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("selectNoText")
    @Test(groups = { "regression", "interfaces", "textbox" }, dependsOnMethods = "select")
    public void selectNoText() {
        Listbox listbox = driver.findListbox(By.id("singleSelect"));
        listbox.select("");
        Assert.assertTrue(listbox.getAttribute("value").equals("Sports"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("selectNegative")
    @Test(groups = { "regression", "interfaces", "textbox" }, dependsOnMethods = "select", expectedExceptions = OptionNotInListboxException.class)
    public void selectNegative() {
        Listbox listbox = driver.findListbox(By.id("singleSelect"));
        listbox.select("text");
        Assert.assertTrue(false);
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("selectValue")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "constructorWithElement")
    public void selectValue() {
        Listbox listbox = driver.findListbox(By.id("singleSelect"));
        listbox.selectValue("Sports");
        Assert.assertTrue(listbox.getFirstSelectedOption().getText().equals("Sports"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("selectValueNoText")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "select")
    public void selectValueNoText() {
        Listbox listbox = driver.findListbox(By.id("singleSelect"));
        listbox.selectValue("");
        Assert.assertTrue(listbox.getAttribute("value").equals("Sports"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("selectValueNegative")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "select", expectedExceptions = OptionNotInListboxException.class)
    public void selectValueNegative() {
        Listbox listbox = driver.findListbox(By.id("singleSelect"));
        listbox.selectValue("text");
        Assert.assertTrue(false);
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("getAllOptions")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "select")
    public void getAllOptions() {
        Listbox listbox = driver.findListbox(By.id("singleSelect"));
        Assert.assertTrue(listbox.getOptions().get(0).getText().equals("Other"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("getAllOptionsSelected")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "select")
    public void getAllSelectedOptions() {
        Listbox listbox = driver.findListbox(By.id("singleSelect"));
        Assert.assertTrue(listbox.getAllSelectedOptions().get(0).getText().equals("Sports"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("isSelected")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "getAllSelectedOptions")
    public void isSelected() {
        Listbox listbox = driver.findListbox(By.id("singleSelect"));
        Assert.assertTrue(listbox.isSelected("Sports"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("deselectByVisibleText")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "isSelected")
    public void deselectByVisibleText() {
        Listbox listbox = driver.findListbox(By.id("multiSelect"));
        listbox.select("Baseball");
        Assert.assertTrue(listbox.isSelected("Baseball"));
        listbox.deselectByVisibleText("Baseball");
        Assert.assertFalse(listbox.isSelected("Baseball"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("deselectByVisibleTextNonMulti")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "constructorWithElement", expectedExceptions = WebException.class)
    public void deselectByVisibleTextNonMulti() {
        Listbox listbox = driver.findListbox(By.id("singleSelect"));
        listbox.deselectByVisibleText("Soccer");
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("deselectAll")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "deselectByVisibleText")
    public void deselectAll() {
        Listbox listbox = driver.findListbox(By.id("multiSelect"));
        listbox.select("Basketball");
        listbox.select("Soccer");
        listbox.deselectAll();
        Assert.assertNull(listbox.getFirstSelectedOption());
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("deselectAllNonMulti")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "constructorWithElement", expectedExceptions = WebException.class)
    public void deselectAllNonMulti() {
        Listbox listbox = driver.findListbox(By.id("singleSelect"));
        listbox.deselectAll();
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("select via page factory negative scenario")
    @Test(groups = { "regression", "interfaces", "listbox", "mustard" }, dependsOnMethods = "deselectByVisibleText", expectedExceptions = NoSuchElementException.class)
    public void negativePageFactoryTest() {
        ElementFactory.initElements(driver, this);
        badSelect.select("Sports");
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("select via page factory")
    @Test(groups = { "regression", "interfaces", "listbox", "mustard" }, dependsOnMethods = "deselectByVisibleText")
    public void pageFactoryTest() {
        ElementFactory.initElements(driver, this);
        listbox.select("Sports");
        Assert.assertTrue(listbox.getFirstSelectedOption().getText().equals("Sports"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("Unordered List")
    @Test(groups = { "regression", "interfaces", "listbox", "mustard" }, dependsOnMethods = "pageFactoryTest")
    public void unorderedList() {
        Button listButton = driver.findButton(By.id("menu1"));
        listButton.click();

        Listbox listbox = driver.findListbox(By.className("dropdown-menu"));
        // listbox.syncVisible();
        listbox.select("HTML");
        Assert.assertTrue(listButton.getText().equals("HTML"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("Override Option Tag")
    @Test(groups = { "regression", "interfaces", "listbox", "mustard" }, dependsOnMethods = "unorderedList")
    public void overrideOptionTag() {
        Button listButton = driver.findButton(By.id("menu1"));
        listButton.click();

        Listbox listbox = driver.findListbox(By.className("dropdown-menu"));
        listbox.syncVisible();
        listbox.overrideOptionTag("a");
        listbox.select("Mercury");
        Assert.assertTrue(listButton.getText().equals("Mercury"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("Override Option Tag")
    @Test(groups = { "regression", "interfaces", "listbox", "mustard" }, dependsOnMethods = "overrideOptionTag")
    public void overrideClickableTag() {
        Button listButton = driver.findButton(By.id("menu1"));
        listButton.click();

        Listbox listbox = driver.findListbox(By.className("dropdown-menu"));
        listbox.syncVisible();
        listbox.overrideClickableTag("a");
        listbox.select("Jupiter");
        Assert.assertTrue(listButton.getText().equals("Jupiter"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("Override Option TagNull")
    @Test(groups = { "regression", "interfaces", "listbox", "mustard" }, dependsOnMethods = "constructorWithElement", expectedExceptions = WebException.class)
    public void overrideOptionTagNull() {
        Listbox listbox = driver.findListbox(By.className("dropdown-menu"));
        listbox.overrideOptionTag("");
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("Override Option Tag Null")
    @Test(groups = { "regression", "interfaces", "listbox", "mustard" }, dependsOnMethods = "constructorWithElement", expectedExceptions = WebException.class)
    public void overrideClickableTagNull() {
        Listbox listbox = driver.findListbox(By.className("dropdown-menu"));
        listbox.overrideClickableTag("");
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("getOptionValues")
    @Test(groups = { "regression", "interfaces", "listbox", "mustard" }, dependsOnMethods = "constructorWithElement")
    public void getOptionValues() {
        String options = driver.findListbox(By.id("singleSelect")).getOptionValues().stream().collect(Collectors.joining(" "));
        Assert.assertTrue(options.contains("Other"));
        Assert.assertTrue(options.contains("Music"));
        Assert.assertTrue(options.contains("Movie"));
        Assert.assertTrue(options.contains("Science"));
        Assert.assertTrue(options.contains("Sports"));
        Assert.assertTrue(options.contains("Politics"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("getOptionTextValues")
    @Test(groups = { "regression", "interfaces", "listbox", "mustard" }, dependsOnMethods = "constructorWithElement")
    public void getOptionTextValues() {
        String options = driver.findListbox(By.id("singleSelect")).getOptionTextValues().stream().collect(Collectors.joining(" "));
        Assert.assertTrue(options.contains("Other"));
        Assert.assertTrue(options.contains("Music"));
        Assert.assertTrue(options.contains("Movie"));
        Assert.assertTrue(options.contains("Science"));
        Assert.assertTrue(options.contains("Sports"));
        Assert.assertTrue(options.contains("Politics"));
    }

}

package com.orasi;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orasi.ui.by.FindByWindows;
import com.orasi.utils.TestReporter;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Button;
import com.orasi.web.webelements.Textbox;
import com.orasi.web.webelements.impl.internal.ElementFactory;

public class Sandbox {
    @FindBy(name = "Plus")
    public Button plus;

    @FindByWindows(accessibilityId = "equalButton")
    public Button equals;

    @FindByWindows(accessibilityId = "CalculatorResults")
    public Textbox results;

    public By two = By.name("Two");

    @BeforeMethod
    public void setup() {
        TestReporter.setDebugLevel(1);
        DriverOptionsManager options = new DriverOptionsManager();
        options.setCapability(DriverType.WINDOWS, "app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        DriverManagerFactory.getManager(DriverType.WINDOWS, options).initalizeDriver();
    }

    @AfterMethod
    public void teardown() {
        DriverManager.quitDriver();
    }

    @Test
    public void simpleCalculator() {
        OrasiDriver driver = DriverManager.getDriver();
        ElementFactory.initElements(driver, this);

        Button one = driver.findButton(By.name("One"));
        one.syncVisible();
        one.click();
        plus.click();
        driver.findButton(two).click();
        equals.click();

        TestReporter.assertTrue("3".equals(results.getText().replace("Display is", "").trim()), "Validate Calculator results is 3 as expected");
        results.syncTextInElement("3"); // redundant but testing other syncs
        TestReporter.logScreenshot(driver.getWebDriver(), "WinAppTest");
    }
}
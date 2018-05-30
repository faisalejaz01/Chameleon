package com.chameleon.web;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.chameleon.AutomationException;
import com.chameleon.DriverManager;
import com.chameleon.DriverManagerFactory;
import com.chameleon.DriverOptionsManager;
import com.chameleon.DriverType;
import com.chameleon.utils.Base64Coder;
import com.chameleon.utils.Constants;
import com.chameleon.utils.Sleeper;
import com.chameleon.utils.exception.KeyExistsException;
import com.chameleon.utils.exception.NoKeyFoundException;
import com.chameleon.web.by.angular.ByNG;
import com.chameleon.web.webelements.Button;
import com.chameleon.web.webelements.Checkbox;
import com.chameleon.web.webelements.Element;
import com.chameleon.web.webelements.Label;
import com.chameleon.web.webelements.Link;
import com.chameleon.web.webelements.Listbox;
import com.chameleon.web.webelements.RadioGroup;
import com.chameleon.web.webelements.Textbox;
import com.chameleon.web.webelements.Webtable;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.saucerest.SauceREST;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class TestExtendedWebDriver extends WebBaseTest {

    protected ResourceBundle appURLRepository = ResourceBundle.getBundle(Constants.ENVIRONMENT_URL_PATH);
    protected SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(
            Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_USERNAME")),
            Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_KEY")));
    DesiredCapabilities caps = null;
    ExtendedWebDriver driver = null;
    File file = null;
    String runLocation = "";
    String browserUnderTest = "";
    String browserVersion = "";
    String operatingSystem = "";
    String environment = "";

    @BeforeClass(groups = { "regression", "utils", "ExtendedWebdriver" })
    @Parameters({ "runLocation", "browserUnderTest", "browserVersion", "operatingSystem", "environment" })
    public void setup(@Optional String runLocation, String browserUnderTest, String browserVersion,
            String operatingSystem, String environment) {
        // setReportToMustard(false);

        if (browserUnderTest.equalsIgnoreCase("jenkinsParameter")) {
            this.browserUnderTest = System.getProperty("jenkinsBrowser").trim();
        } else {
            this.browserUnderTest = browserUnderTest;
        }

        if (browserVersion.equalsIgnoreCase("jenkinsParameter")) {
            browserVersion = System.getProperty("jenkinsBrowserVersion").trim();
        } else {
            this.browserVersion = browserVersion;
        }

        if (operatingSystem.equalsIgnoreCase("jenkinsParameter")) {
            operatingSystem = System.getProperty("jenkinsOperatingSystem").trim();
        } else {
            this.operatingSystem = operatingSystem;
        }

        if (runLocation.equalsIgnoreCase("jenkinsParameter")) {
            runLocation = System.getProperty("jenkinsRunLocation").trim();
        } else {
            this.runLocation = runLocation;
        }

        this.environment = environment;
        caps = new DesiredCapabilities();
        caps.setCapability("ignoreZoomSetting", true);
        caps.setCapability(CapabilityType.BROWSER_NAME, this.browserUnderTest.equalsIgnoreCase("html") ? "firefox" : this.browserUnderTest);
        caps.setCapability(CapabilityType.VERSION, browserVersion);
        caps.setCapability(CapabilityType.PLATFORM, operatingSystem);
        caps.setCapability("enablePersistentHover", false);
        caps.setCapability("name", "TestExtendedWebDriver");
        setRunLocation(runLocation);
        if (runLocation.toLowerCase().equals("local")) {

            if (DriverType.HTML.equals(DriverType.fromString(this.browserUnderTest))) {
                DriverOptionsManager options = new DriverOptionsManager();
                options.getFirefoxOptions().setHeadless(true);
                setBrowserUnderTest("firefox");
                DriverManagerFactory.getManager(DriverType.fromString("firefox"), options).initalizeDriver();
            } else {
                DriverManagerFactory.getManager(DriverType.fromString(this.browserUnderTest)).initalizeDriver();
            }
            driver = DriverManager.getDriver();
        } else {
            try {
                DriverManagerFactory.getManager(DriverType.fromString(browserUnderTest)).initalizeDriver(new URL(getRemoteURL()));
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            driver = DriverManager.getDriver();
        }

        driver.get("http://orasi.github.io/Chameleon/sites/unitTests/orasi/core/interfaces/testsite.html");

    }

    @Override
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResults) {
    }

    private void endSauceTest(int result) {
        Map<String, Object> updates = new HashMap<String, Object>();
        updates.put("name", "TestExtendedWebDriver");

        if (result == ITestResult.FAILURE) {
            updates.put("passed", false);
        } else {
            updates.put("passed", true);
        }

        SauceREST client = new SauceREST(authentication.getUsername(), authentication.getAccessKey());
        client.updateJobInfo(driver.getSessionId().toString(), updates);

    }

    @AfterClass(groups = { "regression", "utils", "ExtendedWebdriver" })
    public void close(ITestContext testResults) {
        if (runLocation.equalsIgnoreCase("sauce")) {
            if (testResults.getFailedTests().size() == 0) {
                endSauceTest(ITestResult.SUCCESS);
            } else {
                endSauceTest(ITestResult.FAILURE);
            }
        }
        driver.quit();
        DriverManager.quitDriver();
        DriverManager.stopService();
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("setExtendedWebDriver")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" })
    public void setExtendedWebDriver() {
        ExtendedWebDriver ExtendedWebDriver = new ExtendedWebDriver();
        ExtendedWebDriver.setDriver(ExtendedWebDriver);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("getPageTimeout")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" })
    public void getPageTimeout() {
        Assert.assertTrue(driver.getPageTimeout() == Constants.PAGE_TIMEOUT);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("setPageTimeout")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "getPageTimeout")
    public void setPageTimeout() {
        if (this.browserUnderTest.toLowerCase().contains("safari") || driver.toString().contains("safari")) {
            throw new SkipException("Test not valid for SafariDriver");
        }
        driver.setPageTimeout(15);
        Assert.assertTrue(driver.getPageTimeout() == 15);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("getElementTimeout")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" })
    public void getElementTimeout() {
        Assert.assertTrue(driver.getElementTimeout() == Constants.ELEMENT_TIMEOUT);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("setElementTimeout")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "getElementTimeout")
    public void setElementTimeout() {
        driver.setElementTimeout(15);
        Assert.assertTrue(driver.getElementTimeout() == 15);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("getScriptTimeout")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "executeJavaScript")
    public void getScriptTimeout() {
        Assert.assertTrue(driver.getScriptTimeout() == Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("setScriptTimeout")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "getScriptTimeout")
    public void setScriptTimeout() {
        driver.setScriptTimeout(15);
        Assert.assertTrue(driver.getScriptTimeout() == 15);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("getDriver")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "setScriptTimeout")
    public void testGetDriver() {
        Assert.assertNotNull(driver.getWebDriver());
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("executeJavaScript")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" })
    public void executeJavaScript() {
        driver.findTextbox(By.id("FirstName")).elementWired();

        WebElement element = (WebElement) driver.executeJavaScript("return document.getElementById('FirstName')");
        Assert.assertNotNull(element);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findElements")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "testGetDriver")
    public void findElements() {
        List<Element> elements = driver.findElements(By.tagName("input"));
        Assert.assertTrue(elements.size() > 0);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findWebElements")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findElements")
    public void findWebElements() {
        List<WebElement> elements = driver.findWebElements(By.tagName("input"));
        Assert.assertTrue(elements.size() > 0);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findTextboxes")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findElements")
    public void findTextboxes() {
        List<Textbox> elements = driver.findTextboxes(By.tagName("input"));
        Assert.assertTrue(elements.size() > 0);
        Assert.assertTrue(elements.get(0) instanceof Textbox);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findButton")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "testGetDriver")
    public void findButton() {
        Button button = driver.findButton(By.id("Add"));
        Assert.assertNotNull(button);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findButtons")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findElements")
    public void findButtons() {
        List<Button> elements = driver.findButtons(By.tagName("input"));
        Assert.assertTrue(elements.size() > 0);
        Assert.assertTrue(elements.get(0) instanceof Button);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findCheckboxes")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findElements")
    public void findCheckboxes() {
        List<Checkbox> elements = driver.findCheckboxes(By.tagName("input"));
        Assert.assertTrue(elements.size() > 0);
        Assert.assertTrue(elements.get(0) instanceof Checkbox);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findLabels")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findElements")
    public void findLabels() {
        List<Label> elements = driver.findLabels(By.tagName("input"));
        Assert.assertTrue(elements.size() > 0);
        Assert.assertTrue(elements.get(0) instanceof Label);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findLinks")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findElements")
    public void findLinks() {
        List<Link> elements = driver.findLinks(By.tagName("input"));
        Assert.assertTrue(elements.size() > 0);
        Assert.assertTrue(elements.get(0) instanceof Link);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findElement")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findWebElements")
    public void findElement() {
        Element element = driver.findElement(By.id("FirstName"));
        Assert.assertNotNull(element);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findListbox")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findElement")
    public void findListbox() {
        driver.setElementTimeout(3);
        Listbox listbox = driver.findListbox(By.id("Category"));
        Assert.assertNotNull(listbox);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findTextbox")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findListbox")
    public void findTextbox() {
        Textbox textbox = driver.findTextbox(By.id("FirstName"));
        Assert.assertNotNull(textbox);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findWebtable")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findTextbox")
    public void findWebtable() {
        Webtable webtable = driver.findWebtable(By.id("VIPs"));
        Assert.assertNotNull(webtable);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findRadioGroup")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findWebtable")
    public void findRadioGroup() {
        driver.get("http://orasi.github.io/Chameleon/sites/unitTests/orasi/core/interfaces/radioGroup.html");
        RadioGroup radioGroup = driver.findRadioGroup(By.id("Content"));
        Assert.assertNotNull(radioGroup);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findCheckbox")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findRadioGroup")
    public void findCheckbox() {
        driver.get("http://orasi.github.io/Chameleon/sites/unitTests/orasi/core/interfaces/checkbox.html");
        Checkbox checkbox = driver.findCheckbox(By.name("checkbox"));
        Assert.assertNotNull(checkbox);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findLabel")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findCheckbox")
    public void findLabel() {
        driver.get("http://orasi.github.io/Chameleon/sites/unitTests/orasi/core/interfaces/label.html");
        Label label = driver.findLabel(By.xpath("//*[@id='radioForm']/label[1]"));
        Assert.assertNotNull(label);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findLink")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findLabel")
    public void findLink() {
        driver.get("http://orasi.github.io/Chameleon/sites/unitTests/orasi/core/interfaces/link.html");
        Link link = driver.findLink(By.xpath("//a[@href='testLinks.html']"));
        Assert.assertNotNull(link);
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("executeAsyncJavaScript")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findLink")
    public void executeAsyncJavaScript() {

        driver.get("http://cafetownsend-angular-rails.herokuapp.com/login");
        Sleeper.sleep(3000);
        driver.executeAsyncJavaScript(
                "var callback = arguments[arguments.length - 1];angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);");
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("getCurrentUrl")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "executeAsyncJavaScript")
    public void getCurrentUrl() {
        Assert.assertTrue(driver.getCurrentUrl().contains("cafetownsend-angular-rails.herokuapp.com"));
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("getTitle")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "executeAsyncJavaScript")
    public void getTitle() {
        Assert.assertTrue(driver.getTitle().contains("CafeTownsend-AngularJS-Rails"));
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findNGModel")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "executeAsyncJavaScript")
    public void findNGModel() {
        Assert.assertNotNull(driver.findTextbox(ByNG.model("user.name")));
        driver.findTextbox(ByNG.model("user.name")).set("Luke");
        driver.findTextbox(ByNG.model("user.password")).set("Skywalker");
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findNGController")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findNGModel")
    public void findNGController() {
        Assert.assertNotNull(driver.findElement(ByNG.controller("HeaderController")));
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("findNGRepeater")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findNGController")
    public void findNGRepeater() {
        Sleeper.sleep(2000);
        Assert.assertNotNull(driver.findElement(ByNG.repeater("employee in employees")));
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("getDataWarehouse")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" })
    public void getDataWarehouse() {
        Assert.assertNotNull(driver.data());
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("addData")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "getDataWarehouse")
    public void addData() {
        driver.data().add("username", "Admin");
        Assert.assertNotNull(driver.data().get("username"));
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("getValidData")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "addData")
    public void getValidData() {
        Assert.assertNotNull(driver.data().get("username"));
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("getInvalidData")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, expectedExceptions = NoKeyFoundException.class)
    public void getInvalidData() {
        driver.data().get("password");
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("addToExistingKey")
    @Test(groups = { "regression", "utils",
            "ExtendedWebdriver" }, dependsOnMethods = "addData", expectedExceptions = KeyExistsException.class)
    public void addToExistingKey() {
        driver.data().add("username", "Employee");
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("updateExistingKey")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "addData")
    public void updateExistingKey() {
        driver.data().update("username", "Manager");
        Assert.assertEquals("Manager", driver.data().get("username"));
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("updateNoKeyFound")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, expectedExceptions = NoKeyFoundException.class)
    public void updateNoKeyFound() {
        driver.data().update("password", "ExtendedWeb123");
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("isAngularComplete")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "findNGRepeater")
    public void isAngularComplete() {
        DriverManager.setDriver(driver);
        Assert.assertNotNull(driver.page().isAngularComplete());
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("isDomComplete")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "isAngularComplete")
    public void isDomComplete() {
        DriverManager.setDriver(driver);
        Assert.assertNotNull(driver.page().isDomComplete());
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("isDomCompleteWithTimeout")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "isDomComplete")
    public void isDomCompleteWithTimeout() {
        DriverManager.setDriver(driver);
        Assert.assertNotNull(driver.page().isDomComplete(3));
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("isDomInteractive")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "isDomCompleteWithTimeout")
    public void isDomInteractive() {
        DriverManager.setDriver(driver);
        Assert.assertNotNull(driver.page().isDomInteractive());
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("isDomInteractiveWithTimeout")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "isDomInteractive")
    public void isDomInteractiveWithTimeout() {
        DriverManager.setDriver(driver);
        Assert.assertNotNull(driver.page().isDomInteractive(3));
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("isJQueryComplete")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "isDomInteractiveWithTimeout")
    public void isJQueryComplete() {
        driver.get("http://www.kariyer.net/");
        DriverManager.setDriver(driver);
        Assert.assertNotNull(driver.page().isJQueryComplete());
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("isJQueryCompleteWithTimeout")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "isDomInteractiveWithTimeout")
    public void isJQueryCompleteWithTimeout() {
        driver.get("http://www.kariyer.net/");
        DriverManager.setDriver(driver);
        driver.page().isDomComplete();
        Assert.assertNotNull(driver.page().isJQueryComplete(15));
    }

    @Features("Utilities")
    @Stories("ExtendedWebDriver")
    @Title("isJQueryCompleteOnNonJQueryPage")
    @Test(groups = { "regression", "utils", "ExtendedWebdriver" }, dependsOnMethods = "isJQueryComplete", expectedExceptions = AutomationException.class)
    public void isJQueryCompleteOnNonJQueryPage() {
        driver.get("http://www.google.com/");
        DriverManager.setDriver(driver);
        driver.page().isDomComplete();
        Assert.assertNotNull(driver.page().isJQueryComplete());
    }

}

package com.chameleon.web;

import static com.chameleon.utils.TestReporter.logTrace;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertHandler {
    private static final String ALERT_FOUND = "Alert was found, handling Alert";
    private static final String ALERT_NOT_FOUND = "No Alert was found, nothing to handle";

    public static boolean isAlertPresent(WebDriver driver, int timeout) {
        logTrace("Entering AlertHandler#isAlertPresent");
        try {
            logTrace("Waiting for [ " + timeout + " ] seconds for an Alert to appear");
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.alertIsPresent());
            logTrace("Alert found");
            logTrace("Exiting AlertHandler#isAlertPresent");
            return true;
        } catch (Exception e) {
            logTrace("No Alert found");
            logTrace("Exiting AlertHandler#isAlertPresent");
            return false;
        }
    }

    public static void handleAllAlerts(WebDriver driver, int timeout) {
        logTrace("Entering AlertHandl"
                + "er#handleAllAlerts");
        int alert = 0;
        while (isAlertPresent(driver, timeout)) {
            logTrace("Handling Alert [ " + alert + 1 + " ] ");
            alertHandler(driver);
        }
        logTrace("Exiting AlertHandler#handleAllAlerts");
    }

    public static void handleAlert(WebDriver driver, int timeout) {
        logTrace("Entering AlertHandler#handleAlert(driver, timeout)");
        if (isAlertPresent(driver, timeout)) {
            logTrace(ALERT_FOUND);
            alertHandler(driver);
        } else {
            logTrace(ALERT_NOT_FOUND);
        }
        logTrace("Exiting AlertHandler#handleAlert(driver, timeout)");
    }

    public static void handleAlert(WebDriver driver, int timeout, String inputText) {
        logTrace("Entering AlertHandler#handleAlert(driver, timeout, inputText)");
        if (isAlertPresent(driver, timeout)) {
            logTrace(ALERT_FOUND);
            alertHandler(driver, inputText);
        } else {
            logTrace(ALERT_NOT_FOUND);
        }
        logTrace("Exiting AlertHandler#handleAlert(driver, timeout, inputText)");
    }

    public static void handleAlert(WebDriver driver, int timeout, Credentials credentials) {
        logTrace("Entering AlertHandler#handleAlert(driver, timeout, credentials)");
        if (isAlertPresent(driver, timeout)) {
            logTrace(ALERT_FOUND);
            alertHandler(driver, credentials);
        } else {
            logTrace(ALERT_NOT_FOUND);
        }
        logTrace("Exiting AlertHandler#handleAlert(driver, timeout, credentials)");
    }

    private static void alertHandler(WebDriver driver) {
        logTrace("Entering AlertHandler#alertHandler(driver)");
        try {
            Alert alert = driver.switchTo().alert();
            logTrace("Closing alert popup with text [ " + alert.getText() + " ]");
            alert.accept();
        } catch (Exception throwAway) {
        }
        logTrace("Exiting AlertHandler#alertHandler(driver)");
    }

    private static void alertHandler(WebDriver driver, String inputText) {
        logTrace("Entering AlertHandler#alertHandler(driver, inputText)");
        try {
            Alert alert = driver.switchTo().alert();
            logTrace("Sending text [ " + inputText + " ] to Alert popup");
            alert.sendKeys(inputText);
            alertHandler(driver);
        } catch (Exception throwAway) {
        }
        logTrace("Exiting AlertHandler#alertHandler(driver, inputText)");
    }

    private static void alertHandler(WebDriver driver, Credentials credentials) {
        logTrace("Entering AlertHandler#alertHandler(driver, credentials)");
        try {
            Alert alert = driver.switchTo().alert();
            logTrace("Closing alert popup with text [ " + alert.getText() + " ] with authentication user");
            alert.authenticateUsing(credentials);
        } catch (Exception throwAway) {
        }
        logTrace("Exiting AlertHandler#alertHandler(driver, credentials)");
    }
}

package com.salesforce;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.orasi.DriverManager;
import com.orasi.DriverManagerFactory;
import com.orasi.DriverType;
import com.orasi.web.OrasiDriver;
import com.salesforce.api.rest.manager.SalesforceObjectManager;

public class Sandbox {
    SalesforceObjectManager salesforce = new SalesforceObjectManager();
    By txtUser = By.id("username");
    By txtPass = By.id("password");
    By btnLogin = By.id("Login");
    By btnDelete = By.name("delete");
    By lblError = By.id("errorTitle");
    By lblName = By.className("topName");

    @BeforeSuite
    public void setup() {
        salesforce.accounts().createDefaultAccount();
    }

    @Test
    public void test() {
        DriverManagerFactory.getManager(DriverType.CHROME).initalizeDriver();
        OrasiDriver driver = DriverManager.getDriver();
        driver.debug().setHighlightOnSync(true);

        driver.get("https://na59.salesforce.com/" + salesforce.accounts().getAccount().getId());
        driver.findTextbox(txtUser).syncEnabled();
        driver.findTextbox(txtUser).set("justin.phlegar@orasi.com");
        driver.findTextbox(txtPass).set("roottoor85");
        driver.findButton(btnLogin).jsClick();

        driver.page().isDomComplete();
        driver.findWebtable(By.id("bodyTable")).syncVisible();
        driver.findLabel(lblName).syncTextInElement(salesforce.accounts().getAccount().getName(), 15);

        salesforce.accounts().deleteAccount(salesforce.accounts().getAccount());
        driver.navigate().refresh();

        driver.findLabel(lblError).syncTextInElement("Record deleted", 15);

    }

    @AfterMethod
    public void cleanup() {
        salesforce.accounts().deleteAllAccounts();
        DriverManager.quitDriver();
    }
}

package com.salesforce;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orasi.DriverManager;
import com.orasi.DriverManagerFactory;
import com.orasi.DriverType;
import com.orasi.web.OrasiDriver;
import com.salesforce.api.domain.Account;
import com.salesforce.api.rest.manager.SalesforceObjectManager;

public class Sandbox {
    By txtUser = By.id("username");
    By txtPass = By.id("password");
    By btnLogin = By.id("Login");
    By btnDelete = By.name("delete");
    By lblError = By.id("errorTitle");
    By lblName = By.className("topName");

    @DataProvider(parallel = true)
    public Object[][] dp() {
        return new Object[][] { { "account1" }, { "account2" } };
    }

    @Test(dataProvider = "dp")
    public void test(String name) {
        Account account = new Account();
        account.setName(name);
        account.setDescription("This account was created via REST automation");
        account.setPhone("3363363366");

        SalesforceObjectManager.getInstance().accounts().createAccount(account);

        DriverManagerFactory.getManager(DriverType.CHROME).initalizeDriver();
        OrasiDriver driver = DriverManager.getDriver();
        driver.debug().setHighlightOnSync(true);

        driver.get("https://na59.salesforce.com/" + SalesforceObjectManager.getInstance().accounts().getAccount().getId());
        driver.findTextbox(txtUser).syncEnabled();
        driver.findTextbox(txtUser).set("justin.phlegar@orasi.com");
        driver.findTextbox(txtPass).set("roottoor85");
        driver.findButton(btnLogin).jsClick();

        driver.page().isDomComplete();
        driver.findWebtable(By.id("bodyTable")).syncVisible();
        driver.findLabel(lblName).syncTextInElement(SalesforceObjectManager.getInstance().accounts().getAccount().getName(), 15);

        SalesforceObjectManager.getInstance().accounts().deleteAccount(SalesforceObjectManager.getInstance().accounts().getAccount());
        driver.navigate().refresh();

        driver.findLabel(lblError).syncTextInElement("Record deleted", 15);

    }

    @AfterMethod
    public void cleanup() {
        SalesforceObjectManager.getInstance().accounts().deleteAllAccounts();
        DriverManager.quitDriver();
    }
}

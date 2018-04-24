package com.salesforce;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orasi.DriverManager;
import com.orasi.DriverManagerFactory;
import com.orasi.DriverType;
import com.orasi.api.restServices.RestResponse;
import com.orasi.web.OrasiDriver;
import com.salesforce.api.domain.Account;
import com.salesforce.api.rest.SalesforceRest;
import com.salesforce.api.rest.authentication.SFResponse;

public class Sandbox {
    Account createdAccount;
    By txtUser = By.id("username");
    By txtPass = By.id("password");
    By btnLogin = By.id("Login");
    By btnDelete = By.name("delete");
    By lblError = By.id("errorTitle");
    By lblName = By.className("topName");

    @BeforeMethod
    public void setup() {
        Account account = new Account();
        account.setName("REST API Test");
        account.setDescription("This account was created via REST automation");
        account.setPhone("3363363366");
        RestResponse createResponse = SalesforceRest.accounts().createAccount(account);

        SFResponse create = createResponse.mapJSONToObject(SFResponse.class);

        RestResponse getResponse = SalesforceRest.accounts().getAccount(create.getId());
        createdAccount = getResponse.mapJSONToObject(Account.class);
    }

    @Test
    public void test() {
        DriverManagerFactory.getManager(DriverType.CHROME).initalizeDriver();
        OrasiDriver driver = DriverManager.getDriver();
        driver.debug().setHighlightOnSync(true);

        driver.get("https://na59.salesforce.com/" + createdAccount.getId());
        driver.findTextbox(txtUser).syncEnabled();
        driver.findTextbox(txtUser).set("justin.phlegar@orasi.com");
        driver.findTextbox(txtPass).set("roottoor85");
        driver.findButton(btnLogin).jsClick();

        driver.page().isDomComplete();
        driver.findWebtable(By.id("bodyTable")).syncVisible();
        driver.findLabel(lblName).syncTextInElement(createdAccount.getName(), 15);

        SalesforceRest.accounts().deleteAccount(createdAccount);
        driver.navigate().refresh();

        driver.findLabel(lblError).syncTextInElement("Record deleted", 15);

    }

    @AfterMethod
    public void cleanup() {
        SalesforceRest.accounts().deleteAccount(createdAccount);
        DriverManager.quitDriver();
    }
}

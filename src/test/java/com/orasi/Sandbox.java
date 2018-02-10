package com.orasi;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orasi.web.OrasiDriver;
import com.orasi.web.WebBaseTest;
import static com.orasi.web.webelements.SyncType.*;

public class Sandbox  {

    @BeforeMethod
    public void setup() {
        DriverManagerFactory.getManager(DriverType.FIREFOX).initalizeDriver();
    }

    @Test
    public void getAllSelectedOptions() {
        launchSite();
        searchForOrasi();
    }

    public void launchSite() {
        DriverManager.getDriver().get("http://google.com");
    }

    public void searchForOrasi() {
        // Create local instance of OrasiDriver for easier usage
        OrasiDriver driver = DriverManager.getDriver();
        driver.findTextbox(By.xpath("//input[@title='Search']")).sync(ENABLED).set("Orasi");
        driver.findButton(By.name("btnK")).sync(ENABLED).click();
        driver.findLink(By.partialLinkText("Orasi Software, Inc.")).sync(VISIBLE);
    }

    @AfterMethod
    public void afterTest() {
        DriverManager.quitDriver();
    }

}
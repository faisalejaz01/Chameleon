package com.orasi;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Sandbox {

    @Test
    public void getAllSelectedOptions() {
        DriverManagerFactory.getManager(DriverType.CHROME).initalizeDriver();
        DriverManager.getDriver().get("http://www.kariyer.net/");
        // System.out.println(PageLoaded.isJQueryComplete(2));
    }

    @AfterMethod
    public void afterTest() {
        DriverManager.quitDriver();
        DriverManager.stopService();
    }

}
package com.orasi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.orasi.utils.io.FileLoader;
import com.orasi.web.OrasiDriver;
import com.orasi.web.WebBaseTest;

public class Sandbox extends WebBaseTest {

    // @BeforeMethod
    public void setup() {
        DriverManagerFactory.getManager(DriverType.FIREFOX).initalizeDriver();
    }

    @Test
    public void getAllSelectedOptions() {
        File tempDir = new File("/tmp");
        tempDir.mkdir();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/tmp/emails.txt"));) {
            writer.write("adam.thomas@orasi.com");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String emails = "";
        try {
            emails = FileLoader.loadFileFromProjectAsString("/tmp/emails.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(emails);
    }

    public void launchSite() {
        DriverManager.getDriver().get("http://google.com");
    }

    public void searchForOrasi() {
        // Create local instance of OrasiDriver for easier usage
        OrasiDriver driver = DriverManager.getDriver();
        driver.findTextbox(By.xpath("//input[@title='Search']")).set("Orasi");
        driver.findButton(By.name("btnK")).click();
        driver.findLink(By.partialLinkText("Orasi Software, Inc.")).syncVisible();
    }

    @AfterMethod
    public void afterTest() {
        DriverManager.quitDriver();
    }

}
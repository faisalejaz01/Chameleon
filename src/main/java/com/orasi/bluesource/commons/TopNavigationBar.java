package com.orasi.bluesource.commons;

import org.openqa.selenium.support.FindBy;

import com.orasi.DriverManager;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Link;
import com.orasi.web.webelements.impl.internal.ElementFactory;

import ru.yandex.qatools.allure.annotations.Step;

public class TopNavigationBar {
    private OrasiDriver driver = null;

    // All the page elements:
    @FindBy(xpath = "//a[text() = 'Logout']")
    private Link lnkLogout;

    @FindBy(xpath = "//a[text() = 'Directory']")
    private Link lnkDirectory;

    @FindBy(xpath = "//a[text() = 'Projects']")
    private Link lnkProjects;

    @FindBy(xpath = "//a[text() = 'Employees']")
    private Link lnkEmployees;

    @FindBy(xpath = "//a[text() = 'Admin ']")
    private Link lnkAdminDrop;

    @FindBy(css = "a[href = '/admin/departments']")
    private Link lnkDept;

    @FindBy(css = "a[href = '/admin/titles']")
    private Link lnkTitle;

    // *********************
    // ** Build page area **
    // *********************
    public TopNavigationBar() {
        this.driver = DriverManager.getDriver();
        ElementFactory.initElements(driver, this);
    }

    public boolean pageLoaded() {
        return driver.page().isElementLoaded(this.getClass(), driver, lnkLogout);
    }
    // *****************************************
    // ***Page Interactions ***
    // *****************************************

    @Step("Click the \"Admin Link\"")
    public void clickAdminLink() {
        lnkAdminDrop.click();
    }

    @Step("Navigate to the \"Departments Page\"")
    public void clickDepartmentsLink() {
        lnkDept.click();
    }

    @Step("Navigate to the \"Titles Page\"")
    public void clickTitlesLink() {
        lnkTitle.click();
    }

    @Step("Navigate to the \"Directory Page\"")
    public void clickDirectoryLink() {
        lnkDirectory.click();
    }

    @Step("Navigate to the \"Projects Page\"")
    public void clickProjectsLink() {
        lnkProjects.click();
    }

    @Step("Navigate to the \"Employees Page\"")
    public void clickEmployeesLink() {
        lnkEmployees.click();
    }

    // Verify logout link is displayed
    @Step("Verify user is logged in successfully")
    public boolean isLoggedIn() {
        return lnkLogout.syncVisible();
    }

    // Click logout
    @Step("Logout of application")
    public void clickLogout() {
        lnkLogout.click();
    }
}

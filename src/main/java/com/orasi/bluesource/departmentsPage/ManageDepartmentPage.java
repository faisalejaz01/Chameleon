package com.orasi.bluesource.departmentsPage;

import org.openqa.selenium.support.FindBy;

import com.orasi.DriverManager;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Button;
import com.orasi.web.webelements.Listbox;
import com.orasi.web.webelements.Textbox;
import com.orasi.web.webelements.impl.internal.ElementFactory;

import ru.yandex.qatools.allure.annotations.Step;

public class ManageDepartmentPage {

    private OrasiDriver driver = null;

    // All the page elements
    @FindBy(id = "department_name")
    private Textbox txtDept;
    @FindBy(xpath = "//select[@id='department_department_id']")
    private Listbox lstParentDept;
    @FindBy(id = "department_minimum_hour_increment")
    private Listbox lstHourIncrement;
    @FindBy(name = "commit")
    private Button btnCreateDept;

    // *********************
    // ** Build page area **
    // *********************
    public ManageDepartmentPage() {
        this.driver = DriverManager.getDriver();
        ElementFactory.initElements(driver, this);
    }

    public boolean pageLoaded() {
        return driver.page().isElementLoaded(this.getClass(), driver, btnCreateDept);
    }

    // *****************************************
    // ***Page Interactions ***
    // *****************************************

    // method to create a new title
    @Step("Create the new department \"{0}\" using the default increment hours")
    public void createDepartment(String departmentName) {
        manageDepartment(departmentName, "", "");
    }

    @Step("Create the new department \"{0}\" with increment hours of \"{1}\"")
    public void createDepartment(String departmentName, String incrementHours) {
        manageDepartment(departmentName, "", incrementHours);
    }

    @Step("Create the new subdepartment \"{0}\" under department \"{1}\"")
    public void createSubdepartment(String departmentName, String parentDepartment) {
        manageDepartment(departmentName, parentDepartment, "");
    }

    @Step("Modify the new department name to \"{0}\"")
    public void modifyDepartmentName(String departmentName) {
        manageDepartment(departmentName, "", "");
    }

    @Step("Modify the new departments parent to \"{0}\"")
    public void modifyDepartmentsParent(String parentDepartment) {
        manageDepartment("", parentDepartment, "");
    }

    @Step("The parent department \"{0}\" was saved")
    public boolean validateCorrectParentDepartment(String parentDepartment) {
        if (lstParentDept.getFirstSelectedOption().getText().trim().equalsIgnoreCase(parentDepartment))
            return true;
        return false;
    }

    @Step("Modify the new departments increment hours to \"{0}\"")
    public void modifyDepartmentsIncrementHours(String incrementHours) {
        manageDepartment("", "", incrementHours);
    }

    @Step("The increment hours \"{0}\" was saved")
    public boolean validateCorrectIncrementHours(String incrementHours) {
        if (lstHourIncrement.getFirstSelectedOption().getText().trim().equalsIgnoreCase(incrementHours))
            return true;
        return false;
    }

    public void clickUpdateButton() {
        btnCreateDept.submit();
    }

    private void manageDepartment(String departmentName, String parentDepartment, String incrementHours) {
        txtDept.set(departmentName);
        lstParentDept.select(parentDepartment);
        lstHourIncrement.select(incrementHours);
        btnCreateDept.submit();
    }
}

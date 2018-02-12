package com.orasi.bluesource.employeesPage;

import org.openqa.selenium.support.FindBy;

import com.orasi.DriverManager;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Button;
import com.orasi.web.webelements.Label;
import com.orasi.web.webelements.Listbox;
import com.orasi.web.webelements.Textbox;
import com.orasi.web.webelements.impl.internal.ElementFactory;

import ru.yandex.qatools.allure.annotations.Step;

public class ManageEmployeeModal {

    private OrasiDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    // All the page elements:

    @FindBy(id = "modal_label_1")
    private Label lblAddEmployeePopup;
    @FindBy(id = "employee_username")
    private Textbox txtUsername;
    @FindBy(id = "employee_first_name")
    private Textbox txtFirstName;
    @FindBy(id = "employee_last_name")
    private Textbox txtLastName;
    @FindBy(id = "employee_title_id")
    private Listbox lstTitle;
    @FindBy(id = "employee_role")
    private Listbox lstRole;
    @FindBy(id = "employee_manager_id")
    private Listbox lstManager;
    @FindBy(id = "employee_status")
    private Listbox lstStatus;
    @FindBy(id = "employee_location")
    private Listbox lstLocation;
    @FindBy(id = "employee_start_date")
    private Textbox txtStartDate;
    @FindBy(id = "employee_cell_phone")
    private Textbox txtCellPhone;
    @FindBy(id = "employee_office_phone")
    private Textbox txtOfficePhone;
    @FindBy(id = "employee_email")
    private Textbox txtEmail;
    @FindBy(id = "employee_im_name")
    private Textbox txtImName;
    @FindBy(id = "employee_im_client")
    private Listbox lstImClient;
    @FindBy(id = "employee_department_id")
    private Listbox lstDept;
    @FindBy(name = "commit")
    private Button btnSave;

    // *********************
    // ** Build page area **
    // *********************
    public ManageEmployeeModal() {
        this.driver = DriverManager.getDriver();
        ElementFactory.initElements(driver, this);
    }

    public boolean pageLoaded() {
        return driver.page().isElementLoaded(this.getClass(), txtUsername);
    }

    // *****************************************
    // ***Page Interactions ***
    // *****************************************

    // adds a new employee on the new employee page
    @Step("And I add a new Employee")
    public void addEmployee() {
        lblAddEmployeePopup.syncEnabled();
        Employee employee = (Employee) driver.data().get("employee");
        // Fill in the details
        txtUsername.set(employee.getUsername());
        txtFirstName.set(employee.getFirstName());
        txtLastName.set(employee.getLastName());
        lstTitle.select(employee.getEmployeeTitle());
        lstRole.select(employee.getRole());
        lstManager.select(employee.getManager());
        lstStatus.select(employee.getStatus());
        lstLocation.select(employee.getLocation());
        txtStartDate.jsSet(employee.getStartDate());
        txtCellPhone.set(employee.getCellPhone());
        txtOfficePhone.set(employee.getOfficePhone());
        txtEmail.set(employee.primaryEmail().getEmail());
        txtImName.set(employee.getImName());
        lstImClient.select(employee.getImClient());
        lstDept.select(employee.getDepartment());

        // submit
        btnSave.syncEnabled();
        btnSave.submit();
    }

    @Step("And I modify the Employee Details")
    public void modifyEmployee() {

        Employee employee = (Employee) driver.data().get("employee");
        lblAddEmployeePopup.syncEnabled();

        if (!txtUsername.getText().equalsIgnoreCase(employee.getUsername())) {
            txtUsername.set(employee.getUsername());
        }
        if (!txtFirstName.getText().equalsIgnoreCase(employee.getFirstName())) {
            txtFirstName.set(employee.getFirstName());
        }
        if (!txtLastName.getText().equalsIgnoreCase(employee.getLastName())) {
            txtLastName.set(employee.getLastName());
        }
        if (!lstTitle.getFirstSelectedOption().getText().equalsIgnoreCase(employee.getEmployeeTitle())) {
            lstTitle.select(employee.getEmployeeTitle());
        }
        if (!lstRole.getFirstSelectedOption().getText().equalsIgnoreCase(employee.getRole())) {
            lstRole.select(employee.getRole());
        }
        if (!lstManager.getFirstSelectedOption().getText().equalsIgnoreCase(employee.getManager())) {
            lstManager.select(employee.getManager());
        }
        if (!lstStatus.getFirstSelectedOption().getText().equalsIgnoreCase(employee.getStatus())) {
            lstStatus.select(employee.getStatus());
        }
        if (!lstLocation.getFirstSelectedOption().getText().equalsIgnoreCase(employee.getLocation())) {
            lstLocation.select(employee.getLocation());
        }
        if (!txtStartDate.getText().equalsIgnoreCase(employee.getStartDate())) {
            txtStartDate.safeSet(employee.getStartDate());
        }
        if (!txtCellPhone.getText().equalsIgnoreCase(employee.getCellPhone())) {
            txtCellPhone.set(employee.getCellPhone());
        }
        if (!txtOfficePhone.getText().equalsIgnoreCase(employee.getOfficePhone())) {
            txtOfficePhone.set(employee.getOfficePhone());
        }
        if (!txtEmail.getText().equalsIgnoreCase(employee.primaryEmail().getEmail())) {
            txtEmail.set(employee.primaryEmail().getEmail());
        }
        if (!txtImName.getText().equalsIgnoreCase(employee.getImName())) {
            txtImName.set(employee.getImName());
        }
        if (!lstImClient.getFirstSelectedOption().getText().equalsIgnoreCase(employee.getImClient())) {
            lstImClient.select(employee.getImClient());
        }
        if (!lstDept.getFirstSelectedOption().getText().equalsIgnoreCase(employee.getDepartment())) {
            lstDept.select(employee.getDepartment());
        }

        // submit
        btnSave.syncEnabled();
        btnSave.submit();

        driver.page().isDomComplete();
    }

}

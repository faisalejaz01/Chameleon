package com.orasi.bluesource.features.manageEmployees;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orasi.DriverManager;
import com.orasi.bluesource.LoginPage;
import com.orasi.bluesource.commons.TopNavigationBar;
import com.orasi.bluesource.employeesPage.Employee;
import com.orasi.bluesource.employeesPage.EmployeeSummaryPage;
import com.orasi.bluesource.employeesPage.EmployeesPage;
import com.orasi.bluesource.employeesPage.ManageEmployeeModal;
import com.orasi.utils.Constants;
import com.orasi.utils.Randomness;
import com.orasi.utils.TestReporter;
import com.orasi.utils.dataProviders.CSVDataProvider;
import com.orasi.web.OrasiDriver;
import com.orasi.web.WebBaseTest;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class EmployeeCRUD extends WebBaseTest {

    private OrasiDriver driver = null;

    @DataProvider(name = "dataScenario")
    public Object[][] scenarios() {
        return CSVDataProvider.getData(Constants.BLUESOURCE_DATAPROVIDER_PATH + "Login.csv");
    }

    @Override
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResults) {
    }

    @Override
    @AfterClass(alwaysRun = true)
    public void afterClass(ITestContext testResults) {
        DriverManager.setDriver(driver);
        endTest(getTestName(), testResults);
    }

    @Features("Manage Employees")
    @Stories("I can create a new Employee")
    @Severity(SeverityLevel.BLOCKER)
    @Title("Manage Employees - Create Employee")
    @Test(dataProvider = "dataScenario", groups = { "regression", "manageEmployees", "employeeCRUD", "qaOnly" })
    public void testAddEmployee(@Parameter String testScenario, @Parameter String role) {
        setApplicationUnderTest("Bluesource");
        driver = testStart("testAddEmployee");
        Employee employee = new Employee();

        driver.data().add("employee", employee);
        // Login
        LoginPage loginPage = new LoginPage();
        TestReporter.assertTrue(loginPage.pageLoaded(), "Verify login page is displayed");
        loginPage.login(role);

        // Verify user is logged in
        TopNavigationBar topNavigationBar = new TopNavigationBar();
        TestReporter.assertTrue(topNavigationBar.isLoggedIn(), "Validate the user logged in successfully");

        // Navigate to Employees Page
        topNavigationBar.clickEmployeesLink();
        EmployeesPage employeesPage = new EmployeesPage();
        TestReporter.assertTrue(employeesPage.pageLoaded(), "Verify Employees page is displayed");

        // Open New Employee Modal
        employeesPage.clickAddEmployeeButton();
        ManageEmployeeModal newEmployee = new ManageEmployeeModal();
        TestReporter.assertTrue(newEmployee.pageLoaded(), "Verify New Employee Popup Modal is displayed");

        // Enter Employee Info
        newEmployee.addEmployee();
        TestReporter.assertTrue(employeesPage.isSuccessMsgDisplayed(), "Verify Success message appears after creating new employee");

        // Validate new Employee created and visible
        employeesPage.pageLoaded();
        employeesPage.enterSearchText(employee.getLastName());
        TestReporter.assertTrue(employeesPage.validateLastnameFoundInTable(employee.getLastName()), "Verify Employee " + employee.getLastName() + " appeared in the Employee Table");
    }

    @Features("Manage Employees")
    @Stories("I can see an Employee's General Info after creating Employee")
    @Severity(SeverityLevel.NORMAL)
    @Title("Manage Employees - View Employee Summary")
    @Test(groups = { "regression", "manageEmployees", "employeeCRUD", "qaOnly" }, dependsOnMethods = { "testAddEmployee" })
    public void testViewEmployeeGeneralInfo() {
        DriverManager.setDriver(driver);
        Employee employee = (Employee) driver.data().get("employee");
        EmployeesPage employeesPage = new EmployeesPage();
        employeesPage.selectEmployeeName(employee.getLastName());

        EmployeeSummaryPage summary = new EmployeeSummaryPage();
        TestReporter.assertTrue(summary.pageLoaded(), "Verify Employees Summary page is displayed");
        summary.validateGeneralInfo();

    }

    @Features("Manage Employees")
    @Stories("I can Modify an Employee's General Info and view changes")
    @Severity(SeverityLevel.MINOR)
    @Title("Manage Employees Employeesmployees - Modify Employee Information")
    @Test(groups = { "regression", "manageEmployees", "employeeCRUD", "qaOnly" }, dependsOnMethods = { "testViewEmployeeGeneralInfo" })
    public void testModifyEmployeeGeneralInfo() {
        DriverManager.setDriver(driver);
        Employee employee = (Employee) driver.data().get("employee");

        EmployeeSummaryPage summary = new EmployeeSummaryPage();
        summary.clickManageGeneralInfo();

        ManageEmployeeModal modifyEmployee = new ManageEmployeeModal();
        TestReporter.assertTrue(modifyEmployee.pageLoaded(), "Verify Manage Employee Popup Modal is displayed");

        employee.setLastName(Randomness.randomAlphaNumeric(8));

        modifyEmployee.modifyEmployee();
        summary.validateGeneralInfo();
    }

    @Features("Manage Employees")
    @Stories("I can mark an Employee as Inactive")
    @Severity(SeverityLevel.MINOR)
    @Title("Manage Employees - Mark Employee Inactive")
    @Test(groups = { "regression", "manageEmployees", "employeeCRUD", "qaOnly" }, dependsOnMethods = { "testModifyEmployeeGeneralInfo" })
    public void testDeactivateEmployee() {
        DriverManager.setDriver(driver);

        EmployeeSummaryPage summary = new EmployeeSummaryPage();
        summary.clickManageGeneralInfo();

        ManageEmployeeModal modifyEmployee = new ManageEmployeeModal();
        TestReporter.assertTrue(modifyEmployee.pageLoaded(), "Verify Manage Employee Popup Modal is displayed");

        ((Employee) driver.data().get("employee")).setStatus("Inactive");
        modifyEmployee.modifyEmployee();
        summary.validateGeneralInfo();

        TopNavigationBar topNavigationBar = new TopNavigationBar();
        topNavigationBar.clickEmployeesLink();

        EmployeesPage employeesPage = new EmployeesPage();
        TestReporter.assertTrue(employeesPage.pageLoaded(), "Verify Employees page is displayed");
        employeesPage.uncheckInactiveCheckbox();
        employeesPage.enterSearchText(((Employee) driver.data().get("employee")).getLastName());
        TestReporter.assertTrue(employeesPage.validateNoRowsFound(), "Verify Employees Table does not have Employee as inactive");
        topNavigationBar.clickLogout();
    }
}
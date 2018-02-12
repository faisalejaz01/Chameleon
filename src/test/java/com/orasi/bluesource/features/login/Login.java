package com.orasi.bluesource.features.login;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orasi.bluesource.LoginPage;
import com.orasi.bluesource.commons.TopNavigationBar;
import com.orasi.utils.Constants;
import com.orasi.utils.TestReporter;
import com.orasi.utils.dataProviders.CSVDataProvider;
import com.orasi.web.WebBaseTest;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class Login extends WebBaseTest {

    @DataProvider(name = "dataScenario")
    public Object[][] scenarios() {
        return CSVDataProvider.getData(Constants.BLUESOURCE_DATAPROVIDER_PATH + "Login.csv");
    }

    @Features("Login")
    @Stories("Logging in will land me on the Homepage")
    @Severity(SeverityLevel.BLOCKER)
    @Title("Login - Login with correct information")
    @Test(dataProvider = "dataScenario", groups = { "regression", "login" })
    public void testLogin(@Parameter String testScenario, @Parameter String role) {
        TestReporter.logScenario("This test logs into the Bluesource application by role & verifies the user was logged in successfully");
        setApplicationUnderTest("Bluesource");
        testStart("Test Login_" + getBrowserUnderTest() + "_" + getOperatingSystem());

        // Login
        TestReporter.logStep("Login to Bluesource using role: [" + role + "]");
        LoginPage loginPage = new LoginPage();
        TestReporter.assertTrue(loginPage.pageLoaded(), "Verify login page is displayed");
        loginPage.login(role);

        // Verify user is logged in
        TestReporter.logStep("Verify successful login");
        TopNavigationBar topNavigationBar = new TopNavigationBar();
        TestReporter.assertTrue(topNavigationBar.isLoggedIn(), "Validate the user logged in successfully");

        // logout
        TestReporter.logStep("Logout of application");
        topNavigationBar.clickLogout();
    }
}
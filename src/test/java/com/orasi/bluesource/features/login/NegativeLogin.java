package com.orasi.bluesource.features.login;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orasi.bluesource.LoginPage;
import com.orasi.utils.Constants;
import com.orasi.utils.TestReporter;
import com.orasi.utils.dataProviders.ExcelDataProvider;
import com.orasi.web.WebBaseTest;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class NegativeLogin extends WebBaseTest {
    @DataProvider(name = "negativeDataScenario", parallel = true)
    public Object[][] negativeScenarios() {
        return new ExcelDataProvider(Constants.BLUESOURCE_DATAPROVIDER_PATH + "Login.xlsx", "LoginNegative").getTestData();
    }

    @Features("Login")
    @Stories("Failing to login will not let me leave the Login page")
    @Severity(SeverityLevel.BLOCKER)
    @Title("Login - Login with incorrect information")
    @Test(dataProvider = "negativeDataScenario", groups = { "regression", "login" })
    public void testFailedLogin(@Parameter String testScenario, @Parameter String role) {

        TestReporter.logScenario("This test attempts to login to bluesource with incorrect credentials & verifies user is not logged in");
        setApplicationUnderTest("Bluesource");
        testStart("TestNegativeLogin_" + getBrowserUnderTest() + "_" + getOperatingSystem());

        // Login
        TestReporter.logStep("Login to Bluesource with incorrect information");
        LoginPage loginPage = new LoginPage();
        TestReporter.assertTrue(loginPage.pageLoaded(), "Verify login page is displayed");
        loginPage.login(role);
        TestReporter.logStep("Validate the user did not log in successfully");
        TestReporter.assertTrue(loginPage.isNotLoggedIn(), "Validate the user did not log in successfully");

    }
}
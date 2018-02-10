package com.orasi.bluesource;

import java.util.ResourceBundle;

import org.openqa.selenium.support.FindBy;

import com.orasi.DriverManager;
import com.orasi.utils.Constants;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Button;
import com.orasi.web.webelements.Element;
import com.orasi.web.webelements.Textbox;
import com.orasi.web.webelements.impl.internal.ElementFactory;

import ru.yandex.qatools.allure.annotations.Step;

public class LoginPage {
    private OrasiDriver driver = null;
    // all the page elements
    @FindBy(id = "employee_username")
    private Textbox txtUsername;
    @FindBy(id = "employee_password")
    private Textbox txtPassword;
    @FindBy(name = "commit")
    private Button btnLogin;
    @FindBy(className = "alert-danger")
    private Element eleAlert;

    // *********************
    // ** Build page area **
    // *********************
    public LoginPage() {
        this.driver = DriverManager.getDriver();
        ElementFactory.initElements(driver, this);
    }

    public boolean pageLoaded() {
        return driver.page().isElementLoaded(this.getClass(), btnLogin);
    }

    // *****************************************
    // ***Page Interactions ***
    // *****************************************

    @Step("Login with the role \"{0}\"")
    public void login(String role) {
        String username = "";
        String password = "";
        final ResourceBundle userCredentialRepo = ResourceBundle.getBundle(Constants.USER_CREDENTIALS_PATH);

        if (!role.toUpperCase().equals("SKIP_USER")) {
            username = userCredentialRepo.getString("BLUESOURCE_" + role.toUpperCase());
        }
        if (!role.toUpperCase().equals("SKIP_PASSWORD")) {
            password = userCredentialRepo.getString("BLUESOURCE_ENCODED_PASSWORD");
        }

        txtUsername.set(username);
        txtPassword.setSecure(password);
        btnLogin.submit();
    }

    @Step("User did not log in successfully")
    public boolean isNotLoggedIn() {
        return btnLogin.isDisplayed();
    }
}
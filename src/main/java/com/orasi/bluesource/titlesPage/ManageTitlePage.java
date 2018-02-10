package com.orasi.bluesource.titlesPage;

import org.openqa.selenium.support.FindBy;

import com.orasi.DriverManager;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Button;
import com.orasi.web.webelements.Textbox;
import com.orasi.web.webelements.impl.internal.ElementFactory;

import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Step;

public class ManageTitlePage {
    private OrasiDriver driver = null;

    // All the page elements
    @FindBy(id = "title_name")
    private Textbox txtTitle;
    @FindBy(name = "commit")
    private Button btnCreateTitle;

    // *********************
    // ** Build page area **
    // *********************
    public ManageTitlePage() {
        this.driver = DriverManager.getDriver();
        ;
        ElementFactory.initElements(driver, this);
    }

    public boolean pageLoaded() {
        return driver.page().isElementLoaded(this.getClass(), driver, txtTitle);
    }

    // *****************************************
    // ***Page Interactions ***
    // *****************************************

    // method to create a new title
    @Step("Create the new title \"{0}\"")
    public void createNewTitle(@Parameter String newTitle) {
        enterTitleName(newTitle);
    }

    @Step("Modify the title \"{0}\"")
    public void modifyTitle(@Parameter String title) {
        enterTitleName(title);
    }

    private void enterTitleName(String title) {
        txtTitle.set(title);
        btnCreateTitle.submit();
    }
}

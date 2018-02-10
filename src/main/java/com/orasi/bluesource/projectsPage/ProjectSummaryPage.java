package com.orasi.bluesource.projectsPage;

import org.openqa.selenium.support.FindBy;

import com.orasi.DriverManager;
import com.orasi.utils.TestReporter;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Button;
import com.orasi.web.webelements.Webtable;
import com.orasi.web.webelements.impl.internal.ElementFactory;

import ru.yandex.qatools.allure.annotations.Step;

public class ProjectSummaryPage {
    private OrasiDriver driver;

    // All the page elements
    @FindBy(xpath = "//table")
    private Webtable tabProjectInfoTable;
    @FindBy(xpath = "//h4[text()='Project Info']/../button")
    private Button btnManageProjectInfo;

    /*
     * General info rows
     */
    private final int CLIENT_PARTNER = 1;
    private final int TEAM_LEADS = 2;
    private final int START_DATE = 3;
    private final int PROJECTED_END_DATE = 4;
    private final int STATUS = 5;

    // *********************
    // ** Build page area **
    // *********************
    public ProjectSummaryPage() {
        this.driver = DriverManager.getDriver();
        ;
        ElementFactory.initElements(driver, this);
    }

    public boolean pageLoaded() {
        return driver.page().isElementLoaded(this.getClass(), tabProjectInfoTable);
    }
    // *****************************************
    // ***Page Interactions ***
    // *****************************************

    @Step("Then the Projects Information is correct")
    public boolean validateProjectInfo(Project project) {

        if (!project.getClientPartner().equalsIgnoreCase(tabProjectInfoTable.getCellData(CLIENT_PARTNER, 2))) {
            TestReporter.logFailure("Client P name did not match");
            return false;
        }
        if (!project.getStartDate().equalsIgnoreCase(tabProjectInfoTable.getCellData(START_DATE, 2))) {
            TestReporter.logFailure("Start Date did not match");
            return false;
        }
        if (!project.getEndDate().equalsIgnoreCase(tabProjectInfoTable.getCellData(PROJECTED_END_DATE, 2))) {
            TestReporter.logFailure("Projected End Date did not match");
            return false;
        }
        if (!project.getStatus().equalsIgnoreCase(tabProjectInfoTable.getCellData(STATUS, 2))) {
            TestReporter.logFailure("Status did not match");
            return false;
        }

        for (String teamLead : project.getTeamLeads()) {
            if (!tabProjectInfoTable.getCellData(TEAM_LEADS, 2).toLowerCase().contains(teamLead.toLowerCase())) {
                TestReporter.logFailure("Team Lead did not match: " + teamLead);
                return false;
            }
        }
        return true;
    }

    @Step("When I click Manage General Info")
    public void clickManageProjectInfo() {
        btnManageProjectInfo.click();
    }
}

package Steps;

import Elements.WebElements.AssetCard;
import Elements.WebElements.Container;
import Elements.WebElements.Implementation.Element;
import Helpers.BaseTest;
import Pages.*;
import com.google.common.collect.Lists;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CommonSteps extends BaseTest
{
//    private String currentURL = getDriver().getCurrentUrl();

//    private LogInPage logInPage = new LogInPage(getDriver());
//    private AboutPage aboutPage = new AboutPage(getDriver());
//    private CommonPage commonPage = new CommonPage(getDriver());
//    private WhyHBOPage whyHBOPage = new WhyHBOPage(getDriver());

    //    Header header = new Header(getDriver());
    private LogInPage logInPage;
    private WhyHBOPage whyHBOPage;
    private CommonPage commonPage;
    private SeriesDetailPage seriesDetailPage;
    private ProfileSelectorPage profileSelectorPage;
    private PINPage pinPage;
    private ForgotPINPage forgotPINPage;
    private CatalogWelcomePage catalogWelcomePage;
    private FamilyPage familyPage;
    private HomePage homePage;
    private WatchlistPage watchlistPage;
    private SearchPage searchPage;
    private PrivacyPolicyUpdatesPage privacyPolicyUpdatesPage;

    /** Constructor **/
    public CommonSteps()
    {
        logInPage = new LogInPage(getDriver());
        whyHBOPage = new WhyHBOPage(getDriver());
        commonPage = new CommonPage(getDriver());
        seriesDetailPage = new SeriesDetailPage(getDriver());
        profileSelectorPage = new ProfileSelectorPage(getDriver());
        pinPage = new PINPage(getDriver());
        forgotPINPage = new ForgotPINPage(getDriver());
        catalogWelcomePage = new CatalogWelcomePage(getDriver());
        familyPage = new FamilyPage(getDriver());
        privacyPolicyUpdatesPage = new PrivacyPolicyUpdatesPage(getDriver());
    }



    And("^I Navigate to a Random Series$")
    public void iNavigateToARandomSeries()
    {
        ArrayList<WebElement> seriesAssets;
        ArrayList<WebElement> availableShelves;

        commonPage.scrollToEndOfPage();
        wait(5);
        commonPage.scrollToTopOfPage();
        Container currentShelf = commonPage.navigateToRandomShelf();

        //the Test Fails Here as currentShelf.allVisibleAssetCards() returns null
        int assetShelfIndex = new Random().nextInt(currentShelf.allVisibleAssetCards().size());
        //
        currentShelf.visibleAssetCards().get(assetShelfIndex);
        WebElement selectedAsset = currentShelf.visibleAssetCards().get(assetShelfIndex);
        System.out.println("SELECTED ASSET CARD IS: " + selectedAsset);
        selectedAsset.click();
        wait(30);
    }




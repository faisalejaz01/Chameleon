package Elements.WebElements.ImplFiles;

import Elements.WebElements.Container;
import Elements.WebElements.Implementation.HBODriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ContainerImpl extends AssetCardImpl implements Container
{

    /** Constructors **/
    public ContainerImpl(WebElement element)
    {
        super(element);
    }

    public ContainerImpl(HBODriver driver, By by)
    {
        super(driver, by);
    }

    @FindBy(className = "_1vbnE") private AssetCard assetCard;
    @FindBy(className = "slick-active") private AssetCard visibleAssetCard;
    //This was tested with both FindBys and FindAll, and both times it returned a NullPointerException
    @FindBys({@FindBy(className = "slick-active")}) private AssetCard visibleAssetCards;

    @FindBy(className = "_2pWgq") private Label title;
    @FindBy(className = "progress") private Container sliderProgress;
    @FindBy(className = "_34qF6") private Button rightArrow;
    @FindBy(className = "_2vwpa") private Button leftArrow;
    @FindBy(className = "D6Br9") private Button seeAllButton;

    @FindBy(tagName = "a") private Link assetLink;

    @Override
    public ArrayList<AssetCard> assetCards()
    {
        getWrappedDriver().setElementTimeout(1, TimeUnit.SECONDS);
        By byDefiniton = getByName("assetCard");
        ArrayList assetCardList = (ArrayList) getWrappedElement().findElements(byDefiniton);
        return assetCardList;
    }

    @Override
    public List<AssetCard> visibleAssetCards()
    {
        getWrappedDriver().setElementTimeout(1, TimeUnit.SECONDS);
        //This was why I used the conversion to byDefinition here
        By byDefiniton = getByName("visibleAssetCard");
        List<WebElement> assetCardList = getWrappedElement().findElements(byDefiniton);
        List<AssetCard> el = new ArrayList<>();
        assetCardList.forEach(element -> el.add(new AssetCardImpl(driver, byDefiniton, element)));

        return el;
    }

    //Using a Find using the standard FindBy fails when used in the following way
    @Override
    public List<AssetCard> allVisibleAssetCards()
    {
        List<AssetCard> assetCards = visibleAssetCards;
        return visibleAssetCards;
    }
}

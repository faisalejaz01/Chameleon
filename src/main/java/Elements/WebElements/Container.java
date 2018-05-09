package Elements.WebElements;

import Elements.WebElements.ImplFiles.ContainerImpl;
import Elements.WebElements.Implementation.ImplementedBy;

import java.util.ArrayList;
import java.util.List;

/**
 * A Container in which elements are located.
 */
@ImplementedBy(ContainerImpl.class)
public interface Container extends AssetCard
{
    ArrayList assetCards();

    List<AssetCard> visibleAssetCards();

    List<AssetCard> allVisibleAssetCards();
}

package union.xenfork.nucleoplasm.json.edit.face.net.minecraft.item;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Rarity;

public interface GetSettings {

    int getMaxCount();
    int getMaxDamage();
    Item getRecipeRemainder();

    Rarity getRarity();

    boolean getFireproof();

    FoodComponent getFoodComponent();
    FeatureSet getRequiredFeatures();


    void setMaxCount(int maxCount);
    void setMaxDamage(int maxDamage);
    void setRecipeRemainder(Item recipeRemainder);
    void setRarity(Rarity rarity);
    void setFireproof(boolean fireproof);
    void setFoodComponent(FoodComponent foodComponent);
    void setRequiredFeatures(FeatureSet requiredFeatures);

}

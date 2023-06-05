package union.xenfork.nucleoplasm.json.edit.face;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Rarity;

import java.util.List;

public interface GetSettingsSettings {

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

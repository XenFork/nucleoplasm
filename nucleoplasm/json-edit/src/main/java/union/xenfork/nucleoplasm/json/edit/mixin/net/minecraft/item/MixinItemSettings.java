package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.item;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.net.minecraft.item.GetSettings;

@Mixin(Item.Settings.class)
public class MixinItemSettings implements GetSettings {

    @Shadow
    int maxCount;

    @Shadow
    int maxDamage;

    @Shadow
    @Nullable Item recipeRemainder;

    @Shadow
    Rarity rarity;

    @Shadow
    boolean fireproof;

    @Shadow
    @Nullable FoodComponent foodComponent;

    @Shadow
    FeatureSet requiredFeatures;

    @Override
    public int getMaxCount() {
        return maxCount;
    }

    @Override
    public int getMaxDamage() {
        return maxDamage;
    }

    @Override
    public @Nullable Item getRecipeRemainder() {
        return recipeRemainder;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public boolean getFireproof() {
        return fireproof;
    }

    @Override
    public @Nullable FoodComponent getFoodComponent() {
        return foodComponent;
    }

    @Override
    public FeatureSet getRequiredFeatures() {
        return requiredFeatures;
    }

    @Override
    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    @Override
    public void setRecipeRemainder(@Nullable Item recipeRemainder) {
        this.recipeRemainder = recipeRemainder;
    }

    @Override
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public void setFireproof(boolean fireproof) {
        this.fireproof = fireproof;
    }
    @Override
    public void setFoodComponent(FoodComponent foodComponent) {
        if (this.foodComponent == null || foodComponent == null) {
            this.foodComponent = foodComponent;
        } else {
            this.foodComponent.setHunger(foodComponent.getHunger());
            this.foodComponent.setMeat(foodComponent.getMeat());
            this.foodComponent.setAlwaysEdible(foodComponent.getAlwaysEdible());
            this.foodComponent.setSnack(foodComponent.getSnack());
            this.foodComponent.setSaturationModifier(foodComponent.getSaturationModifier());
            this.foodComponent.setStatusEffects(foodComponent.getStatusEffects());
        }
        this.foodComponent = foodComponent;
    }

    @Override
    public void setRequiredFeatures(FeatureSet requiredFeatures) {
        if (requiredFeatures == null || this.requiredFeatures == null) {
            this.requiredFeatures = requiredFeatures;
        } else {
            this.requiredFeatures.setFeaturesMask(requiredFeatures.getFeaturesMask());
            this.requiredFeatures.setUniverse(requiredFeatures.getUniverse());
        }
    }
}

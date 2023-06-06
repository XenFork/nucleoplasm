package union.xenfork.nucleoplasm.json.edit.mixin.register;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.GetItemSettings;

@Mixin(value = Item.Settings.class)
public class MixinItemSettings implements GetItemSettings {
    @Shadow
    int maxCount;

    @Shadow
    int maxDamage;

    @Shadow
    @Nullable Item recipeRemainder;

    @Shadow
    Rarity rarity;

    @Shadow
    @Nullable FoodComponent foodComponent;

    @Shadow
    boolean fireproof;

    @Shadow
    FeatureSet requiredFeatures;

    @Override
    public int maxCount() {
        return maxCount;
    }

    @Override
    public int maxDamage() {
        return maxDamage;
    }

    @Override
    public @Nullable Item recipeRemainder() {
        return recipeRemainder;
    }

    @Override
    public Rarity rarity() {
        return rarity;
    }

    @Override
    public @Nullable FoodComponent foodComponent() {
        return foodComponent;
    }

    @Override
    public boolean getFireproof() {
        return fireproof;
    }

    @Override
    public FeatureSet requiredFeatures() {
        return requiredFeatures;
    }

    @Override
    public void maxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public void maxDamage(int maxDamage) {
        this.maxCount = maxDamage;
    }

    @Override
    public void recipeRemainder(@Nullable Item recipeRemainder) {
        this.recipeRemainder = recipeRemainder;
    }

    @Override
    public void rarity(Rarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public void foodComponent(FoodComponent foodComponent) {
        this.foodComponent = foodComponent;
    }

    @Override
    public void fireproof(boolean fireproof) {
        this.fireproof = fireproof;
    }

    @Override
    public void requiredFeatures(FeatureSet requiredFeatures) {
        this.requiredFeatures = requiredFeatures;
    }
}

package union.xenfork.nucleoplasm.json.edit.mixin.register;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.json.edit.face.GetItem;
import union.xenfork.nucleoplasm.json.edit.face.GetItemSettings;

@Mixin(value = Item.class, priority = 9998)
public class MixinItem implements GetItem {
    @Mutable
    @Shadow
    @Final
    private Rarity rarity;
    @Mutable
    @Shadow
    @Final
    @Nullable
    private Item recipeRemainder;
    @Mutable
    @Shadow
    @Final
    private int maxDamage;
    @Mutable
    @Shadow
    @Final
    private int maxCount;
    @Mutable
    @Shadow
    @Final
    @Nullable
    private FoodComponent foodComponent;
    @Mutable
    @Shadow
    @Final
    private boolean fireproof;
    @Mutable
    @Shadow
    @Final
    private FeatureSet requiredFeatures;
    @Final
    @Mutable
    private Item.Settings settings;
    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(Item.Settings settings, CallbackInfo ci) {
        this.settings = settings;
    }

    @Override
    public Item.Settings settings() {
        return settings;
    }

    @Override
    public void settings(Item.Settings settings) {
        this.settings = settings;
    }

    @Override
    public void reInitSettings() {
        rarity = settings.rarity();
        recipeRemainder = settings.recipeRemainder();
        maxDamage = settings.maxDamage();
        maxCount = settings.maxCount();
        foodComponent = settings.foodComponent();
        fireproof = settings.getFireproof();
        requiredFeatures = settings.requiredFeatures();
    }

    @Mixin(value = Item.Settings.class, priority = 9999)
    private static class MixinSettings implements GetItemSettings {
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
}

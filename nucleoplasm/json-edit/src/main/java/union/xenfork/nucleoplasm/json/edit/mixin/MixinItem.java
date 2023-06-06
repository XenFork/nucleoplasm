package union.xenfork.nucleoplasm.json.edit.mixin;

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

@Mixin(value = Item.class)
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
}

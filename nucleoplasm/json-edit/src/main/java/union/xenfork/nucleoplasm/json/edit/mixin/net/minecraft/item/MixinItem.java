package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.item;

import net.minecraft.SharedConstants;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.json.edit.face.GetSettings;

@Mixin(Item.class)
public class MixinItem implements GetSettings {
    @Shadow
    @Final
    private static Logger LOGGER;
    @Mutable
    @Shadow
    @Final
    @Nullable
    private FoodComponent foodComponent;
    @Mutable
    @Final
    @Shadow
    private int maxCount;
    @Mutable
    @Final
    @Shadow
    private int maxDamage;
    @Mutable
    @Final
    @Shadow
    @Nullable
    private Item recipeRemainder;
    @Mutable
    @Final
    @Shadow
    private Rarity rarity;
    @Mutable
    @Shadow
    @Final
    private boolean fireproof;
    @Mutable
    @Shadow
    @Final
    private FeatureSet requiredFeatures;
    private Item.Settings settings;
    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(Item.Settings settings, CallbackInfo ci) {
        this.settings = settings;
    }

    @Override
    public Item.Settings getSettings() {
        return settings;
    }

    @Override
    public void setSettings() {
        rarity = settings.getRarity();
        recipeRemainder = settings.getRecipeRemainder();
        maxDamage = settings.getMaxDamage();
        maxCount = settings.getMaxCount();
        if (settings.getFoodComponent() == null || foodComponent == null) {
            foodComponent = settings.getFoodComponent();
        } else {
            foodComponent.setHunger(settings.getFoodComponent().getHunger());
            foodComponent.setStatusEffects(settings.getFoodComponent().getStatusEffects());
            foodComponent.setSaturationModifier(settings.getFoodComponent().getSaturationModifier());
            foodComponent.setMeat(settings.getFoodComponent().getMeat());
            foodComponent.setSnack(settings.getFoodComponent().getSnack());
            foodComponent.setAlwaysEdible(settings.getFoodComponent().getAlwaysEdible());
        }
        fireproof = settings.getFireproof();
        requiredFeatures = settings.getRequiredFeatures();

        if (SharedConstants.isDevelopment) {
            String string = this.getClass().getSimpleName();
            if (!string.endsWith("Item")) {
                LOGGER.error("Item classes should end with Item and {} doesn't.", string);
            }
        }
    }
}

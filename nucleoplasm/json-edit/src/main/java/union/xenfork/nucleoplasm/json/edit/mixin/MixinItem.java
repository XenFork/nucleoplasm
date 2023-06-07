package union.xenfork.nucleoplasm.json.edit.mixin;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
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
import union.xenfork.nucleoplasm.json.edit.face.Get;
import union.xenfork.nucleoplasm.json.edit.face.GetSettings;
import union.xenfork.nucleoplasm.json.edit.gson.FeatureSetGson;
import union.xenfork.nucleoplasm.json.edit.gson.FoodComponentGson;
import union.xenfork.nucleoplasm.json.edit.gson.ItemSettingsGson;

@Mixin(Item.class)
public class MixinItem implements Get<ItemSettingsGson>, GetSettings {
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
    private Item.Settings settings;
    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(Item.Settings settings,
                      CallbackInfo ci) {
        this.settings = settings;
    }

    @Override
    public ItemSettingsGson get() {
        ItemSettingsGson is = new ItemSettingsGson();
        is.maxCount = settings.maxCount;
        is.maxDamage = settings.maxDamage;
        is.recipeRemainder = Registries.ITEM.getId(settings.recipeRemainder).toString();
        is.rarity = settings.rarity.name();
        if (settings.foodComponent != null) {
            is.foodComponent = (FoodComponentGson) settings.foodComponent.get();
        }
        is.fireproof = settings.fireproof;
        is.requiredFeatures = new FeatureSetGson(settings.requiredFeatures);
        return is;
    }

    @Override
    public void set(ItemSettingsGson itemSettingsGson) {
        settings.maxCount = itemSettingsGson.maxCount;
        settings.maxDamage = itemSettingsGson.maxDamage;
        if (settings.foodComponent != null) {
            //noinspection unchecked
            settings.foodComponent.set(itemSettingsGson.foodComponent);
        }
        rarity = settings.rarity;
        recipeRemainder = settings.recipeRemainder;
        maxDamage = settings.maxDamage;
        maxCount = settings.maxCount;
        foodComponent = settings.foodComponent;
        fireproof = settings.fireproof;
        requiredFeatures = settings.requiredFeatures;

    }

    @Override
    public Item.Settings getSettings() {
        return settings;
    }
}

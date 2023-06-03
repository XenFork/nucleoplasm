package union.xenfork.nucleoplasm.json.edit.registry.item;

import com.google.gson.annotations.SerializedName;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import union.xenfork.nucleoplasm.json.edit.registry.effect.StatusEffectInstanceLoader;

import java.util.HashMap;

/**
 * @author baka4n
 * {@link FoodComponent}
 */
public class FoodComponentLoader {
    @SerializedName("hunger")
    private int hunger;
    @SerializedName("saturationModifier")
    private float saturationModifier;
    @SerializedName("meat")
    private boolean meat;
    @SerializedName("alwaysEdible")
    private boolean alwaysEdible;
    @SerializedName("snack")
    private boolean snack;

    @SerializedName("statusEffects")
    private HashMap<StatusEffectInstanceLoader, Float> statusEffects;

    public static FoodComponentLoader create(FoodComponent itemFoodComponent) {
        FoodComponentLoader loader = new FoodComponentLoader();
        loader.hunger = itemFoodComponent.getHunger();
        loader.saturationModifier = itemFoodComponent.getSaturationModifier();
        loader.meat = itemFoodComponent.isMeat();
        loader.alwaysEdible = itemFoodComponent.isAlwaysEdible();
        loader.snack = itemFoodComponent.isSnack();
        HashMap<StatusEffectInstanceLoader, Float> map = new HashMap<>();
        for (Pair<StatusEffectInstance, Float> statusEffect : itemFoodComponent.getStatusEffects())
            map.put(StatusEffectInstanceLoader.create(statusEffect.getFirst()), statusEffect.getSecond());
        loader.statusEffects = map;
        return loader;
    }

    public static FoodComponentLoader create(int hunger, float saturationModifier, boolean meat, boolean alwaysEdible, boolean snack, HashMap<StatusEffectInstanceLoader, Float> statusEffects) {
        FoodComponentLoader foodComponentLoader = new FoodComponentLoader();
        foodComponentLoader.hunger = hunger;
        foodComponentLoader.saturationModifier = saturationModifier;
        foodComponentLoader.meat = meat;
        foodComponentLoader.alwaysEdible =alwaysEdible;
        foodComponentLoader.snack = snack;
        foodComponentLoader.statusEffects = statusEffects;
        return foodComponentLoader;
    }

    public FoodComponent getFoodComponent() {
        FoodComponent.Builder builder = new FoodComponent.Builder();
        builder.hunger(hunger).saturationModifier(saturationModifier);
        if (meat) builder.meat();
        if (alwaysEdible) builder.alwaysEdible();
        if (snack) builder.snack();
        statusEffects.forEach((s, f) -> {
            builder.statusEffect(s.getStatusEffectInstance(), f);
        });
        return builder.build();
    }
}

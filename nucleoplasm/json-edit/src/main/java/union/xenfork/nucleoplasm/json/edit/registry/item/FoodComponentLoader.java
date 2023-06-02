package union.xenfork.nucleoplasm.json.edit.registry.item;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.FoodComponent;
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
}

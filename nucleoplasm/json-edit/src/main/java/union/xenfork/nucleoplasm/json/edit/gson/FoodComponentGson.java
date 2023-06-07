package union.xenfork.nucleoplasm.json.edit.gson;

import com.google.gson.annotations.SerializedName;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.ArrayList;

public class FoodComponentGson {
    @SerializedName("hunger")
    public int hunger;
    @SerializedName("saturation_modifier")
    public float saturationModifier;
    @SerializedName("meat")
    public boolean meat;
    @SerializedName("always_edible")
    public boolean alwaysEdible;
    @SerializedName("snack")
    public boolean snack;
    @SerializedName("status_effects")
    public ArrayList<Pair<StatusEffectInstance, Float>> statusEffects;

    public FoodComponentGson() {

    }


}

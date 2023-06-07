package union.xenfork.nucleoplasm.json.edit.gson;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public class ItemSettingsGson {
    @SerializedName("count")
    public int maxCount;
    @SerializedName("damage")
    public int maxDamage;
    @SerializedName("recipe_remainder")
    @Nullable
    public String recipeRemainder;
    @SerializedName("rarity")
    public String rarity;
    @SerializedName("food")
    @Nullable
    public FoodComponentGson foodComponent;
    @SerializedName("fireproof")
    public boolean fireproof;
    @SerializedName("required_features")
    public FeatureSetGson requiredFeatures;

}

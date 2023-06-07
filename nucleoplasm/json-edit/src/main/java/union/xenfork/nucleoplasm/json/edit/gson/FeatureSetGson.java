package union.xenfork.nucleoplasm.json.edit.gson;

import com.google.gson.annotations.SerializedName;
import net.minecraft.resource.featuretoggle.FeatureSet;

public class FeatureSetGson {
    @SerializedName("universe")
    public String universe;
    @SerializedName("features_mask")
    public long featuresMask;

    public FeatureSetGson(FeatureSet requiredFeatures) {
        if (requiredFeatures.universe != null) {
            universe = requiredFeatures.universe.toString();
        }
        featuresMask = requiredFeatures.featuresMask;
    }
}

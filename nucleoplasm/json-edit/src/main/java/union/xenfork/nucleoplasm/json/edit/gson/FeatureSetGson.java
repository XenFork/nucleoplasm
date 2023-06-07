package union.xenfork.nucleoplasm.json.edit.gson;

import net.minecraft.resource.featuretoggle.FeatureSet;

public class FeatureSetGson {
    public String universe;
    public long featuresMask;

    public FeatureSetGson(FeatureSet requiredFeatures) {
        if (requiredFeatures.universe != null) {
            universe = requiredFeatures.universe.toString();
        }
        featuresMask = requiredFeatures.featuresMask;
    }
}

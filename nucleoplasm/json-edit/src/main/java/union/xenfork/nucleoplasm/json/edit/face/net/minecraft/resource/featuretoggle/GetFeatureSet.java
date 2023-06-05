package union.xenfork.nucleoplasm.json.edit.face.net.minecraft.resource.featuretoggle;

import net.minecraft.resource.featuretoggle.FeatureUniverse;

public interface GetFeatureSet {
    FeatureUniverse getUniverse();
    long getFeaturesMask();
    void setUniverse(FeatureUniverse universe);
    void setFeaturesMask(long featuresMask);
}

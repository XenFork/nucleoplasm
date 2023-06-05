package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.resource.featuretoggle;

import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.featuretoggle.FeatureUniverse;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.GetFeatureSet;

@Mixin(FeatureSet.class)
public class MixinFeatureSet implements GetFeatureSet {
    @Mutable
    @Shadow
    @Final
    @Nullable
    private FeatureUniverse universe;

    @Mutable
    @Shadow
    @Final
    private long featuresMask;

    @Override
    public @Nullable FeatureUniverse getUniverse() {
        return universe;
    }

    @Override
    public long getFeaturesMask() {
        return featuresMask;
    }

    @Override
    public void setUniverse(@Nullable FeatureUniverse universe) {
        this.universe = universe;
    }

    @Override
    public void setFeaturesMask(long featuresMask) {
        this.featuresMask = featuresMask;
    }
}

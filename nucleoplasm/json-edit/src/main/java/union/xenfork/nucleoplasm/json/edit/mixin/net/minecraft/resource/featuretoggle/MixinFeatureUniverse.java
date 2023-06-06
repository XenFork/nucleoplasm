package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.resource.featuretoggle;

import net.minecraft.resource.featuretoggle.FeatureUniverse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.net.minecraft.resource.featuretoggle.GetFeatureUniverse;

@Mixin(FeatureUniverse.class)
public class MixinFeatureUniverse implements GetFeatureUniverse {
    @Mutable
    @Shadow
    @Final
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}

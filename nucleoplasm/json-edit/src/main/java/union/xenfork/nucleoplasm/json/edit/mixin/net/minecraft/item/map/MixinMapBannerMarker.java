package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.item.map;

import net.minecraft.item.map.MapBannerMarker;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.net.minecraft.item.map.GetMapBannerMarker;

@Mixin(MapBannerMarker.class)
public class MixinMapBannerMarker implements GetMapBannerMarker {

    @Shadow
    @Final
    private BlockPos pos;

    @Override
    public BlockPos getPos() {
        return pos;
    }

    @Override
    public void setPos(BlockPos pos) {

    }
}

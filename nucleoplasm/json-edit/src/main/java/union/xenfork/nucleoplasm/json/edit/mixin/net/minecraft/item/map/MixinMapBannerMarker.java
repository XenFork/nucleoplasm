package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.item.map;

import net.minecraft.item.map.MapBannerMarker;
import org.spongepowered.asm.mixin.Mixin;
import union.xenfork.nucleoplasm.json.edit.face.net.minecraft.item.map.GetMapBannerMarker;

@Mixin(MapBannerMarker.class)
public class MixinMapBannerMarker implements GetMapBannerMarker {

}

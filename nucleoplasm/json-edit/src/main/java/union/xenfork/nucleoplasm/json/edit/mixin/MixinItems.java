package union.xenfork.nucleoplasm.json.edit.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.json.edit.registry.face.GetItem;

import java.util.HashMap;
import java.util.Map;

@Mixin(Items.class)
public class MixinItems implements GetItem {
    private static final Map<String, Item> map = new HashMap<>();
    @Shadow
    @Final
    public static Item AIR;

    @Shadow
    @Final
    public static Item STONE;

    @Inject(method = "<clinit>", at = @At(value = "RETURN"))
    private static void clinitReturn(CallbackInfo ci) {
        map.put("AIR", AIR);
        map.put("STONE", STONE);
    }

    @Override
    public Map<String, Item> get() {
        return map;
    }
}

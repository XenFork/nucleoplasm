package union.xenfork.nucleoplasm.json.edit.mixin.register;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(Items.class)
public class MixinItems {
    @Shadow
    @Final
    public static Item AIR;

    @Shadow
    @Final
    public static Item STONE;
    private static Map<String, Item> map = new HashMap<>();

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void clinitReturn(CallbackInfo ci) {
        map.put(Registries.ITEM.getId(AIR).toString(), AIR);
        map.put(Registries.ITEM.getId(STONE).toString(), STONE);
    }
}

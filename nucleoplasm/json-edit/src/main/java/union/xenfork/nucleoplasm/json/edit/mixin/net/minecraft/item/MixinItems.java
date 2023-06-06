package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.item;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.json.edit.Nucleoplasm;
import union.xenfork.nucleoplasm.json.edit.face.Get;

import java.nio.file.Path;

@Mixin(Items.class)
public class MixinItems implements Get<Path> {
    @Shadow
    @Final
    public static Item AIR;
    private static Path item;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void clinit(CallbackInfo ci) {
        item = Nucleoplasm.registry.resolve("item");
    }

    @Override
    public Path get() {
        return item;
    }
}

package union.xenfork.nucleoplasm.json.edit.mixin.register;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.json.edit.face.GetItem;

@Mixin(Item.class)
public class MixinItem implements GetItem {
    @Final
    @Mutable
    private Item.Settings settings;
    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(Item.Settings settings, CallbackInfo ci) {
        this.settings = settings;
    }

    @Override
    public Item.Settings getSettings() {
        return settings;
    }

    public void setSettings(Item.Settings settings) {
        this.settings = settings;
    }
}

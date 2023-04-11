package union.xenfork.nucleoplasm.api.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Shadow @Final private static Logger LOGGER;

    @Inject(method = "init()V", at = @At("RETURN"))
    public void tick(CallbackInfo ci) {
        LOGGER.info("nucleoplasm api title screen mixin");
    }
}

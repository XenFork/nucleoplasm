package union.xenfork.nucleoplasm.json.edit.mixin;

import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.SinglePreparationResourceReloader;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SinglePreparationResourceReloader.class)
public class MixinSinglePreparationResourceReloader {
    @Inject(method = "method_18790", at = @At("RETURN"))
    private void method_18790(ResourceManager resourceManager, Profiler profiler, Object prepared, CallbackInfo ci) {
        nucleoplasm_synchronizer_lambda(resourceManager, profiler, prepared);
    }

    protected void nucleoplasm_synchronizer_lambda(ResourceManager resourceManager, Profiler profiler, Object prepared) {
    }
}

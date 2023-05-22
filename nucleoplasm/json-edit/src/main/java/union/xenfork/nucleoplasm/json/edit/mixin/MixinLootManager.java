package union.xenfork.nucleoplasm.json.edit.mixin;

import net.minecraft.loot.LootDataKey;
import net.minecraft.loot.LootDataType;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootTableReporter;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(LootManager.class)
public class MixinLootManager {
    @Inject(method = "load", at = @At("RETURN"))
    private static <T> void loadReturn(
        LootDataType<T> type, ResourceManager resourceManager,
        Executor executor, Map<LootDataType<?>, Map<Identifier, ?>> results,
        CallbackInfoReturnable<CompletableFuture<?>> cir
    ) {

    }

    @Inject(method = "reload", at = @At("RETURN"))
    private void reloadReturn(
        ResourceReloader.Synchronizer synchronizer, ResourceManager manager,
        Profiler prepareProfiler, Profiler applyProfiler,
        Executor prepareExecutor, Executor applyExecutor,
        CallbackInfoReturnable<CompletableFuture<Void>> cir
    ) {

    }

    @Inject(method = "validate(Ljava/util/Map;)V", at = @At("RETURN"))
    private void validateReturn(Map<LootDataType<?>, Map<Identifier, ?>> lootData, CallbackInfo ci) {

    }

    @Inject(method = "validate(Lnet/minecraft/loot/LootTableReporter;Lnet/minecraft/loot/LootDataKey;Ljava/lang/Object;)V", at = @At("RETURN"))
    private static <T> void validateReturn(LootTableReporter reporter, LootDataKey<T> key, Object value, CallbackInfo ci) {

    }
}

package union.xenfork.nucleoplasm.json.edit.mixin;

import com.google.gson.JsonElement;
import net.minecraft.loot.LootDataType;
import net.minecraft.loot.LootManager;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.json.edit.Nucleoplasm;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(LootManager.class)
public abstract class MixinLootManager {
    @Shadow
    protected abstract void validate(Map<LootDataType<?>, Map<Identifier, ?>> lootData);

    @Inject(method = "reload", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void reload(ResourceReloader.Synchronizer synchronizer, ResourceManager manager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor, CallbackInfoReturnable<CompletableFuture<Void>> cir, Map<LootDataType<?>, Map<Identifier, ?>> map,  CompletableFuture<?>[] completableFutures) {
        completableFutures = LootDataType.stream().map((type) -> loadT(type, manager, prepareExecutor, map)).toArray((i) -> new CompletableFuture<?>[i]);
        CompletableFuture<?> var10000 = CompletableFuture.allOf(completableFutures);
        Objects.requireNonNull(synchronizer);
        cir.setReturnValue(var10000.thenCompose(synchronizer::whenPrepared).thenAcceptAsync((v) -> {
            this.validate(map);
        }, applyExecutor));
    }

    private static <T> CompletableFuture<?> loadT(LootDataType<T> type, ResourceManager resourceManager, Executor executor, Map<LootDataType<?>, Map<Identifier, ?>> results) {
        Map<Identifier, T> map = new HashMap<>();
        results.put(type, map);
        return CompletableFuture.runAsync(() -> {
            Map<Identifier, JsonElement> map2 = new HashMap<>();
            JsonDataLoader.load(resourceManager, type.getId(), type.getGson(), map2);
            Path t = Nucleoplasm.dir.resolve(type.getId());
            Nucleoplasm.outputJson(map2, t);
            Map<Identifier, File> iFile = new HashMap<>();
            Nucleoplasm.load(t.toFile(), null, null, iFile, "/");
            map2.clear();
            Nucleoplasm.parser(iFile, map2);
            map2.forEach((id, json) -> {
                type.parse(id, json).ifPresent((value) -> {
                    map.put(id, value);
                });
            });
        }, executor);
    }
}

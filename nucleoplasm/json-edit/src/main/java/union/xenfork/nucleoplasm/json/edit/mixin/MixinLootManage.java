package union.xenfork.nucleoplasm.json.edit.mixin;

import com.google.gson.JsonElement;
import net.minecraft.loot.LootDataType;
import net.minecraft.loot.LootManager;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(LootManager.class)
public abstract class MixinLootManage {

    @Inject(method = "load", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private static <T> void load(LootDataType<T> type, ResourceManager resourceManager, Executor executor, Map<LootDataType<?>, Map<Identifier, ?>> results, CallbackInfoReturnable<CompletableFuture<?>> cir, Map<?,?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object o = entry.getKey();
            Object o2 = entry.getValue();
            System.out.println(o.getClass());
        }
//        if (identifierMap != null) {
//            cir.setReturnValue(CompletableFuture.runAsync(() -> {
//                Map<Identifier, JsonElement> map2 = new HashMap<>();
//                JsonDataLoader.load(resourceManager, type.getId(), type.getGson(), map2);
//                map2.forEach((id, json) -> {
//                    type.parse(id, json).ifPresent((value) -> {
//                        identifierMap.put(id, value);
//                    });
//                });
//            }, executor));
//        }
    }

//    @Inject(method = "reload", at = @At(value = "INVOKE", target = "Ljava/util/Objects;requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;", shift = At.Shift.AFTER))
//    public void reload(ResourceReloader.Synchronizer synchronizer, ResourceManager manager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor, CallbackInfoReturnable<CompletableFuture<Void>> cir) {
//
//    }
}

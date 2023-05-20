package union.xenfork.nucleoplasm.json.edit.mixin;

import com.google.gson.JsonSerializationContext;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.minecraft.loot.LootDataType;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootTable;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(LootManager.class)
public class MixinLootManager {
//    @Inject(method = "reload", at = @At(value = "INVOKE", target = "Ljava/util/Objects;requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
//    private void reload(ResourceReloader.Synchronizer synchronizer, ResourceManager manager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor, CallbackInfoReturnable<CompletableFuture<Void>> cir, Map<LootDataType<?>, Map<Identifier, ?>> map, CompletableFuture<?>[] completableFutures) {
////        for (Map.Entry<LootDataType<?>, Map<Identifier, ?>> entry : map.entrySet()) {
////            LootDataType<?> lootDataType = entry.getKey();
////            Map<Identifier, ?> identifierMap = entry.getValue();
////            System.out.println(lootDataType.getClass());
////            System.out.println(identifierMap);
////        }
//    }
    private static final LootTable.Serializer serializer = new LootTable.Serializer();


    @Inject(method = "validate(Ljava/util/Map;)V", at = @At("RETURN"))
    private void validate(Map<LootDataType<?>, Map<Identifier, ?>> lootData, CallbackInfo ci) {
        lootData.forEach((lootDataType, identifierMap) -> {
            identifierMap.forEach((identifier, o) -> {
                System.out.println(identifier);
                if (o instanceof LootTable table) {
//                    serializer.serialize(table, table.getType())
                }
            });
        });
    }


}

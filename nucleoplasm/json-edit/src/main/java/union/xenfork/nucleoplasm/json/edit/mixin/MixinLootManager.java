package union.xenfork.nucleoplasm.json.edit.mixin;

import com.google.gson.JsonElement;
import net.minecraft.loot.LootDataType;
import net.minecraft.loot.LootManager;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.json.edit.Nucleoplasm;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(LootManager.class)
public class MixinLootManager {
    private static final Map<Identifier, JsonElement> map2 = new HashMap<>();

    @Inject(method = "load", at = @At(value = "HEAD"))
    private static <T> void load(LootDataType<T> type, ResourceManager resourceManager, Executor executor, Map<LootDataType<?>, Map<Identifier, ?>> results, CallbackInfoReturnable<CompletableFuture<?>> cir) {
        if (!Files.exists(Nucleoplasm.loot_table)) {
            JsonDataLoader.load(resourceManager, type.getId(), type.getGson(), map2);
            Nucleoplasm.outputJson(map2, Nucleoplasm.loot_table);
        }

    }

    @Inject(method = "load", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private static <T> void loadReturn(LootDataType<T> type, ResourceManager resourceManager, Executor executor, Map<LootDataType<?>, Map<Identifier, ?>> results, CallbackInfoReturnable<CompletableFuture<?>> cir, Map<Identifier, T> map) {
        Map<Identifier, File> iFile = new HashMap<>();
        Nucleoplasm.load(Nucleoplasm.loot_table.toFile(), null, null, iFile, "/");
        map2.clear();
        Nucleoplasm.parser(iFile, map2);
        cir.setReturnValue(CompletableFuture.runAsync(() -> {
            JsonDataLoader.load(resourceManager, type.getId(), type.getGson(), map2);
            map2.forEach((id, json) -> {
                type.parse(id, json).ifPresent((value) -> {
                    map.put(id, value);
                });
            });
        }, executor));
    }
//    private static final LootTable.Serializer serializer = new LootTable.Serializer();


//    @Inject(method = "validate(Ljava/util/Map;)V", at = @At("RETURN"))
//    private void validate(Map<LootDataType<?>, Map<Identifier, ?>> lootData, CallbackInfo ci) {
////        lootData.forEach((lootDataType, identifierMap) -> {
////            identifierMap.forEach((identifier, o) -> {
////                System.out.println(identifier);
////                if (o instanceof LootTable table) {
//////                    serializer.serialize(table, table.getType())
////                }
////            });
////        });
//    }


}

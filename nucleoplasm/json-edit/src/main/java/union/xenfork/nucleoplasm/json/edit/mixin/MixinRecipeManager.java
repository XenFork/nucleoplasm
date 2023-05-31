package union.xenfork.nucleoplasm.json.edit.mixin;

import com.google.gson.JsonElement;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.json.edit.Nucleoplasm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Mixin(RecipeManager.class)
public class MixinRecipeManager {
    @Shadow
    @Final
    private static Logger LOGGER;

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        preInit(map);
        init();
    }

    private static void preInit(Map<Identifier, JsonElement> map) {
        map.forEach((id, json) -> LOGGER.info("load {}, json {}", id, json.toString()));
        if (!Files.exists(Nucleoplasm.recipe)) {
            try {
                Files.createDirectories(Nucleoplasm.recipe);
            } catch (IOException e) {
                LOGGER.error("error create .minecraft/json/recipes ", e);
            }
            map.forEach((id, json) -> {
                Path path = Nucleoplasm.recipe
                    .resolve(id.getNamespace())
                    .resolve(id.getPath() + ".json");
                Path parent = path.getParent();
                if (!Files.exists(parent)) {
                    try {
                        Files.createDirectories(parent);
                    } catch (IOException e) {
                        LOGGER.error("don't create parent dir", e);
                    }
                }
                BufferedWriter bw;
                try {
                    bw = Files.newBufferedWriter(path);
                    bw.write(json.toString());
                    bw.close();
                } catch (IOException e) {
                    LOGGER.error("don't create json file", e);
                }

            });
        }
    }

    private static void init() {

    }
}

package union.xenfork.nucleoplasm.json.edit.mixin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.json.edit.RecipeGen;

import java.io.*;
import java.nio.file.Path;
import java.util.Map;

import static union.xenfork.nucleoplasm.json.edit.RecipeGen.dir;

@Mixin(RecipeManager.class)
public class MixinRecipeManage {
    @Shadow private Map<RecipeType<?>, Map<Identifier, Recipe<?>>> recipes;
    @Shadow @Final private static Gson GSON;

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"))
    private void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        if (!dir.toFile().exists()) {
            dir.toFile().mkdirs();
            for (var entry : map.entrySet()) {
                Path resolve = dir.resolve(entry.getKey().getNamespace());
                if (!resolve.toFile().exists()) resolve.toFile().mkdirs();
                Path resolve1 = resolve.resolve(entry.getKey().getPath() + ".json");
                try {
                    resolve1.toFile().createNewFile();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(resolve1.toFile()));
                    bw.write(entry.getValue().toString());
                    bw.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        map.clear();
        File[] files = dir.toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                String[] split = file.getPath().replace("\\", "/").split("/");
                String namespace = split[split.length - 1];
                File[] files1 = file.listFiles();
                if (files1 != null) {
                    for (File listFile : files1) {
                        String[] split2 = listFile.getPath().replace("\\", "/").split("/");
                        String path = split2[split2.length - 1].replace(".json", "").trim();
                        try {
                            JsonReader jr = new JsonReader(new FileReader(listFile));
                            JsonElement parse = Streams.parse(jr);
                            map.put(Identifier.of(namespace, path), parse);
                        } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
    }
}

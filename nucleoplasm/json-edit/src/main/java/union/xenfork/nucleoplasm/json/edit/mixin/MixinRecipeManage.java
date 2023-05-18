package union.xenfork.nucleoplasm.json.edit.mixin;

import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static union.xenfork.nucleoplasm.json.edit.Nucleoplasm.recipe;

@Mixin(RecipeManager.class)
public class MixinRecipeManage {
    private static final Map<Identifier, File> iFile = new HashMap<>();
    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"))
    private void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        if (!recipe.toFile().exists()) recipe.toFile().mkdirs();
        for (var entry : map.entrySet()) {
            Path resolve = recipe.resolve(entry.getKey().getNamespace());
            if (!resolve.toFile().exists()) resolve.toFile().mkdirs();
            Path resolve1 = resolve.resolve(entry.getKey().getPath() + ".json");
            File file = resolve1.toFile();
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(entry.getValue().toString());
                    bw.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        map.clear();
        iFile.clear();
        load(recipe.toFile(), new StringBuilder());
        iFile.forEach((identifier, file) -> {
            try {
                JsonReader jr = new JsonReader(new FileReader(file));
                JsonElement parse = Streams.parse(jr);
                map.put(identifier, parse);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

        });
    }

    public void load(File file, StringBuilder namespace) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    StringBuilder sb = new StringBuilder(namespace);
                    String[] split = file1.getPath().split("\\\\");
                    sb.append("_").append(split[split.length - 1]);
                    load(file1, sb);
                } else {
                    String[] split = file1.getPath().split("\\\\");
                    iFile.put(Identifier.of(namespace.toString(), split[split.length - 1].replace(".json", "")), file1);
                }
            }
        }
    }
}

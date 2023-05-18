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
import java.util.Map;

import static union.xenfork.nucleoplasm.json.edit.Nucleoplasm.recipes;

@Mixin(RecipeManager.class)
public class MixinRecipeManage {

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"))
    private void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        if (!recipes.toFile().exists()) recipes.toFile().mkdirs();
        for (var entry : map.entrySet()) {
            Path resolve = recipes.resolve(entry.getKey().getNamespace());
            if (!resolve.toFile().exists()) {
                resolve.toFile().mkdirs();
                Path resolve1 = resolve.resolve(entry.getKey().getPath() + ".json");
                File file = resolve1.toFile();
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
        findFile(recipes.toFile(), "", map);
//        File[] files = recipes.toFile().listFiles();
//        if (files != null) {
//            for (File file : files) {
//                String namespace = file.getPath().substring(file.getPath().lastIndexOf(File.pathSeparatorChar) + 1);
////                String[] split = file.getPath().replace("\\", "/").split("/");
////                String namespace = split[split.length - 1];
//                File[] files1 = file.listFiles();
//                if (files1 != null) {
//                    for (File listFile : files1) {
//                        String path = listFile.getPath().substring(file.getPath().lastIndexOf(File.pathSeparatorChar) + 1).replace(".json", "").trim();
////                        String[] split2 = listFile.getPath().replace("\\", "/").split("/");
////                        String path = split2[split2.length - 1].replace(".json", "").trim();
//                        try {
//                            JsonReader jr = new JsonReader(new FileReader(listFile));
//                            JsonElement parse = Streams.parse(jr);
//                            map.put(Identifier.of(namespace, path), parse);
//                        } catch (FileNotFoundException e) {
//                            System.out.println(e.getMessage());
//                        }
//                    }
//                }
//            }
//        }
    }

    public void findFile(File file, String namespace, Map<Identifier, JsonElement> map) {
        String namespaceT = file.getPath().substring(file.getPath().lastIndexOf(File.pathSeparatorChar) + 1);
        StringBuilder sb = new StringBuilder(namespace);
        if (file.isDirectory()) {
            sb.append("_").append(namespaceT);
            File[] files = file.listFiles();
            if (files != null) {
                for (File listFile : files) {
                    findFile(listFile, sb.toString(), map);
                }
            }
        } else {
            String path = file.getPath().substring(file.getPath().lastIndexOf(File.pathSeparatorChar) + 1).replace(".json", "").trim();
//                        String[] split2 = listFile.getPath().replace("\\", "/").split("/");
//                        String path = split2[split2.length - 1].replace(".json", "").trim();
            try {
                JsonReader jr = new JsonReader(new FileReader(file));
                JsonElement parse = Streams.parse(jr);
                map.put(Identifier.of(sb.toString(), path), parse);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

package union.xenfork.nucleoplasm.json.edit.mixin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static union.xenfork.nucleoplasm.json.edit.Nucleoplasm.config;
import static union.xenfork.nucleoplasm.json.edit.Nucleoplasm.recipe;

@Mixin(JsonDataLoader.class)
public class MixinDataJsonLoader {
    @Shadow
    @Final
    private static Logger LOGGER;

//    @Shadow
//    @Final
//    private Gson gson;
//    private static final Map<String, Boolean> hasCreate = new HashMap<>();



//    private static void validate(String dataType) {
//        try(Stream<Path> list = Files.list(Nucleoplasm.dir)) {
//            List<Path> listList = list.toList();
//            for (Path path : listList)
//                try (Stream<Path> list1 = Files.list(path)) {
//                    List<Path> list1List = list1.toList();
//                    for (Path path1 : list1List)
//                        if (path1.toString().contains(dataType))
////                            hasCreate.put(dataType, true);
//                }
//        } catch (IOException e) {
//            LOGGER.error("fail to validate", e);
//        }
//
//    }

    @Shadow
    @Final
    private Gson gson;

    @Inject(method = "load", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private static void load(ResourceManager manager, String dataType, Gson gson, Map<Identifier, JsonElement> results, CallbackInfo ci) {
        if (dataType.contains("recipes")) {
            preRecipes(manager, dataType);
            results.clear();
            loadRecipes(recipe, recipe, gson, results);
            ci.cancel();
        }
    }

    private static void loadRecipes(Path first, Path get, Gson gson, Map<Identifier, JsonElement> results) {
        try(Stream<Path> list = Files.list(get)) {
            for (Path path : list.toList()) {
                if (Files.isDirectory(path)) {
                    loadRecipes(first, path, gson, results);
                } else {
                    String replace = path.toString()
                        .replace(first.toString(), "")
                        .replace("\\", "/")
                        .replace(".json", "");
                    String id_name = replace.substring(0, replace.indexOf("/"));
                    String id_path = replace.substring(replace.indexOf("/") + 1);
                    Identifier id = new Identifier(id_name, id_path);
                    try (BufferedReader reader = Files.newBufferedReader(path)) {
                        JsonElement jsonElement = JsonHelper.deserialize(gson, reader, JsonElement.class);
                        JsonElement jsonElement2 = results.put(id, jsonElement);
                        if (jsonElement2 != null) {
                            throw new IllegalStateException("Duplicate data file ignored with ID ");
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("fail to list {}", get);
        }
    }

    private static void preRecipes(ResourceManager manager, String dataType) {
        ResourceFinder finder = ResourceFinder.json(dataType);
        List<String> namespaces = new ArrayList<>();
        String namespace;
        Map<Identifier, Resource> resources = finder.findResources(manager);
        for (Map.Entry<Identifier, Resource> entry : resources.entrySet()) {
            Identifier id = entry.getKey();
            Resource res = entry.getValue();
            namespace = id.getNamespace();
            if (!namespaces.contains(namespace)) namespaces.add(namespace);
            if (!config.containsKey(namespace + "_" + dataType + "_" + "loaded")) {
                if (!Files.exists(recipe)) try {
                    Files.createDirectories(recipe);
                } catch (IOException e) {
                    LOGGER.error("don't create dir {}", recipe);
                }

                Path path = recipe.resolve(id.getNamespace()).resolve(id.getPath());
                Path parent = path.getParent();
                if (!Files.exists(parent)) {
                    try {
                        Files.createDirectories(parent);
                    } catch (IOException e) {
                        LOGGER.error("fail to create dir {}", parent);
                    }
                }
                try {
                    var reader = res.getInputStream();
                    Files.copy(reader, path);
                } catch (IOException e) {
                    LOGGER.error("fail load reader to {}", res);
                }
            }
        }
        for (String space : namespaces) {
            config.set(space + "_" + dataType + "_" + "loaded", true);
        }
    }
}


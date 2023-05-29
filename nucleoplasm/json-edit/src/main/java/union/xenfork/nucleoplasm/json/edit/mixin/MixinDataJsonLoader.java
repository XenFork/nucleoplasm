package union.xenfork.nucleoplasm.json.edit.mixin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.mixin.resource.conditions.SinglePreparationResourceReloaderMixin;
import net.minecraft.loot.LootManager;
import net.minecraft.resource.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.json.edit.Nucleoplasm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Mixin(JsonDataLoader.class)
public class MixinDataJsonLoader extends MixinSinglePreparationResourceReloader {
    @Shadow
    @Final
    private static Logger LOGGER;
    @Shadow
    @Final
    private String dataType;
    @Shadow
    @Final
    private Gson gson;

    @SuppressWarnings("unchecked")
    @Override
    protected void nucleoplasm_synchronizer_lambda(ResourceManager resourceManager, Profiler profiler, Object prepared) {
        profiler.push("Fabric resource conditions: %s".formatted(dataType));
        Map<Identifier, JsonElement> prepared1 = (Map<Identifier, JsonElement>) prepared;
        prepared1.forEach((id, json) -> {
            System.out.println(id);
        });
    }

    private static final Map<String, Boolean> hasCreate = new HashMap<>();



    private static void validate(String dataType) {
        try(Stream<Path> list = Files.list(Nucleoplasm.dir)) {
            List<Path> listList = list.toList();
            for (Path path : listList)
                try (Stream<Path> list1 = Files.list(path)) {
                    List<Path> list1List = list1.toList();
                    for (Path path1 : list1List)
                        if (path1.toString().contains(dataType))
                            hasCreate.put(dataType, true);
                }
        } catch (IOException e) {
            LOGGER.error("fail to validate", e);
        }

    }

    @Inject(method = "load", at = @At("HEAD"), cancellable = true)
    private static void load(ResourceManager manager, String dataType, Gson gson, Map<Identifier, JsonElement> results, CallbackInfo ci) {
        ResourceFinder resourceFinder1 = ResourceFinder.json(dataType);
        Map<Identifier, Resource> resources = resourceFinder1.findResources(manager);
        validate(dataType);
        resources.forEach((id, __) -> LOGGER.error(id + __.toString()));
        LOGGER.info("success");
        LOGGER.info("success");
        LOGGER.info("success");

        if (!hasCreate.containsKey(dataType)) {
            resources.forEach((id, resource) -> {
                Path namespacePath = Nucleoplasm.dir.resolve(id.getNamespace());
                Path pathPath = namespacePath.resolve(id.getPath());
                Path parent = pathPath.getParent();
                try {
                    Files.createDirectories(namespacePath);
                } catch (IOException e) {LOGGER.error("fail to load files", e);}
                try {
                    Files.createDirectories(parent);
                } catch (IOException e) {LOGGER.error("fail to load", e);}
                try {
                    Files.createFile(pathPath);
                } catch (IOException e) {LOGGER.error("fail to load", e);}
                try {
                    BufferedReader reader = resource.getReader();
                    StringBuilder sb = new StringBuilder();
                    reader.lines().forEach(str -> sb.append(str).append("\n"));
                    try (BufferedWriter br = Files.newBufferedWriter(pathPath)) {
                        br.write(sb.toString());
                    }
                } catch (IOException e) {LOGGER.error("error to read", e);}
            });
            hasCreate.put(dataType, true);

        }
        try(Stream<Path> list = Files.list(Nucleoplasm.dir)) {
            List<Path> listList = list.toList();
            for (Path path : listList) {
                try(Stream<Path> list1 = Files.list(path)) {
                    List<Path> list1List = list1.toList();
                    for (Path path1 : list1List) {
                        if (path1.toString().contains(dataType)) {
                            if (dataType.contains("recipes")) {
                                initRecipe(path1, path1, gson, results);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("fail to init");
        }
        if (dataType.contains("recipes")) {
            ci.cancel();
        }

    }

    private static void initRecipe(Path dataType, Path path, Gson gson, Map<Identifier, JsonElement> results) {
        try(Stream<Path> list = Files.list(path)) {
            List<Path> listList = list.toList();
            for (Path path1 : listList) {
                if (Files.isDirectory(path1)) {
                    initRecipe(dataType, path1, gson, results);
                } else {
                    String[] split = dataType.getParent().toString().replace("\\", "/").split("/");
                    Identifier identifier = new Identifier(split[split.length - 1],path1.toString().replace(dataType.toString(), "").replace("\\", "/").substring(1));
                    if (dataType.toString().contains("loot_tables")) {
                        System.out.println(identifier);
                    }
                    try (Reader reader = Files.newBufferedReader(path1)) {
                        JsonElement deserialize = JsonHelper.deserialize(gson, reader, JsonElement.class);
                        JsonElement put = results.put(identifier, deserialize);
                        if (put != null) {
                            throw new IllegalStateException("Duplicate data file ignored with ID " + put);
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("fail load json", e);
        }
    }
}


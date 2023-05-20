package union.xenfork.nucleoplasm.json.edit;

import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;

public class Nucleoplasm implements ModInitializer {
    public static final Logger logger = LoggerFactory.getLogger("nucleoplasm_json_edit");
    public static final Path dir = FabricLoader.getInstance().getGameDir().resolve("json");
    public static final Path recipe = dir.resolve("recipes");
    public static final Path loot_table = dir.resolve("loot_table");

    @Override
    public void onInitialize() {
    }

    public static void outputJson(Map<Identifier, JsonElement> map, Path defaultPath) {
        if (!Files.exists(defaultPath)) {
            try {
                Files.createDirectories(defaultPath);
            } catch (IOException e) {
                logger.error("Failed to output json files", e);
                return;
            }
        }
        map.forEach((id, json) -> {
            Path namespace = defaultPath.resolve(id.getNamespace());
            if (!Files.exists(namespace)) {
                try {
                    Files.createDirectory(namespace);
                } catch (IOException e) {
                    logger.error("Failed to output json files", e);
                    return;
                }
            }
            Path path = namespace.resolve(id.getPath() + ".json");
            if (!Files.exists(path)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
                    writer.write(json.toString());
                } catch (IOException e) {
                    logger.error("Failed to output json files", e);
                }
            }
        });
    }

    public static void loadRecipe(File file, StringBuilder namespace, StringBuilder path, Map<Identifier, File> iFile) {
        File[] files = file.listFiles();
        if (files != null && !Arrays.stream(files).toList().isEmpty()) {
            for (File file1 : files) {
                String replace = file
                    .getPath()
                    .replace("\\", "/")
                    .replace(file1.getParent(), "")
                    .replace("/", "");
                if (file1.isDirectory()) {
                    if (namespace == null) {
                        namespace = new StringBuilder();
                        namespace.append(replace);
                    } else if (path == null) {
                        path = new StringBuilder();
                        path.append(replace);
                    } else {
                        path.append("_").append(replace);
                    }
                } else {
                    if (namespace == null) namespace = new StringBuilder("null");
                    if (path == null) path = new StringBuilder(replace.replace(".json", ""));
                    else path.append("_").append(replace);
                }

            }
        }
    }

    public static void parserRecipe(Map<Identifier, File> iFile, Map<Identifier, JsonElement> map) {
        iFile.forEach((identifier, file) -> {
            try {
                JsonReader jr = new JsonReader(new FileReader(file));
                JsonElement parse = Streams.parse(jr);
                map.put(identifier, parse);
            } catch (FileNotFoundException e) {
                logger.error("Failed to read" + file, e);
                System.out.println(e.getMessage());
            }
        });
    }
}

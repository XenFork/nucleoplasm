package union.xenfork.nucleoplasm.json.edit;

import com.google.gson.JsonElement;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

//    public static void load(File file, StringBuilder namespace,Map<Identifier, File> iFile) {
//        File[] files = file.listFiles();
//        if (files != null) {
//            for (File file1 : files) {
//                if (file1.isDirectory()) {
//                    StringBuilder sb = new StringBuilder(namespace);
//                    String[] split = file1.getPath().split("\\\\");
//                    sb.append("_").append(split[split.length - 1]);
//                    load(file1, sb, iFile);
//                } else {
//                    String[] split = file1.getPath().split("\\\\");
//                    iFile.put(Identifier.of(namespace.toString(), split[split.length - 1].replace(".json", "")), file1);
//                }
//            }
//        }
//    }
}

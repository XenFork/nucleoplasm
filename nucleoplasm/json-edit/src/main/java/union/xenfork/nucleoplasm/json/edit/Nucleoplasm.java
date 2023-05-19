package union.xenfork.nucleoplasm.json.edit;

import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import java.io.*;
import java.nio.file.Path;
import java.util.Map;

public class Nucleoplasm implements ModInitializer {
    public static final Path dir = FabricLoader.getInstance().getGameDir().resolve("json");
    public static final Path recipe = dir.resolve("recipes");
    public static final Path loot_table = dir.resolve("loot_table");
    @Override
    public void onInitialize() {
    }

    public static void outputJson(Map<Identifier, JsonElement> map, Path default_path) {
        if (!default_path.toFile().exists()){
            default_path.toFile().mkdirs();
            map.forEach((id, json) -> {
                Path namespace = default_path.resolve(id.getNamespace());
                if (!namespace.toFile().exists()) namespace.toFile().mkdirs();
                Path path = namespace.resolve(id.getPath() + ".json");
                if (!path.toFile().exists()) {
                    try {
                        path.toFile().createNewFile();
                        FileWriter out = new FileWriter(path.toFile());
                        BufferedWriter bw = new BufferedWriter(out);
                        bw.write(json.toString());
                        bw.close();
                        out.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
        }

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

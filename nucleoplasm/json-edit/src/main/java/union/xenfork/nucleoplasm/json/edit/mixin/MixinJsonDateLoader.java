package union.xenfork.nucleoplasm.json.edit.mixin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.json.edit.Nucleoplasm;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Mixin(JsonDataLoader.class)
public class MixinJsonDateLoader {

    @Inject(method = "load", at = @At(value = "RETURN"))
    private static void load(ResourceManager manager, String dataType, Gson gson, Map<Identifier, JsonElement> results, CallbackInfo ci) {
        System.out.println(dataType);
        Path t = Nucleoplasm.dir.resolve(dataType);
        if (!t.toFile().exists()) {
            t.toFile().mkdirs();
            results.forEach((identifier, jsonElement) -> {
                System.out.println(identifier);
                String namespace = identifier.getNamespace();
                Path namespace_ = t.resolve(namespace);
                if (!namespace_.toFile().exists()) namespace_.toFile().mkdirs();
                String path = identifier.getPath();
                Path path_ = namespace_;
                String[] split = path.split("/");
                for (int i = 0; i < split.length; i++) {
                    String s = split[i];
                    if (i == split.length - 1) {
                        path_ = path_.resolve(s + ".json");
                    } else {
                        path_ = path_.resolve(s);
                    }
                }
                Path parent = path_.getParent();
                if (!parent.toFile().exists()) parent.toFile().mkdirs();
                if (!path_.toFile().exists()) {
                    try {
                        path_.toFile().createNewFile();
                        BufferedWriter bw = new BufferedWriter(new FileWriter(path_.toFile()));
                        bw.write(jsonElement.toString());
                        bw.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
        }

    }
}

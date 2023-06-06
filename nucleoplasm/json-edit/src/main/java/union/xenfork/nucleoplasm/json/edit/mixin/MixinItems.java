package union.xenfork.nucleoplasm.json.edit.mixin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.json.edit.Nucleoplasm;
import union.xenfork.nucleoplasm.json.edit.gson.ItemGson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Mixin(Items.class)
public class MixinItems {
    private static final Gson gson =
        new GsonBuilder()
            .setPrettyPrinting()
            .create();
    private static final Map<String, Item> map = new HashMap<>();

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void clinitReturn(CallbackInfo ci) {
        for (Field field : Items.class.getFields()) {
            field.setAccessible(true);
            try {
                Item o = (Item)field.get(null);
                String string = Registries.ITEM.getId(o).toString();
                if (!map.containsKey(string)) {
                    map.put(string, o);
                }
            } catch (IllegalAccessException e) {
                Nucleoplasm.logger.error("{} is don't load", field);
            }
        }
        map.forEach((id, item) -> {
            String pathString = id.replace(":", "/") + ".json";
            Path itemPath = Nucleoplasm.registry.resolve("item");
            var tPath = itemPath.resolve(pathString);
            var tParent = tPath.getParent();
            if (!Files.exists(tParent)) {
                try {
                    Files.createDirectories(tParent);
                } catch (IOException e) {
                    Nucleoplasm.logger.error("fail to create dir {}", tParent);
                }
            }
            try (BufferedWriter bw = Files.newBufferedWriter(tPath)) {
                ItemGson itemGson = new ItemGson(item);
                String json = gson.toJson(itemGson);
                bw.write(json);
//                ClassGson classGson = new ClassGson(item);
//                String json = gson.toJson(classGson);
//                bw.write(json);
            } catch (IOException e) {
                Nucleoplasm.logger.error("fail to create {}", tPath);
            }


        });
    }

    private static void createDir() {

    }

    private static Item get() {
        return null;
    }
}

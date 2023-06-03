package union.xenfork.nucleoplasm.json.edit.registry.item.def;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Item;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.SettingsLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.util.InitImpl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static union.xenfork.nucleoplasm.json.edit.registry.Util.gson;

/**
 * @author baka4n
 * {@link Item}
 */
public class ItemLoader implements InitImpl {
    @SerializedName("class_name")
    private String class_name;
    @SerializedName("id")
    private String identifier;
    @SerializedName("settings")
    private SettingsLoader settings;

    public ItemLoader(Item item, DefaultedRegistry<Item> registry) {
        class_name = item.getClass().getName();
        identifier = registry.getId(item).toString();
        settings = SettingsLoader.create(item, registry);
    }

    public static ItemLoader create(Item item, DefaultedRegistry<Item> registry) {
        return new ItemLoader(item, registry);
    }



    public String getClass_name() {
        return class_name;
    }

    public Identifier getIdentifier() {
        String[] split = identifier.split(":");
        return Identifier.of(split[0], split[1]);
    }

    public SettingsLoader getSettings() {
        return settings;
    }

    @Override
    public void init(Logger logger, Path path) {
        String json = gson.toJson(this);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }
}

package union.xenfork.nucleoplasm.json.edit.registry.item.util;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.MinecartItem;
import net.minecraft.item.SaddleItem;
import net.minecraft.registry.DefaultedRegistry;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static union.xenfork.nucleoplasm.json.edit.registry.Util.gson;

/**
 * @author baka4n
 * {@link MinecartItem}
 */
public class MinecartItemLoader extends ItemLoader {
    @SerializedName("type")
    private String type;
    public MinecartItemLoader(MinecartItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        type = item.type.name();
    }

    public static MinecartItemLoader create(MinecartItem item, DefaultedRegistry<Item> registry) {
        return new MinecartItemLoader(item, registry);
    }

    public AbstractMinecartEntity.Type getType() {
        return AbstractMinecartEntity.Type.valueOf(type);
    }

    public static void initMinecartItem(Logger logger, MinecartItem item, DefaultedRegistry<Item> registry, Path path) {
        MinecartItemLoader load = MinecartItemLoader.create(item, registry);
        String json = gson.toJson(load);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }
}

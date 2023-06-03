package union.xenfork.nucleoplasm.json.edit.registry.item.util;

import net.minecraft.item.Item;
import net.minecraft.item.PlaceableOnWaterItem;
import net.minecraft.registry.DefaultedRegistry;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.BlockItemLoader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static union.xenfork.nucleoplasm.json.edit.registry.Util.gson;

/**
 * @author baka4n
 * {@link PlaceableOnWaterItem}
 */
public class PlaceableOnWaterItemLoader extends BlockItemLoader {
    public PlaceableOnWaterItemLoader(PlaceableOnWaterItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }


    public static PlaceableOnWaterItemLoader create(PlaceableOnWaterItem item, DefaultedRegistry<Item> registry) {
        return new PlaceableOnWaterItemLoader(item, registry);
    }

    public static void initPlaceableOnWaterItem(Logger logger, PlaceableOnWaterItem item, DefaultedRegistry<Item> registry, Path path) {
        PlaceableOnWaterItemLoader load = PlaceableOnWaterItemLoader.create(item, registry);
        String json = gson.toJson(load);
        try(BufferedWriter bw  = Files.newBufferedWriter(path)) {
            bw.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }
}

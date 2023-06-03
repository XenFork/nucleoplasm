package union.xenfork.nucleoplasm.json.edit.registry.item.util;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static union.xenfork.nucleoplasm.json.edit.registry.Util.gson;

/**
 * @author baka4n
 * {@link AirBlockItem}
 */
public class AirBlockItemLoader extends ItemLoader {
    @SerializedName("block")
    private String block_identifier;

    public AirBlockItemLoader(AirBlockItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        block_identifier = Registries.BLOCK.getId(item.block).toString();
    }

    public static AirBlockItemLoader create(AirBlockItem item, DefaultedRegistry<Item> registry) {
        return new AirBlockItemLoader(item, registry);
    }

    public static void initAirBlockItem(Logger logger, AirBlockItem item, DefaultedRegistry<Item> registry, Path path) {
        AirBlockItemLoader load = AirBlockItemLoader.create(item, registry);
        String json = gson.toJson(load);
        try(BufferedWriter bw  = Files.newBufferedWriter(path)) {
            bw.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }

    public Identifier getBlock_identifier() {
        String[] split = block_identifier.split(":");
        return Identifier.of(split[0], split[1]);
    }
}

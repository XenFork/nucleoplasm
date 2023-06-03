package union.xenfork.nucleoplasm.json.edit.registry.item.util.e;

import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.util.InitImpl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static union.xenfork.nucleoplasm.json.edit.registry.Util.gson;

/**
 * @author baka4n
 * {@link ElytraItem}
 */
public class ElytraItemLoader extends ItemLoader {

    public ElytraItemLoader(ElytraItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }

    public static ElytraItemLoader create(ElytraItem item, DefaultedRegistry<Item> registry) {
        return new ElytraItemLoader(item, registry);
    }
}

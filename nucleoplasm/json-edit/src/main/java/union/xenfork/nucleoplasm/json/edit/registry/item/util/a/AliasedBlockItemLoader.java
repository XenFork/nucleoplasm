package union.xenfork.nucleoplasm.json.edit.registry.item.util.a;

import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.BlockItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.util.InitImpl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static union.xenfork.nucleoplasm.json.edit.registry.Util.gson;

/**
 * @author baka4n
 * {@link AliasedBlockItem}
 */
public class AliasedBlockItemLoader extends BlockItemLoader {
    public AliasedBlockItemLoader(AliasedBlockItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }

    public static AliasedBlockItemLoader create(AliasedBlockItem item, DefaultedRegistry<Item> registry) {
        return new AliasedBlockItemLoader(item, registry);
    }
}

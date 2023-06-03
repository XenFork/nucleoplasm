package union.xenfork.nucleoplasm.json.edit.registry.item.util;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.MinecartItem;
import net.minecraft.item.OnAStickItem;
import net.minecraft.registry.DefaultedRegistry;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.entity.EntityTypeLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static union.xenfork.nucleoplasm.json.edit.registry.Util.gson;

/**
 * @author baka4n
 * {@link OnAStickItem}
 */
public class OnAStickItemLoader extends ItemLoader {
    @SerializedName("target")
    private EntityTypeLoader target;
    @SerializedName("damagePerUse")
    private int damagePerUse;
    public OnAStickItemLoader(OnAStickItem<?> item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        target = EntityTypeLoader.create(item.target);
        damagePerUse = item.damagePerUse;
    }

    public static OnAStickItemLoader create(OnAStickItem<?> item, DefaultedRegistry<Item> registry) {
        return new OnAStickItemLoader(item, registry);
    }

    public static void initOnAStickItemLoader(Logger logger, OnAStickItem<?> item, DefaultedRegistry<Item> registry, Path path) {
        OnAStickItemLoader load = OnAStickItemLoader.create(item, registry);
        String json = gson.toJson(load);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }

    public EntityType<?> getTarget() {
        return target.getEntity_type();
    }

    public int getDamagePerUse() {
        return damagePerUse;
    }
}

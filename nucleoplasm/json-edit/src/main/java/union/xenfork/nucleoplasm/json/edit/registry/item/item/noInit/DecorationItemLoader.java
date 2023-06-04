package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.item.DecorationItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link DecorationItem}
 */
public class DecorationItemLoader extends ItemLoader {
    @SerializedName("type")
    private String type;
    public DecorationItemLoader(DecorationItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        type = Registries.ENTITY_TYPE.getId(item.entityType).toString();
    }

    @SuppressWarnings("unchecked")
    public EntityType<? extends AbstractDecorationEntity> getType() {
        String[] split = type.split(":");
        EntityType<?> entityType = Registries.ENTITY_TYPE.get(Identifier.of(split[0], split[1]));
        return (EntityType<? extends AbstractDecorationEntity>) entityType;
    }
}

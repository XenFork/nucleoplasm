package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.tool;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link ToolItem}
 */
public class ToolItemLoader extends ItemLoader {
    @SerializedName("type")
    private String type;
    public ToolItemLoader(ToolItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        ToolMaterials[] values = ToolMaterials.values();
        for (ToolMaterials value : values) {
            if (value.equals(item.getMaterial())) {
                type = value.name();
                break;
            }
        }
    }

    public ToolMaterial getToolMaterial() {
        return ToolMaterials.valueOf(type);
    }
}

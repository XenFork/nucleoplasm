package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.horsearmor;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link HorseArmorItem}
 */
public class HorseArmorItemLoader extends ItemLoader {
    @SerializedName("bonus")
    private int bonus;
    @SerializedName("entity_texture")
    private String entityTexture;
    public HorseArmorItemLoader(HorseArmorItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        bonus = item.getBonus();
        entityTexture = item.entityTexture.replace("textures/entity/horse/armor/horse_armor_", "").replace(".png", "");
    }
}

package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link SmithingTemplateItem}
 */
public class SmithingTemplateItemLoader extends ItemLoader {
    @SerializedName("title")
    private String titleText;
    public SmithingTemplateItemLoader(SmithingTemplateItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        titleText = item.titleText.getString();
    }
}

package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand;

import com.google.gson.annotations.SerializedName;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link BannerPatternItem}
 */
public class BannerPatternItemLoader extends ItemLoader {
    @SerializedName("patternItemTag")
    private String patternItemTag;
    public BannerPatternItemLoader(BannerPatternItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        patternItemTag = item.getPattern().id().toString();
    }

    public TagKey<BannerPattern> getTag() {
        String[] split = patternItemTag.split(":");
        return TagKey.of(RegistryKeys.BANNER_PATTERN, Identifier.of(split[0], split[1]));
    }
}

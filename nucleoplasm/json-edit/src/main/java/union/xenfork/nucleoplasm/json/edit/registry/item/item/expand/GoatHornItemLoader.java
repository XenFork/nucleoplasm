package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.GoatHornItem;
import net.minecraft.item.Instrument;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link GoatHornItem}
 */
public class GoatHornItemLoader extends ItemLoader {
    @SerializedName("tag")
    private String instrumentTag;
    public GoatHornItemLoader(GoatHornItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        instrumentTag =  item.instrumentTag.id().toString();
    }

    public TagKey<Instrument> getInstrumentTag() {
        return TagKey.of(RegistryKeys.INSTRUMENT, new Identifier(instrumentTag.split(":")));
    }
}

package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.PowderSnowBucketItem;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.BlockItemLoader;

/**
 * @author baka4n
 * {@link PowderSnowBucketItem}
 */
public class PowderSnowBucketItemLoader extends BlockItemLoader {
    @SerializedName("placeSound")
    private String placeSound;
    public PowderSnowBucketItemLoader(PowderSnowBucketItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        placeSound =  item.placeSound.getId().toString();
    }

    public SoundEvent getPlaceSound() {
        return Registries.SOUND_EVENT.get(new Identifier(placeSound.split(":")));
    }
}

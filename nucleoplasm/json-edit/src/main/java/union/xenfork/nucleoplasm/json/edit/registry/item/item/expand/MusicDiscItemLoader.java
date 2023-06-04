package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link MusicDiscItem}
 */
public class MusicDiscItemLoader extends ItemLoader {
    @SerializedName("comparator_output")
    private int comparatorOutput;
    @SerializedName("sound")
    private String sound;
    @SerializedName("length_in_ticks")
    private int lengthInTicks;
    public MusicDiscItemLoader(MusicDiscItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        comparatorOutput = item.getComparatorOutput();
        sound = item.getSound().getId().toString();
        lengthInTicks = item.getSongLengthInTicks();
    }

    public SoundEvent getSoundEvent() {
        return Registries.SOUND_EVENT.get(new Identifier(sound.split(":")));
    }
}

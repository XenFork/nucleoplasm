package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.bucket.expand;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.EntityType;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.bucket.BucketItemLoader;

/**
 * @author baka4n
 * {@link EntityBucketItem}
 */
public class EntityBucketItemLoader extends BucketItemLoader {
    @SerializedName("entity_type")
    private String entityType;
    @SerializedName("emptying_sound")
    private String emptyingSound;

    public EntityBucketItemLoader(EntityBucketItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        entityType = Registries.ENTITY_TYPE.getId(item.entityType).toString();
        emptyingSound = item.emptyingSound.getId().toString();
    }

    public EntityType<?> getEntityType() {
        return Registries.ENTITY_TYPE.get(new Identifier(entityType.split(":")));
    }

    public SoundEvent getSoundEvent() {
        return Registries.SOUND_EVENT.get(new Identifier(emptyingSound.split(":")));
    }
}

package union.xenfork.nucleoplasm.json.edit.registry.entity;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

/**
 * @author baka4n
 * {@link EntityType}
 */
public class EntityTypeLoader {
    @SerializedName("entity_type")
    private String entity_type;

    public EntityTypeLoader(EntityType<?> target) {
        entity_type = Registries.ENTITY_TYPE.getId(target).toString();
    }

    public static EntityTypeLoader create(EntityType<?> target) {
        return new EntityTypeLoader(target);
    }

    public EntityType<?> getEntity_type() {
        String[] split = entity_type.split(":");
        return Registries.ENTITY_TYPE.get(Identifier.of(split[0], split[1]));
    }
}

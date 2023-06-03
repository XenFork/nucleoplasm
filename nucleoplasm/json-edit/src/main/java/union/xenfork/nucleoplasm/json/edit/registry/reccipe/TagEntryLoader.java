package union.xenfork.nucleoplasm.json.edit.registry.reccipe;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class TagEntryLoader extends EntryLoader {
    @SerializedName("tag")
    private String tag;
    public TagEntryLoader(Ingredient.TagEntry entry) {
        super(entry.getStacks().toArray(new ItemStack[0]));
        TagKey<Item> tag = entry.tag;
        this.tag = tag.id().toString();
    }

    public <T> TagKey<T> getTagKey(RegistryKey<? extends Registry<T>> registryKey) {
        String[] split = tag.split(":");
        return TagKey.of(registryKey, Identifier.of(split[0], split[1]));
    }
}

package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.tools.expand.mining;

import com.google.gson.annotations.SerializedName;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.tools.ToolItemLoader;

/**
 * @author baka4n
 * {@link MiningToolItem}
 */
public class MiningToolItemLoader extends ToolItemLoader {
    @SerializedName("effectiveBlocks")
    private String tag;
    @SerializedName("miningSpeed")
    private float miningSpeed;
    @SerializedName("attackDamage")
    private  float attackDamage;
    public MiningToolItemLoader(MiningToolItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        tag = item.effectiveBlocks.id().toString();
        miningSpeed = item.miningSpeed;
        attackDamage = item.getAttackDamage();
    }

    public TagKey<Block> getEffectiveBlocks() {
        String[] split = tag.split(":");
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(split[0], split[1]));
    }
}

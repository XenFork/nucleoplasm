package union.xenfork.nucleoplasm.json.edit.registry.item.util.a;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.json.edit.registry.reccipe.IngredientLoader;

public class ArmorMaterialLoader {
    @SerializedName("name")
    private String name;
    @SerializedName("durability")
    private int durability;
    @SerializedName("protection")
    private int protection;
    @SerializedName("enchantability")
    private int enchantability;
    @SerializedName("equipSound")
    private String equipSound;
    @SerializedName("toughness")
    private float toughness;
    @SerializedName("knockbackResistance")
    private float knockbackResistance;
    @SerializedName("repairIngredient")
    private IngredientLoader repairIngredient;

    public ArmorMaterialLoader(ArmorItem item) {
        ArmorMaterial material = item.getMaterial();
        name = material.getName();
        durability = material.getDurability(item.getType());
        protection = material.getProtection(item.getType());
        enchantability = material.getEnchantability();
        Identifier id = Registries.SOUND_EVENT.getId(material.getEquipSound());
        if (id != null) equipSound = id.toString();
        toughness = material.getToughness();
        knockbackResistance = material.getKnockbackResistance();
        repairIngredient = new IngredientLoader(material.getRepairIngredient());
    }
}

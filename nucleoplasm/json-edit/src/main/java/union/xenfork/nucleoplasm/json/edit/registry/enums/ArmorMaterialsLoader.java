package union.xenfork.nucleoplasm.json.edit.registry.enums;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import oshi.util.tuples.Pair;
import union.xenfork.nucleoplasm.json.edit.registry.item.InitImpl;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.reccipe.IngredientLoader;

import java.util.ArrayList;

public class ArmorMaterialsLoader implements InitImpl {
    @SerializedName("type")
    private String type;
    @SerializedName("name")
    private String name;
    @SerializedName("durabilityMultiplier")
    private int durabilityMultiplier;
    @SerializedName("protection_amounts")
    private ArrayList<Pair<String, Integer>> protectionAmounts;
    @SerializedName("enchantability")
    private int enchantability;
    @SerializedName("equipSound")
    private String equipSound;
    @SerializedName("toughness")
    private float toughness;
    @SerializedName("knockbackResistance")
    private float knockbackResistance;
    @SerializedName("repairIngredientSupplier")
    private ArrayList<ItemLoader> repairIngredientSupplier;


    public ArmorMaterialsLoader(ArmorMaterials armorMaterials) {
        type = armorMaterials.name();
        name = armorMaterials.getName();
        durabilityMultiplier = armorMaterials.durabilityMultiplier;
        protectionAmounts = new ArrayList<>();
        armorMaterials.protectionAmounts.forEach((type1, integer) -> protectionAmounts.add(new Pair<>(type1.name(), integer)));
        enchantability = armorMaterials.getEnchantability();
        equipSound = armorMaterials.getEquipSound().getId().toString();
        toughness = armorMaterials.getToughness();
        knockbackResistance = (armorMaterials).getKnockbackResistance();
//        repairIngredientSupplier = new IngredientLoader(armorMaterials.getRepairIngredient());
    }
}

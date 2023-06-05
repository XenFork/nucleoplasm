package union.xenfork.nucleoplasm.json.edit.registry.enums;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import oshi.util.tuples.Pair;
import union.xenfork.nucleoplasm.json.edit.registry.face.InitImpl;

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
    @SerializedName("repairClassName")
    private String repairClassName;
    @SerializedName("repairIngredientSupplier")
    private String repairIngredientSupplier;


    public ArmorMaterialsLoader(ArmorMaterials armorMaterials) {
        this(armorMaterials, Items.class, armorMaterials.getName());
    }

    public ArmorMaterialsLoader(ArmorMaterials armorMaterials, Class<Items> item, String name) {
        type = armorMaterials.name();
        this.name = armorMaterials.getName();
        durabilityMultiplier = armorMaterials.durabilityMultiplier;
        protectionAmounts = new ArrayList<>();
        armorMaterials.protectionAmounts.forEach((type1, integer) -> protectionAmounts.add(new Pair<>(type1.name(), integer)));
        enchantability = armorMaterials.getEnchantability();
        equipSound = armorMaterials.getEquipSound().getId().toString();
        toughness = armorMaterials.getToughness();
        knockbackResistance = (armorMaterials).getKnockbackResistance();
        repairClassName = item.getName();
        repairIngredientSupplier = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getDurabilityMultiplier() {
        return durabilityMultiplier;
    }

    public ArrayList<Pair<String, Integer>> getProtectionAmounts() {
        return protectionAmounts;
    }

    public int getEnchantability() {
        return enchantability;
    }

    public String getEquipSound() {
        return equipSound;
    }

    public float getToughness() {
        return toughness;
    }

    public float getKnockbackResistance() {
        return knockbackResistance;
    }

    public String getRepairClassName() {
        return repairClassName;
    }

    public String getRepairIngredientSupplier() {
        return repairIngredientSupplier;
    }
}

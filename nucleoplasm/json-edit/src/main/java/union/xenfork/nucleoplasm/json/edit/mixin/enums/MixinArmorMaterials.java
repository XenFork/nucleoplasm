package union.xenfork.nucleoplasm.json.edit.mixin.enums;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oshi.util.tuples.Pair;
import union.xenfork.nucleoplasm.json.edit.Nucleoplasm;
import union.xenfork.nucleoplasm.json.edit.registry.Util;
import union.xenfork.nucleoplasm.json.edit.registry.enums.ArmorMaterialsLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Mixin(ArmorMaterials.class)
public class MixinArmorMaterials {

    MixinArmorMaterials(String field_name, int id, String name, int durabilityMultiplier, EnumMap protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier repairIngredientSupplier) {
        throw new AssertionError();
    }

    @Final
    @Shadow
    @Mutable
    private static ArmorMaterials[] field_7888;

    @Shadow
    @Final
    public EnumMap<ArmorItem.Type, Integer> protectionAmounts;

    static {
        Path armorMaterials = Nucleoplasm.dir.resolve("enums").resolve("materials").resolve("armor");
        if (!Files.exists(armorMaterials)) try {
            Files.createDirectories(armorMaterials);
        } catch (IOException ignored) {}
        if (!Nucleoplasm.config.containsKey("is_armor_material_loader") && Nucleoplasm.config.get("is_armor_material_loader") instanceof Boolean b && !b) {
            for (ArmorMaterials armorMaterial : field_7888) {
                ArmorMaterialsLoader loader;
                switch (armorMaterial.name()) {
                    case "IRON", "CHAIN", "GOLD", "NETHERITE" -> loader = new ArmorMaterialsLoader(armorMaterial, Items.class, (armorMaterial.name().equals("CHAIN") ? "IRON" : armorMaterial.name()) + "_INGOT");
                    case "TURTLE" -> loader = new ArmorMaterialsLoader(armorMaterial, Items.class, "SCUTE");
                    default -> loader = new ArmorMaterialsLoader(armorMaterial);
                }
                loader.init(Nucleoplasm.logger, armorMaterials.resolve(armorMaterial.name() + ".json"));
            }
            Nucleoplasm.config.put("is_armor_material_loader", true);
        } else {
            field_7888 = new ArrayList<ArmorMaterials>().toArray(new ArmorMaterials[0]);
        }
    }

    private static void loader(Path path) {
        try (Stream<Path> list = Files.list(path)) {
            for (Path path1 : list.toList()) {
                if (Files.isDirectory(path1)) {
                    loader(path1);
                } else {
                    try (BufferedReader bufferedReader = Files.newBufferedReader(path1)) {
                        ArmorMaterialsLoader loader = Util.gson.fromJson(bufferedReader, ArmorMaterialsLoader.class);
                        int ordinal = field_7888.length;
                        field_7888 = Arrays.copyOf(field_7888, ordinal + 1);
                        ArrayList<Pair<String, Integer>> protectionAmounts = loader.getProtectionAmounts();
                        EnumMap<ArmorItem.Type, Object> make = net.minecraft.util.Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                            for (Pair<String, Integer> protectionAmount : protectionAmounts) {
                                map.put(ArmorItem.Type.valueOf(protectionAmount.getA()), protectionAmount.getB());
                            }
                        });
                        field_7888[ordinal] = (ArmorMaterials) (Object)
                            new MixinArmorMaterials(
                                loader.getType(),
                                ordinal,
                                loader.getName(),
                                loader.getDurabilityMultiplier(),
                                make,
                                loader.getEnchantability(),
                                Registries.SOUND_EVENT.get(new Identifier(loader.getEquipSound().split(":"))),
                                loader.getToughness(),
                                loader.getKnockbackResistance(),
                                () -> {
                                    return Ingredient.ofItems(new ItemConvertible[]{Items.DIAMOND});
                                });

                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArmorMaterialsLoader get(Path path, Logger logger) {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(path);
            return Util.gson.fromJson(bufferedReader, ArmorMaterialsLoader.class);
        } catch (IOException e) { logger.error("fail to load {}", path); }
        return null;
    }

}

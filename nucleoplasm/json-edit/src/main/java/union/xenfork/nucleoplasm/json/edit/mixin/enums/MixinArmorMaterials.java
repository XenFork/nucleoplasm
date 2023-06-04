package union.xenfork.nucleoplasm.json.edit.mixin.enums;

import net.minecraft.item.ArmorMaterials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.Nucleoplasm;
import union.xenfork.nucleoplasm.json.edit.registry.enums.ArmorMaterialsLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Mixin(ArmorMaterials.class)
public class MixinArmorMaterials {
    @Final
    @Shadow
    @Mutable
    private static ArmorMaterials[] field_7888;

    static {
        Path armorMaterials = Nucleoplasm.dir.resolve("enums").resolve("materials").resolve("armor");
        if (Files.exists(armorMaterials)) try {
            Files.createDirectories(armorMaterials);
        } catch (IOException ignored) {}
        for (ArmorMaterials armorMaterial : field_7888) {
            ArmorMaterialsLoader loader = new ArmorMaterialsLoader(armorMaterial);
            loader.init(Nucleoplasm.logger, armorMaterials.resolve(armorMaterial.name()));
        }
    }

}

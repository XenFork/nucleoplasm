package union.xenfork.nucleoplasm.json.edit.mixin;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.json.edit.Nucleoplasm;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Mixin(StatusEffects.class)
public class MixinStatusEffects {
    private static final Map<String, StatusEffect> map = new HashMap<>();

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void clinit(CallbackInfo ci) {
        for (Field field : Items.class.getFields()) {
            field.setAccessible(true);
            try {
                StatusEffect o = (StatusEffect)field.get(null);
                String string = Objects.requireNonNull(Registries.STATUS_EFFECT.getId(o)).toString();
                if (!map.containsKey(string)) {
                    map.put(string, o);
                }
            } catch (IllegalAccessException e) {
                Nucleoplasm.logger.error("{} is don't load", field);
            }
        }
    }


}

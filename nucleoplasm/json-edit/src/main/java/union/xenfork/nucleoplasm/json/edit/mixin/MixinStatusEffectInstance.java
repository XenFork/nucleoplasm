package union.xenfork.nucleoplasm.json.edit.mixin;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectInstance.FactorCalculationData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.Get;
import union.xenfork.nucleoplasm.json.edit.gson.StatusEffectInstanceGson;

import java.util.Optional;

@Mixin(StatusEffectInstance.class)
public class MixinStatusEffectInstance implements Get<StatusEffectInstanceGson> {
    @Mutable
    @Shadow
    @Final
    private StatusEffect type;

    @Shadow
    private boolean ambient;

    @Shadow
    private int amplifier;

    @Shadow
    @Nullable
    private StatusEffectInstance hiddenEffect;

    @Shadow
    private int duration;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @Mutable
    @Shadow
    @Final
    private Optional<FactorCalculationData> factorCalculationData;

    @Shadow
    private boolean showIcon;

    @Shadow
    private boolean showParticles;

    @Override
    public StatusEffectInstanceGson get() {
        StatusEffectInstanceGson gson = new StatusEffectInstanceGson();
        gson.type = type;
        gson.ambient = ambient;
        gson.amplifier = amplifier;
        gson.hiddenEffect = hiddenEffect;
        gson.duration = duration;
        factorCalculationData.ifPresent(calculationData -> gson.factorCalculationData = calculationData);
        gson.showIcon = showIcon;
        gson.showParticles = showParticles;
        return gson;
    }

    @Override
    public void set(StatusEffectInstanceGson gson) {
        type = gson.type;
        ambient = gson.ambient;
        amplifier = gson.amplifier;
        hiddenEffect = gson.hiddenEffect;
        duration = gson.duration;
        factorCalculationData = Optional.of(gson.factorCalculationData);
        showIcon = gson.showIcon;
        showParticles = gson.showParticles;

    }
}

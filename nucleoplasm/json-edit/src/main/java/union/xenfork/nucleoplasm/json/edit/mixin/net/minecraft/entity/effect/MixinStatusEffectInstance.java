package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.net.minecraft.entity.effect.GetStatusEffectInstance;

import java.util.Optional;

@Mixin(StatusEffectInstance.class)
public class MixinStatusEffectInstance implements GetStatusEffectInstance {

    @Mutable
    @Shadow
    @Final
    private StatusEffect type;

    @Shadow
    private int duration;

    @Shadow
    private int amplifier;

    @Shadow
    private boolean ambient;

    @Shadow
    private boolean showParticles;

    @Shadow
    private boolean showIcon;

    @Shadow
    @Nullable
    private StatusEffectInstance hiddenEffect;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @Mutable
    @Shadow
    @Final
    private Optional<StatusEffectInstance.FactorCalculationData> factorCalculationData;

    @Override
    public StatusEffect getType() {
        return type;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getAmplifier() {
        return amplifier;
    }

    @Override
    public boolean getAmbient() {
        return ambient;
    }

    @Override
    public boolean getShowParticles() {
        return showParticles;
    }

    @Override
    public boolean getShowIcon() {
        return showIcon;
    }

    @Override
    public @Nullable StatusEffectInstance getHiddenEffect() {
        return hiddenEffect;
    }

    @Override
    public Optional<StatusEffectInstance.FactorCalculationData> getFactorCalculationData() {
        return factorCalculationData;
    }

    @Override
    public void setType(StatusEffect type) {
        this.type = type;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    @Override
    public void setShowParticles(boolean showParticles) {
        this.showParticles = showParticles;
    }

    @Override
    public void setShowIcon(boolean showIcon) {
        this.showIcon = showIcon;
    }

    @Override
    public void setHiddenEffect(@Nullable StatusEffectInstance hiddenEffect) {
        this.hiddenEffect = hiddenEffect;
    }

    @Override
    public void setFactorCalculationData(StatusEffectInstance.FactorCalculationData factorCalculationData) {
        this.factorCalculationData = Optional.of(factorCalculationData);
    }
}

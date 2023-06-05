package union.xenfork.nucleoplasm.json.edit.face;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.Optional;

public interface GetStatusEffectInstance {
    StatusEffect getType();
    int getDuration();
    int getAmplifier();
    boolean getAmbient();
    boolean getShowParticles();
    boolean getShowIcon();
    StatusEffectInstance getHiddenEffect();
    Optional<StatusEffectInstance.FactorCalculationData> getFactorCalculationData();
    void setType(StatusEffect type);
    void setDuration(int duration);
    void setAmplifier(int amplifier);
    void setShowParticles(boolean showParticles);
    void setShowIcon(boolean showIcon);
    void setHiddenEffect(StatusEffectInstance hiddenEffect);
    void setFactorCalculationData(StatusEffectInstance.FactorCalculationData factorCalculationData);
}

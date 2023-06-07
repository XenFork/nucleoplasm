package union.xenfork.nucleoplasm.json.edit.gson;

import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.Nullable;

public class StatusEffectInstanceGson {
    public String type;
    public int duration;
    public int amplifier;
    public boolean ambient;
    public boolean showParticles;
    public boolean showIcon;
    @Nullable
    public StatusEffectInstance hiddenEffect;
    public StatusEffectInstance.FactorCalculationData factorCalculationData;
}

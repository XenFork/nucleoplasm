package union.xenfork.nucleoplasm.json.edit.registry.effect;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;

import java.util.Optional;

/**
 * @author baka4n
 * {@link StatusEffectInstance}
 */
public class StatusEffectInstanceLoader {
    @SerializedName("effectType")
    private StatusEffectLoader type;
    @SerializedName("duration")
    private int duration;
    @SerializedName("amplifier")
    private int amplifier;
    @SerializedName("ambient")
    private boolean ambient;
    @SerializedName("showParticles")
    private boolean showParticles;
    @SerializedName("showIcon")
    private boolean showIcon;
    @SerializedName("hiddenEffect")
    private StatusEffectInstanceLoader hiddenEffect;
    @SerializedName("factorCalculationData")
    private FactorCalculationDataLoader factorCalculationData;

    public static StatusEffectInstanceLoader create(StatusEffectLoader type,
                                                    int duration,
                                                    int amplifier,
                                                    boolean showParticles,
                                                    boolean showIcon,
                                                    StatusEffectInstanceLoader hiddenEffect,
                                                    FactorCalculationDataLoader factorCalculationData) {
        StatusEffectInstanceLoader loader = new StatusEffectInstanceLoader();
        loader.type = type;
        loader.duration = duration;
        loader.amplifier = amplifier;
        loader.showParticles = showParticles;
        loader.showIcon = showIcon;
        loader.hiddenEffect = hiddenEffect;
        loader.factorCalculationData = factorCalculationData;
        return loader;
    }

    public static StatusEffectInstanceLoader create(StatusEffectInstance first) {
        StatusEffectInstanceLoader loader = new StatusEffectInstanceLoader();
        loader.type = StatusEffectLoader.create(first.getEffectType());
        loader.duration = first.getDuration();
        loader.amplifier = first.getAmplifier();
        loader.showParticles = first.shouldShowParticles();
        loader.showIcon = first.shouldShowIcon();
        if (first.hiddenEffect != null) loader.hiddenEffect = StatusEffectInstanceLoader.create(first.hiddenEffect);
        first.getFactorCalculationData().ifPresent(calculationData -> loader.factorCalculationData = FactorCalculationDataLoader.create(calculationData));
        return loader;
    }

    public StatusEffectInstance getStatusEffectInstance() {
        return new StatusEffectInstance(type.getStatusEffect(), duration, amplifier, ambient, showParticles, showIcon, hiddenEffect.getStatusEffectInstance(), Optional.of(factorCalculationData.getFactorCalculationData()));
    }
}

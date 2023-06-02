package union.xenfork.nucleoplasm.json.edit.registry.effect;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.effect.StatusEffectInstance;
import union.xenfork.nucleoplasm.json.edit.registry.item.noinit.FactorCalculationDataLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.noinit.StatusEffectLoader;

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

}

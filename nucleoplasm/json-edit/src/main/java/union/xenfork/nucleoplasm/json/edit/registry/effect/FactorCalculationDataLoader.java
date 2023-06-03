package union.xenfork.nucleoplasm.json.edit.registry.effect;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.effect.StatusEffectInstance.FactorCalculationData;

import java.util.Optional;

/**
 * @author baka4n
 * {@link FactorCalculationData}
 */
public class FactorCalculationDataLoader {
    @SerializedName("paddingDuration")
    private int paddingDuration;
    @SerializedName("factorStart")
    private float factorStart;
    @SerializedName("factorTarget")
    private float factorTarget;
    @SerializedName("factorCurrent")
    private float factorCurrent;
    @SerializedName("effectChangedTimestamp")
    private int effectChangedTimestamp;
    @SerializedName("factorPreviousFrame")
    private float factorPreviousFrame;
    @SerializedName("hadEffectLastTick")
    private boolean hadEffectLastTick;

    public static FactorCalculationDataLoader create(int paddingDuration,
                                                     float factorStart,
                                                     float factorTarget,
                                                     float factorCurrent,
                                                     int effectChangedTimestamp,
                                                     float factorPreviousFrame,
                                                     boolean hadEffectLastTick) {
        FactorCalculationDataLoader loader = new FactorCalculationDataLoader();
        loader.paddingDuration = paddingDuration;
        loader.factorStart = factorStart;
        loader.factorTarget = factorTarget;
        loader.factorCurrent = factorCurrent;
        loader.effectChangedTimestamp = effectChangedTimestamp;
        loader.factorPreviousFrame = factorPreviousFrame;
        loader.hadEffectLastTick = hadEffectLastTick;
        return loader;
    }

    public static FactorCalculationDataLoader create(FactorCalculationData factorCalculationData) {
        FactorCalculationDataLoader loader = new FactorCalculationDataLoader();
        loader.hadEffectLastTick = factorCalculationData.hadEffectLastTick;
        loader.factorTarget = factorCalculationData.factorTarget;
        loader.factorStart = factorCalculationData.factorStart;;
        loader.factorCurrent = factorCalculationData.factorCurrent;
        loader.paddingDuration = factorCalculationData.paddingDuration;
        loader.effectChangedTimestamp = factorCalculationData.effectChangedTimestamp;
        loader.factorPreviousFrame = factorCalculationData.factorPreviousFrame;
        return loader;
    }
}

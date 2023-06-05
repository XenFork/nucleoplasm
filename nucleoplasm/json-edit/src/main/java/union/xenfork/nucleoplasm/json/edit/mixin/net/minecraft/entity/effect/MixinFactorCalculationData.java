package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.entity.effect;

import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.net.minecraft.entity.effect.GetFactorCalculationData;

@Mixin(StatusEffectInstance.FactorCalculationData.class)
public class MixinFactorCalculationData implements GetFactorCalculationData {
    @Mutable
    @Shadow
    @Final
    private int paddingDuration;

    @Shadow
    private float factorStart;

    @Shadow
    private float factorTarget;

    @Shadow
    private float factorCurrent;

    @Shadow
    private int effectChangedTimestamp;

    @Shadow
    private float factorPreviousFrame;

    @Shadow
    private boolean hadEffectLastTick;

    @Override
    public int getPaddingDuration() {
        return paddingDuration;
    }

    @Override
    public float getFactorStart() {
        return factorStart;
    }

    @Override
    public float getFactorTarget() {
        return factorTarget;
    }

    @Override
    public float getFactorCurrent() {
        return factorCurrent;
    }

    @Override
    public int getEffectChangedTimestamp() {
        return effectChangedTimestamp;
    }

    @Override
    public float getFactorPreviousFrame() {
        return factorPreviousFrame;
    }

    @Override
    public boolean getHadEffectLastTick() {
        return hadEffectLastTick;
    }

    @Override
    public void setPaddingDuration(int paddingDuration) {
        this.paddingDuration = paddingDuration;
    }

    @Override
    public void setFactorStart(float factorStart) {
        this.factorStart = factorStart;
    }

    @Override
    public void setFactorTarget(float factorTarget) {
        this.factorTarget = factorTarget;
    }

    @Override
    public void setFactorCurrent(float factorCurrent) {
        this.factorCurrent = factorCurrent;
    }

    @Override
    public void setEffectChangedTimestamp(int effectChangedTimestamp) {
        this.effectChangedTimestamp = effectChangedTimestamp;
    }

    @Override
    public void setFactorPreviousFrame(float factorPreviousFrame) {
        this.factorPreviousFrame = factorPreviousFrame;
    }

    @Override
    public void setHadEffectLastTick(boolean hadEffectLastTick) {
        this.hadEffectLastTick = hadEffectLastTick;
    }
}

package union.xenfork.nucleoplasm.json.edit.face.net.minecraft.entity.effect;

public interface GetFactorCalculationData {
    int getPaddingDuration();
    float getFactorStart();
    float getFactorTarget();
    float getFactorCurrent();
    int getEffectChangedTimestamp();
    float getFactorPreviousFrame();
    boolean getHadEffectLastTick();
    void setPaddingDuration(int paddingDuration);
    void setFactorStart(float factorStart);
    void setFactorTarget(float factorTarget);
    void setFactorCurrent(float factorCurrent);
    void setEffectChangedTimestamp(int effectChangedTimestamp);
    void setFactorPreviousFrame(float factorPreviousFrame);
    void setHadEffectLastTick(boolean hadEffectLastTick);
}

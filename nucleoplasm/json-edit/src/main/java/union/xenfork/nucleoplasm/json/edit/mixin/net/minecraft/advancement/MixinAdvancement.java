package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.advancement;

import com.google.common.collect.ImmutableMap;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.net.minecraft.advancement.GetAdvancement;

import java.util.Map;

@Mixin(Advancement.class)
public abstract class MixinAdvancement implements GetAdvancement {

    @Mutable
    @Shadow
    @Final
    @Nullable
    private Advancement parent;

    @Mutable
    @Shadow
    @Final
    @Nullable
    private AdvancementDisplay display;

    @Mutable
    @Shadow
    @Final
    private AdvancementRewards rewards;

    @Mutable
    @Shadow
    @Final
    private Identifier id;

    @Mutable
    @Shadow
    @Final
    private Map<String, AdvancementCriterion> criteria;

    @Mutable
    @Shadow
    @Final
    private String[][] requirements;

    @Mutable
    @Shadow
    @Final
    private boolean sendsTelemetryEvent;

    @Shadow
    public abstract boolean sendsTelemetryEvent();

    @Override
    public @Nullable Advancement getParent() {
        return parent;
    }

    @Override
    public @Nullable AdvancementDisplay getDisplay() {
        return display;
    }

    @Override
    public AdvancementRewards getRewards() {
        return rewards;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public Map<String, AdvancementCriterion> getCriteria() {
        return criteria;
    }

    @Override
    public String[][] getRequirements() {
        return requirements;
    }

    @Override
    public boolean getSendsTelemetryEvent() {
        return sendsTelemetryEvent;
    }

    @Override
    public void setParent(@Nullable Advancement parent) {
        this.parent = parent;
    }

    @Override
    public void setDisplay(@Nullable AdvancementDisplay display) {
        this.display = display;
    }

    @Override
    public void setRewards(AdvancementRewards rewards) {
        this.rewards = rewards;
    }

    @Override
    public void setId(Identifier id) {
        this.id = id;
    }

    @Override
    public void setCriteria(Map<String, AdvancementCriterion> criteria) {
        this.criteria = ImmutableMap.copyOf(criteria);
    }

    @Override
    public void setRequirements(String[][] requirements) {
        this.requirements = requirements;
    }

    @Override
    public void setSendsTelemetryEvent(boolean sendsTelemetryEvent) {
        this.sendsTelemetryEvent = sendsTelemetryEvent;
    }
}

package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.advancement;

import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.CriterionConditions;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.net.minecraft.advancement.GetAdvancementCriterion;

@Mixin(AdvancementCriterion.class)
public class MixinAdvancementCriterion implements GetAdvancementCriterion {

    @Mutable
    @Shadow
    @Final
    @Nullable
    private CriterionConditions conditions;

    @Override
    public @Nullable CriterionConditions getConditions() {
        return conditions;
    }

    @Override
    public void setConditions(@Nullable CriterionConditions conditions) {
        this.conditions = conditions;
    }
}

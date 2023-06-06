package union.xenfork.nucleoplasm.json.edit.face.net.minecraft.advancement;

import net.minecraft.advancement.criterion.CriterionConditions;

public interface GetAdvancementCriterion {
    CriterionConditions getConditions();
    void setConditions(CriterionConditions conditions);
}

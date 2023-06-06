package union.xenfork.nucleoplasm.json.edit.face.net.minecraft.advancement;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.util.Identifier;

import java.util.Map;

public interface GetAdvancement {
    Advancement getParent();
    AdvancementDisplay getDisplay();
    AdvancementRewards getRewards();
    Identifier getId();
    Map<String, AdvancementCriterion> getCriteria();
    String[][] getRequirements();
    boolean getSendsTelemetryEvent();
    void setParent(Advancement parent);
    void setDisplay(AdvancementDisplay display);
    void setRewards(AdvancementRewards rewards);
    void setId(Identifier id);
    void setCriteria(Map<String, AdvancementCriterion> criteria);
    void setRequirements(String[][] requirements);
    void setSendsTelemetryEvent(boolean sendsTelemetryEvent);
}

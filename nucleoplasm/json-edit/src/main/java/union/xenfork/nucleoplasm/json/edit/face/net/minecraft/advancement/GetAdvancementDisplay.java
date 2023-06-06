package union.xenfork.nucleoplasm.json.edit.face.net.minecraft.advancement;

import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public interface GetAdvancementDisplay {
    void setTitle(Text title);
    void setDescription(Text description);
    void setIcon(ItemStack icon);
    void setBackground(Identifier background);
    void setFrame(AdvancementFrame frame);
    void setShowToast(boolean showToast);
    void setAnnounceToChat(boolean announceToChat);
    void setHidden(boolean hidden);
    void setX(float x);
    void setY(float y);
}

package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.advancement;

import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.net.minecraft.advancement.GetAdvancementDisplay;

@Mixin(AdvancementDisplay.class)
public class MixinAdvancementDisplay implements GetAdvancementDisplay {
    @Mutable
    @Shadow
    @Final
    private Text title;

    @Mutable
    @Shadow
    @Final
    private Text description;

    @Mutable
    @Shadow
    @Final
    private ItemStack icon;

    @Mutable
    @Shadow
    @Final
    @Nullable
    private Identifier background;

    @Mutable
    @Shadow
    @Final
    private AdvancementFrame frame;

    @Mutable
    @Shadow
    @Final
    private boolean showToast;

    @Mutable
    @Shadow
    @Final
    private boolean announceToChat;

    @Mutable
    @Shadow
    @Final
    private boolean hidden;

    @Shadow
    private float x;

    @Shadow
    private float y;

    @Override
    public void setTitle(Text title) {
        this.title = title;
    }

    @Override
    public void setDescription(Text description) {
        this.description = description;
    }

    @Override
    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }

    @Override
    public void setBackground(@Nullable Identifier background) {
        this.background = background;
    }

    @Override
    public void setFrame(AdvancementFrame frame) {
        this.frame = frame;
    }

    @Override
    public void setShowToast(boolean showToast) {
        this.showToast = showToast;
    }

    @Override
    public void setAnnounceToChat(boolean announceToChat) {
        this.announceToChat = announceToChat;
    }

    @Override
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }
}

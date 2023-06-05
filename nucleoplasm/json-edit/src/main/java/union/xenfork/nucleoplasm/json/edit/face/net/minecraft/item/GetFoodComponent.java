package union.xenfork.nucleoplasm.json.edit.face.net.minecraft.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.List;

public interface GetFoodComponent {
    int getHunger();
    float getSaturationModifier();
    boolean getMeat();
    boolean getAlwaysEdible();
    boolean getSnack();
    List<Pair<StatusEffectInstance, Float>> getStatusEffects();
    void setHunger(int hunger);
    void setSaturationModifier(float saturationModifier);
    void setMeat(boolean meat);
    void setAlwaysEdible(boolean alwaysEdible);
    void setSnack(boolean snack);
    void setStatusEffects(List<Pair<StatusEffectInstance, Float>> statusEffects);
}

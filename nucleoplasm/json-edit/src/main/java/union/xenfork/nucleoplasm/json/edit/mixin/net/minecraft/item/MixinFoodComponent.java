package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.GetFoodComponent;

import java.util.List;

@Mixin(FoodComponent.class)
public class MixinFoodComponent implements GetFoodComponent {
    @Mutable
    @Shadow
    @Final
    private int hunger;

    @Mutable
    @Shadow
    @Final
    private float saturationModifier;

    @Mutable
    @Shadow
    @Final
    private boolean meat;

    @Mutable
    @Shadow
    @Final
    private boolean alwaysEdible;

    @Mutable
    @Shadow
    @Final
    private boolean snack;

    @Mutable
    @Shadow
    @Final
    private List<Pair<StatusEffectInstance, Float>> statusEffects;

    @Override
    public int getHunger() {
        return hunger;
    }

    @Override
    public float getSaturationModifier() {
        return saturationModifier;
    }

    @Override
    public boolean getMeat() {
        return meat;
    }

    @Override
    public boolean getAlwaysEdible() {
        return alwaysEdible;
    }

    @Override
    public boolean getSnack() {
        return snack;
    }

    @Override
    public List<Pair<StatusEffectInstance, Float>> getStatusEffects() {
        return statusEffects;
    }

    @Override
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    @Override
    public void setSaturationModifier(float saturationModifier) {
        this.saturationModifier = saturationModifier;
    }

    @Override
    public void setMeat(boolean meat) {
        this.meat = meat;
    }

    @Override
    public void setAlwaysEdible(boolean alwaysEdible) {
        this.alwaysEdible = alwaysEdible;
    }

    @Override
    public void setSnack(boolean snack) {
        this.snack = snack;
    }

    @Override
    public void setStatusEffects(List<Pair<StatusEffectInstance, Float>> statusEffects) {
        this.statusEffects = statusEffects;
    }
}

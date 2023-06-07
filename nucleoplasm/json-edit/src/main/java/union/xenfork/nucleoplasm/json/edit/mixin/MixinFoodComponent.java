package union.xenfork.nucleoplasm.json.edit.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.Get;
import union.xenfork.nucleoplasm.json.edit.gson.FoodComponentGson;
import union.xenfork.nucleoplasm.json.edit.gson.StatusEffectInstanceGson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(FoodComponent.class)
public abstract class MixinFoodComponent implements Get<FoodComponentGson> {
    @Shadow
    public abstract int getHunger();

    @Shadow
    public abstract float getSaturationModifier();

    @Shadow
    public abstract boolean isMeat();

    @Shadow
    public abstract boolean isAlwaysEdible();

    @Shadow
    public abstract boolean isSnack();

    @Shadow
    public abstract List<Pair<StatusEffectInstance, Float>> getStatusEffects();

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
    public FoodComponentGson get() {
        FoodComponentGson fcg = new FoodComponentGson();
        fcg.hunger = getHunger();
        fcg.saturationModifier = getSaturationModifier();
        fcg.meat = isMeat();
        fcg.alwaysEdible = isAlwaysEdible();
        fcg.snack = isSnack();
        for (Pair<StatusEffectInstance, Float> statusEffect : getStatusEffects()) {
            if (fcg.statusEffects == null) fcg.statusEffects = new ArrayList<>();
            fcg.statusEffects.add(new Pair<>((StatusEffectInstanceGson) statusEffect.getFirst().get(), statusEffect.getSecond()));
        }
        return fcg;
    }

    @Override
    public void set(FoodComponentGson foodComponentGson) {
        hunger = foodComponentGson.hunger;
        saturationModifier = foodComponentGson.saturationModifier;
        meat = foodComponentGson.meat;
        alwaysEdible = foodComponentGson.alwaysEdible;
        snack = foodComponentGson.snack;
        ArrayList<Pair<StatusEffectInstance, Float>> statusEffects1 = new ArrayList<>();
        for (Pair<StatusEffectInstanceGson, Float> statusEffect : foodComponentGson.statusEffects) {
            statusEffects.add(new Pair<>(new StatusEffectInstance(statusEffect.getFirst().type, statusEffect.getFirst().duration, statusEffect.getFirst().amplifier, statusEffect.getFirst().ambient, statusEffect.getFirst().showParticles, statusEffect.getFirst().showIcon, statusEffect.getFirst().hiddenEffect, Optional.ofNullable(statusEffect.getFirst().factorCalculationData)), statusEffect.getSecond()));
        }
        statusEffects = statusEffects1;
    }
}

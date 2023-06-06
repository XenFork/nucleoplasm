package union.xenfork.nucleoplasm.json.edit.mixin.net.minecraft.advancement;

import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.json.edit.face.net.minecraft.advancement.GetAdvancementRewards;

@Mixin(AdvancementRewards.class)
public class MixinAdvancementRewards implements GetAdvancementRewards {
    @Mutable
    @Shadow
    @Final
    private int experience;

    @Mutable
    @Shadow
    @Final
    private Identifier[] loot;

    @Mutable
    @Shadow
    @Final
    private Identifier[] recipes;

    @Mutable
    @Shadow
    @Final
    private CommandFunction.LazyContainer function;

    @Override
    public int getExperience() {
        return experience;
    }

    @Override
    public Identifier[] getLoot() {
        return loot;
    }

    @Override
    public Identifier[] getRecipes() {
        return recipes;
    }

    @Override
    public CommandFunction.LazyContainer getFunction() {
        return function;
    }

    @Override
    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public void setLoot(Identifier[] loot) {
        this.loot = loot;
    }

    @Override
    public void setRecipes(Identifier[] recipes) {
        this.recipes = recipes;
    }

    @Override
    public void setFunction(CommandFunction.LazyContainer function) {
        this.function = function;
    }
}

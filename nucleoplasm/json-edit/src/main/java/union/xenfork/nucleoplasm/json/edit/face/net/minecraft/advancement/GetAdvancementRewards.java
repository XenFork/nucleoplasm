package union.xenfork.nucleoplasm.json.edit.face.net.minecraft.advancement;

import net.minecraft.server.function.CommandFunction;
import net.minecraft.util.Identifier;

public interface GetAdvancementRewards {
    int getExperience();
    Identifier[] getLoot();
    Identifier[] getRecipes();
    CommandFunction.LazyContainer getFunction();
    void setExperience(int experience);
    void setLoot(Identifier[] loot);
    void setRecipes(Identifier[] recipes);
    void setFunction(CommandFunction.LazyContainer function);
}

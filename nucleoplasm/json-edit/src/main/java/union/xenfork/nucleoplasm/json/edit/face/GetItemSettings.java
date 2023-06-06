package union.xenfork.nucleoplasm.json.edit.face;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;

public interface GetItemSettings {
    AssertionError null_ats = new AssertionError();
    default int maxCount() {
        throw null_ats;
    }
    default int maxDamage() {
        throw null_ats;
    }

    default @Nullable Item recipeRemainder() {
        throw null_ats;
    }
    default Rarity rarity() {
        throw null_ats;
    }
    default @Nullable FoodComponent foodComponent() {
        throw null_ats;
    }

    default boolean getFireproof() {
        throw null_ats;
    }

    default FeatureSet requiredFeatures() {
        throw null_ats;
    }
    default void maxCount(int maxCount) {}
    default void maxDamage(int maxDamage) {}
    default void recipeRemainder(@Nullable Item recipeRemainder) {}
    default void rarity(Rarity rarity) {}
    default void foodComponent(FoodComponent foodComponent){}
    default void fireproof(boolean fireproof) {}

    default void requiredFeatures(FeatureSet requiredFeatures) {}
}

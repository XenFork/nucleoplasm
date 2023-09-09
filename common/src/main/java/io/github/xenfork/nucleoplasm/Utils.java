package io.github.xenfork.nucleoplasm;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.JsonHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class Utils {
    public static Map<String, Ingredient> readSymbolsShaped(JsonObject json) {
        Map<String, Ingredient> map = Maps.newHashMap();

        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            try {
                map.put(entry.getKey(), fromJsonStack(entry.getValue(), false));
            } catch (CommandSyntaxException ignored) {}
        }


        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    public static Ingredient fromJsonStack(@Nullable JsonElement json, boolean allowAir) throws CommandSyntaxException {
        Ingredient ingredient = Ingredient.fromJson(json);
        if (json != null && !json.isJsonNull()) {
            JsonObject nbt = JsonHelper.asObject(json, "nbt");
            for (int i = 0; i < ingredient.getMatchingStacks().length; i++) {
                ItemStack matchingStack = ingredient.getMatchingStacks()[i];
                NbtCompound parse = StringNbtReader.parse(nbt.toString());
                matchingStack.setNbt(parse);
                ingredient.getMatchingStacks()[i] = matchingStack;
            }
        }
        return ingredient;
    }

}

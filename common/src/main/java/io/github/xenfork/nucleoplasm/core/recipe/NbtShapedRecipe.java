package io.github.xenfork.nucleoplasm.core.recipe;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.github.xenfork.nucleoplasm.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;

import java.util.Map;
import java.util.Set;

public class NbtShapedRecipe extends ShapedRecipe {
    public NbtShapedRecipe(Identifier id, String group, CraftingRecipeCategory category, int width, int height, DefaultedList<Ingredient> input, ItemStack output, boolean showNotification) {
        super(id, group, category, width, height, input, output, showNotification);
    }

    public NbtShapedRecipe(Identifier id, String group, CraftingRecipeCategory category, int width, int height, DefaultedList<Ingredient> input, ItemStack output) {
        super(id, group, category, width, height, input, output);
    }

    public static class Serializer implements RecipeSerializer<NbtShapedRecipe> {

        public Serializer() {
        }

        @Override
        public NbtShapedRecipe read(Identifier id, JsonObject json) {
            String group = JsonHelper.getString(json, "group", "");
            CraftingRecipeCategory category = CraftingRecipeCategory.CODEC.byId(JsonHelper.getString(json, "category", (String)null), CraftingRecipeCategory.MISC);

            Map<String, Ingredient> map = Utils.readSymbolsShaped(JsonHelper.getObject(json, "key"));
            String[] strings = NbtShapedRecipe.removePadding(NbtShapedRecipe.getPattern(JsonHelper.getArray(json, "pattern")));
            int i = strings[0].length();
            int j = strings.length;
            DefaultedList<Ingredient> defaultedList = NbtShapedRecipe.createPatternMatrix(strings, map, i, j);
            ItemStack itemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"));
            boolean bl = JsonHelper.getBoolean(json, "show_notification", true);
            return new NbtShapedRecipe(id, group, category, i, j, defaultedList, itemStack, bl);
        }

        @Override
        public NbtShapedRecipe read(Identifier id, PacketByteBuf buf) {
            return null;
        }

        @Override
        public void write(PacketByteBuf buf, NbtShapedRecipe recipe) {

        }
    }

    @VisibleForTesting
    static String[] removePadding(String... pattern) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;

        for(int m = 0; m < pattern.length; ++m) {
            String string = pattern[m];
            i = Math.min(i, findFirstSymbol(string));
            int n = findLastSymbol(string);
            j = Math.max(j, n);
            if (n < 0) {
                if (k == m) {
                    ++k;
                }

                ++l;
            } else {
                l = 0;
            }
        }

        if (pattern.length == l) {
            return new String[0];
        } else {
            String[] strings = new String[pattern.length - l - k];

            for(int o = 0; o < strings.length; ++o) {
                strings[o] = pattern[o + k].substring(i, j + 1);
            }

            return strings;
        }
    }

    private static int findLastSymbol(String pattern) {
        int i;
        i = pattern.length() - 1;
        while (i >= 0 && pattern.charAt(i) == ' ') {
            --i;
        }

        return i;
    }

    private static int findFirstSymbol(String line) {
        int i;
        i = 0;
        while (i < line.length() && line.charAt(i) == ' ') {
            ++i;
        }
        return i;
    }

    static String[] getPattern(JsonArray json) {
        String[] strings = new String[json.size()];
        if (strings.length > 3) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, 3 is maximum");
        } else if (strings.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        } else {
            for(int i = 0; i < strings.length; ++i) {
                String string = JsonHelper.asString(json.get(i), "pattern[" + i + "]");
                if (string.length() > 3) {
                    throw new JsonSyntaxException("Invalid pattern: too many columns, 3 is maximum");
                }

                if (i > 0 && strings[0].length() != string.length()) {
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                }

                strings[i] = string;
            }

            return strings;
        }
    }

    static DefaultedList<Ingredient> createPatternMatrix(String[] pattern, Map<String, Ingredient> symbols, int width, int height) {
        DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(width * height, Ingredient.EMPTY);
        Set<String> set = Sets.newHashSet(symbols.keySet());
        set.remove(" ");

        for(int i = 0; i < pattern.length; ++i) {
            for(int j = 0; j < pattern[i].length(); ++j) {
                String string = pattern[i].substring(j, j + 1);
                Ingredient ingredient = (Ingredient)symbols.get(string);
                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + string + "' but it's not defined in the key");
                }

                set.remove(string);
                defaultedList.set(j + width * i, ingredient);
            }
        }

        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        } else {
            return defaultedList;
        }
    }
}

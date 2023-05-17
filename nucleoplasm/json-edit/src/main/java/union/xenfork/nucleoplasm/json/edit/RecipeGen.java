package union.xenfork.nucleoplasm.json.edit;

import com.google.gson.Gson;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Consumer;

public class RecipeGen extends FabricRecipeProvider {

    public RecipeGen(FabricDataOutput output) {
        super(output);
    }
    public static final Gson gson = new Gson();
    public static final Path dir = FabricLoader.getInstance().getGameDir().resolve("json").resolve("edit");
    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

    }
}

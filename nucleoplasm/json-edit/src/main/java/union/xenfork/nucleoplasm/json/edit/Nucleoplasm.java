package union.xenfork.nucleoplasm.json.edit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class Nucleoplasm implements ModInitializer {
    public static final Path dir = FabricLoader.getInstance().getGameDir().resolve("json");
    public static final Path recipes = dir.resolve("recipes");
    @Override
    public void onInitialize() {

    }
}

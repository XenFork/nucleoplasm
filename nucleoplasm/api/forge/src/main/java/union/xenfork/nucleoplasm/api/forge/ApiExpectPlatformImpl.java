package union.xenfork.nucleoplasm.api.forge;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ApiExpectPlatformImpl {

    public static Path getGameDir() {
        return FMLPaths.GAMEDIR.get();
    }

    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static Path getModsDir() {
        return FMLPaths.MODSDIR.get();
    }
}

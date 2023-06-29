package union.xenfork.nucleoplasm.api;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class ApiExpectPlatform {
    @ExpectPlatform
    public static Path getGameDir() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Path getConfigDir() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Path getModsDir() {
        throw new AssertionError();
    }
}

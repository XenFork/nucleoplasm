package union.xenfork.nucleoplasm.api.config;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Config {
    private ConfigSerializes t;
    private static final Logger logger = LoggerFactory.getLogger("config");
    private Path p;
    public Config(String name) {
        p = FabricLoader.getInstance().getConfigDir().resolve(name);
        if (!Files.exists(p)) {
            t = ConfigSerializes.of(configSerializes -> {
                configSerializes.language = "en_us";
                configSerializes.dataFile = FabricLoader.getInstance().getGameDir().resolve("data").toFile();
                if (configSerializes.key == null) {
                    logger.info("please choose your Encryption Type,such as /i/r");
                    Scanner scanner = new Scanner(System.in);
                    configSerializes.key = scanner.next();
                }
            }, p);
        } else {
            try {
                t = (ConfigSerializes) new ConfigSerializes().load(p, false, null);
            } catch (IOException | ClassNotFoundException ignored) {}
        }
    }


}

package union.xenfork.nucleoplasm.json.edit.registry.face;

import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import org.slf4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static union.xenfork.nucleoplasm.json.edit.registry.Util.gson;

public interface InitImpl {
    default void init(Logger logger, Path path) {
        String json = gson.toJson(this);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }
}

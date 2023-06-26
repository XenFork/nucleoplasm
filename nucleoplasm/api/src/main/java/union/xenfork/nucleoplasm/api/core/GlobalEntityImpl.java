package union.xenfork.nucleoplasm.api.core;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import net.minecraft.world.GameMode;

import java.util.function.Consumer;

public class GlobalEntityImpl {
    private final Config config;

    public GlobalEntityImpl() {
        config = Config.of(c -> {
            c.name("global");
            c.cache(16L * 1024 * 1024);
        });
        try(DB db = QuickIO.usingDB(config)) {
            Collection<GlobalEntity> collection = db.collection(GlobalEntity.class);
            if (collection.findAll().isEmpty()) {
                collection.save(GlobalEntity.of(globalEntity -> {
                    globalEntity.gamemode = GameMode.SURVIVAL.name();
                }));
            }
        }
    }

    public void editGlobal(Consumer<GlobalEntity> consumer) {
        try(DB db = QuickIO.usingDB(config)) {
            Collection<GlobalEntity> collection = db.collection(GlobalEntity.class);
            GlobalEntity globalEntity = collection.findAll().get(0);
            consumer.accept(globalEntity);
            collection.save(globalEntity);
        }
    }
}

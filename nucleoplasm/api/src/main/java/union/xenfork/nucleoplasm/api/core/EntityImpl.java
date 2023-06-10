package union.xenfork.nucleoplasm.api.core;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.nio.file.Path;

@SuppressWarnings("DuplicatedCode")
public class EntityImpl implements SQLInterface {
    private final DB db;


    public EntityImpl(Path path) {
        Config config = Config.of(c -> {
            c.name("entity");
            c.path(path.toFile().getAbsolutePath());
            c.cache(16L * 1024 * 1024);
        });
        db = QuickIO.usingDB(config);
    }


    @Override
    public void save() {

    }

    @Override
    public void close() {
        db.close();
    }

    @Override
    public void create(ServerPlayerEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        collection.save(Entity.of(e -> {
            e.player_name = entity.getEntityName();
            e.uuid = entity.getUuid();
        }));
    }

    @Override
    public void login(ServerPlayerEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(entity.getEntityName()));
        if (one.uuid.compareTo(entity.getUuid()) != 0) one.uuid = entity.getUuid();
        collection.save(one);

    }

    @Override
    public void logout(ServerPlayerEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(entity.getEntityName()));
        if (one.uuid.compareTo(entity.getUuid()) != 0) one.uuid = entity.getUuid();
        collection.save(one);
    }

    @Override
    public void tick(ServerPlayerEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(entity.getEntityName()));
        if (one.uuid.compareTo(entity.getUuid()) != 0) one.uuid = entity.getUuid();
        collection.save(one);
    }

    @Override
    public void attackBlock(PlayerEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(entity.getEntityName()));
        if (one.uuid.compareTo(entity.getUuid()) != 0) one.uuid = entity.getUuid();
        collection.save(one);
    }

    @Override
    public void attackEntity(PlayerEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(entity.getEntityName()));
        if (one.uuid.compareTo(entity.getUuid()) != 0) one.uuid = entity.getUuid();
        collection.save(one);
    }

    @Override
    public void interactEntity(PlayerEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(entity.getEntityName()));
        if (one.uuid.compareTo(entity.getUuid()) != 0) one.uuid = entity.getUuid();
        collection.save(one);
    }

    @Override
    public void interactItem(PlayerEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(entity.getEntityName()));
        if (one.uuid.compareTo(entity.getUuid()) != 0) one.uuid = entity.getUuid();
        collection.save(one);
    }

    @Override
    public void interactBlock(PlayerEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(entity.getEntityName()));
        if (one.uuid.compareTo(entity.getUuid()) != 0) one.uuid = entity.getUuid();
        collection.save(one);
    }

    @Override
    public void blockBreak(PlayerEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(entity.getEntityName()));
        if (one.uuid.compareTo(entity.getUuid()) != 0) one.uuid = entity.getUuid();
        collection.save(one);
    }

    @Override
    public void pickupItem(PlayerEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(entity.getEntityName()));
        if (one.uuid.compareTo(entity.getUuid()) != 0) one.uuid = entity.getUuid();
        collection.save(one);
    }
}

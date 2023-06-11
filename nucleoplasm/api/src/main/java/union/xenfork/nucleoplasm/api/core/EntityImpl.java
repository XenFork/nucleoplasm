package union.xenfork.nucleoplasm.api.core;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

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
    public Entity find(PlayerEntity player) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(player.getEntityName()));
        collection.save(one);
        return one;
    }

    @Override
    public Entity find(ServerPlayerEntity player) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(player.getEntityName()));
        collection.save(one);
        return one;
    }

    @Override
    public void close(MinecraftServer server) {
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
        if (one == null) {
            create(entity);
            one = collection.findOne(e -> e.player_name.equals(entity.getEntityName()));
        }
        collection.save(one);
    }

    @Override
    public void logout(ServerPlayerEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(entity.getEntityName()));
        collection.save(one);
    }

    @Override
    public void tick(ServerPlayerEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(entity.getEntityName()));
        collection.save(one);
    }

    @Override
    public ActionResult take(ServerPlayerEntity player) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(player.getEntityName()));
        collection.save(one);
        return ActionResult.PASS;
    }

    @Override
    public ActionResult playerMove(ServerPlayerEntity player) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(player.getEntityName()));
        collection.save(one);
        return ActionResult.PASS;
    }

    @Override
    public ActionResult attackBlock(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(player.getEntityName()));
        collection.save(one);
        return ActionResult.PASS;
    }

    @Override
    public ActionResult attackEntity(PlayerEntity player, World world, Hand hand, net.minecraft.entity.Entity entity, @Nullable EntityHitResult hitResult) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(player.getEntityName()));
        collection.save(one);
        return ActionResult.PASS;
    }

    @Override
    public ActionResult interactEntity(PlayerEntity player, World world, Hand hand, net.minecraft.entity.Entity entity, @Nullable EntityHitResult hitResult) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(player.getEntityName()));
        collection.save(one);
        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> interactItem(PlayerEntity player, World world, Hand hand) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(player.getEntityName()));
        collection.save(one);
        return TypedActionResult.pass(player.getStackInHand(hand));
    }

    @Override
    public ActionResult interactBlock(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(player.getEntityName()));
        collection.save(one);
        return ActionResult.PASS;
    }

    @Override
    public boolean blockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(player.getEntityName()));
        collection.save(one);
        return true;
    }

    @Override
    public ActionResult pickupItem(PlayerEntity player, ItemEntity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(player.getEntityName()));
        collection.save(one);
        return ActionResult.PASS;
    }

    @Override
    public ActionResult dropItem(ServerPlayerEntity player) {
        Collection<Entity> collection = db.collection(Entity.class);
        Entity one = collection.findOne(e -> e.player_name.equals(player.getEntityName()));
        collection.save(one);
        return ActionResult.PASS;
    }
}

package union.xenfork.nucleoplasm.api.core;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import com.mojang.brigadier.ParseResults;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;
import java.util.List;

public class EntityImpl {
    private final DB db;
    private List<Entity> all;

    public EntityImpl(Path path) {
        Config config = Config.of(c -> {
            c.name("player");
            c.path(path.toFile().getAbsolutePath());
            c.cache(16L * 1024 * 1024);
        });
        db = QuickIO.usingDB(config);
        Collection<Entity> collection = db.collection(Entity.class);
        all = collection.findAll();
    }

    public Entity create(ServerPlayerEntity player) {
        Entity of = Entity.of(entity -> {
            entity.player_name = player.getEntityName();
            entity.uuid = player.getUuid();
        });
        all.add(of);
        return of;
    }

    public void update() {
        all = db.collection(Entity.class).findAll();
    }

    public Entity find(ServerPlayerEntity player) {
        List<Entity> list = all.stream().filter(entity -> entity.player_name.equals(player.getEntityName())).toList();
        return list.get(0);
    }

    public Entity find(PlayerEntity player) {
        List<Entity> list = all.stream().filter(entity -> entity.player_name.equals(player.getEntityName())).toList();
        return list.get(0);
    }

    public Entity find(String entityName) {
        List<Entity> list = all.stream().filter(entity -> entity.player_name.equals(entityName)).toList();
        return list.get(0);
    }

    private void save() {
        Collection<Entity> collection = db.collection(Entity.class);
        collection.save(all);
    }

    private void close() {
        db.close();
    }

    public ActionResult attackBlock(PlayerEntity entity, World world, Hand hand, BlockPos blockPos, Direction direction) {
        return ActionResult.PASS;
    }

    public void close(MinecraftServer server) {
        close();
    }

    public void save(MinecraftServer server) {
        save();
    }

    public ActionResult pickupItem(PlayerEntity entity, ItemEntity itemEntity) {
        return ActionResult.PASS;
    }

    public void tick(ServerPlayerEntity player) {

    }

    public void tick(ServerWorld world) {
        long timeReference = world.getServer().getTimeReference();
        if (timeReference % 10000 == 0) {
            save();
        }
    }

    public ActionResult dropItem(ServerPlayerEntity player, ItemStack stack) {
        return ActionResult.PASS;
    }

    public void logout(ServerPlayerEntity player) {

    }

    public void login(ServerPlayerEntity player) {
        List<Entity> list = all.stream().filter(entity -> entity.player_name.equals(player.getEntityName())).toList();
        if (list.isEmpty()) {
            Entity entity = create(player);
            all.add(entity);
        }
    }

    public boolean blockBreak(World world, PlayerEntity entity, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity) {
        return true;
    }

    public ActionResult interactBlock(PlayerEntity entity, World world, Hand hand, BlockHitResult blockHitResult) {
        return ActionResult.PASS;
    }

    public TypedActionResult<ItemStack> interactItem(PlayerEntity player, World world, Hand hand) {
        return TypedActionResult.pass(player.getStackInHand(hand));
    }

    public ActionResult interactEntity(PlayerEntity player, World world, Hand hand, net.minecraft.entity.Entity entity, @Nullable EntityHitResult entityHitResult) {
        return ActionResult.PASS;
    }

    public ActionResult attackEntity(PlayerEntity player, World world, Hand hand, net.minecraft.entity.Entity entity, @Nullable EntityHitResult entityHitResult) {
        return ActionResult.PASS;
    }

    public void execute(ParseResults<ServerCommandSource> parseResults, String command, CallbackInfoReturnable<Integer> cir) {

    }
}

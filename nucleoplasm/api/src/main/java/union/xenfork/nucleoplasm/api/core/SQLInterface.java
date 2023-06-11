package union.xenfork.nucleoplasm.api.core;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
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

public interface SQLInterface {
    union.xenfork.nucleoplasm.api.core.Entity find(PlayerEntity player);
    union.xenfork.nucleoplasm.api.core.Entity find(ServerPlayerEntity player);
    void close(MinecraftServer server);
    void create(ServerPlayerEntity entity);
    //玩家登录
    void login(ServerPlayerEntity entity);
    //玩家登出
    void logout(ServerPlayerEntity entity);
    //滴答
    void tick(ServerPlayerEntity entity);
    ActionResult take(ServerPlayerEntity player);
    ActionResult playerMove(ServerPlayerEntity player);
    //攻击方块
    ActionResult attackBlock(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction);
    //攻击实体
    ActionResult attackEntity(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult);
    //使用实体
    ActionResult interactEntity(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult);
    TypedActionResult<ItemStack> interactItem(PlayerEntity player, World world, Hand hand);
    ActionResult interactBlock(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult);
    boolean blockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity);
    ActionResult pickupItem(PlayerEntity player, ItemEntity entity);
    ActionResult dropItem(ServerPlayerEntity player);
}

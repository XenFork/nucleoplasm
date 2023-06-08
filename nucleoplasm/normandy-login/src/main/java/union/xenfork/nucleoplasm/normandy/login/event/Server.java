package union.xenfork.nucleoplasm.normandy.login.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.GameMode;
import union.xenfork.nucleoplasm.api.event.ItemEvents;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmEntity;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmLoader;
import union.xenfork.nucleoplasm.normandy.login.quickio.nnl.NNLPlayerEntity;

import java.util.List;
import java.util.Objects;

import static union.xenfork.nucleoplasm.normandy.login.NucleoplasmServer.nnlPlayerDB;

public class Server {
    public static void init(NucleoplasmLoader<NucleoplasmEntity> nnl) {
        worldTick(nnl);
        playerLoginOut(nnl);
        playerLogin(nnl);
        blockBreak(nnl);
        pickupItem(nnl);

        interactBlock(nnl);
        interactItem(nnl);
        interactEntity(nnl);
        attackEntity(nnl);
        attackBlock(nnl);
        serverStopped(nnl);
    }

    private static void attackBlock(NucleoplasmLoader<NucleoplasmEntity> nnl) {
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            var entity = nnl.findEntity((ServerPlayerEntity) player);
            if (!entity.is_login) return ActionResult.FAIL;
            return ActionResult.PASS;
        });//攻击方块时候取消事件
    }

    private static void attackEntity(NucleoplasmLoader<NucleoplasmEntity> nnl) {

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            var nnlPlayerEntity = nnl.findEntity((ServerPlayerEntity) player);
            if (!nnlPlayerEntity.is_login) return ActionResult.FAIL;
            return ActionResult.PASS;
        });//没有登录之前取消攻击实体的功能
    }

    /**
     * @since 使用实体回调
     */
    private static void interactEntity(NucleoplasmLoader<NucleoplasmEntity> nnl) {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            var nnlPlayerEntity = nnl.findEntity((ServerPlayerEntity) player);
            if (!nnlPlayerEntity.is_login) return ActionResult.FAIL;
            return ActionResult.PASS;
        });//使用实体的时候取消事件
    }

    /**
     * @since 使用物品事件
     */
    private static void interactItem(NucleoplasmLoader<NucleoplasmEntity> nnl) {
        UseItemCallback.EVENT.register((player, world, hand) -> {

            var entity = nnl.findEntity((ServerPlayerEntity) player);
            if (!entity.is_login) return TypedActionResult.fail(player.getStackInHand(hand));
            return TypedActionResult.pass(player.getStackInHand(hand));
        });//使用物品的时候取消事件
    }

    /**
     * @since 使用方块回调
     */
    private static void interactBlock(NucleoplasmLoader<NucleoplasmEntity> nnl) {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            var entity = nnl.findEntity((ServerPlayerEntity) player);
            if (!entity.is_login) return ActionResult.FAIL;
            return ActionResult.PASS;
        });//使用方块取消事件
    }

    /**
     * @since 破坏方块事件
     */
    private static void blockBreak(NucleoplasmLoader<NucleoplasmEntity> nnl) {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            NucleoplasmEntity entity = nnl.findEntity((ServerPlayerEntity) player);
            return entity.is_login;
        });//没有登录之前取消方块破坏
    }

    private static void playerLogin(NucleoplasmLoader<NucleoplasmEntity> nnl) {
        ServerPlayerEvents.LOGIN_EVENT.register(player -> {
            NucleoplasmEntity entity = nnl.findEntity(player);
            entity.login_time = Objects.requireNonNull(player.getServer()).getTimeReference();
        });
    }

    /**
     * @since 玩家退出服务器事件
     */
    private static void playerLoginOut(NucleoplasmLoader<NucleoplasmEntity> nnl) {
        ServerPlayerEvents.LOGIN_OUT_EVENT.register(player -> {
            NucleoplasmEntity entity = nnl.findEntity(player);
            entity.is_login = false;
            entity.x = player.getX();
            entity.y = player.getY();
            entity.z = player.getZ();
            entity.last_logout_time = Objects.requireNonNull(player.getServer()).getTimeReference();


//            NNLPlayerEntity entity = nnlPlayerDB.findEntity(player);
//            entity.isLogin = false;
//            entity.x = player.getX();
//            entity.y = player.getY();
//            entity.z = player.getZ();
        });
    }

    /**
     * @since 世界滴答事件
     */
    private static void worldTick(NucleoplasmLoader<NucleoplasmEntity> nnl) {
        ServerTickEvents.START_WORLD_TICK.register(world -> {
            List<ServerPlayerEntity> players = world.getPlayers();
            for (ServerPlayerEntity player : players) {
                NucleoplasmEntity entity = nnl.findEntity(player);
                if (!entity.is_login) {
                    player.teleport(entity.x, entity.y, entity.z);
                    player.setInvulnerable(true);
                    player.changeGameMode(GameMode.SURVIVAL);
                }
            }
        });
    }

    /**
     * @since 拾取事件
     */
    private static void pickupItem(NucleoplasmLoader<NucleoplasmEntity> nnl) {
        ItemEvents.PICK_ITEM_EVENT.register((player, entity) -> {
            NucleoplasmEntity entity1 = nnl.findEntity((ServerPlayerEntity) player);

            if (!entity1.is_login) {
                return ActionResult.FAIL;
            }
            return ActionResult.PASS;
        });
    }

    /**
     * @since 服务器停止事件
     */
    private static void serverStopped(NucleoplasmLoader<NucleoplasmEntity> nnl) {
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            nnl.save();
        });
    }
}

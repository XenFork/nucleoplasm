package union.xenfork.nucleoplasm_normandy_login.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import union.xenfork.nucleoplasm.api.event.ItemEvents;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;
import union.xenfork.nucleoplasm_normandy_login.quickio.nnl.NNLPlayerDB;
import union.xenfork.nucleoplasm_normandy_login.quickio.nnl.NNLPlayerEntity;
import union.xenfork.nucleoplasm_normandy_login.server.NucleoplasmNormandyLoginServer;

import java.util.List;

import static union.xenfork.nucleoplasm_normandy_login.server.NucleoplasmNormandyLoginServer.nnlPlayerDB;

public class Server {
    public static void init() {
        serverStarted();
        playerLogin();
        playerLoginOut();
        blockBreak();
        pickupItem();
        worldTick();
        interactBlock();
        interactItem();
        interactEntity();
        attackEntity();
        attackBlock();
        serverStopped();
    }

    private static void attackBlock() {
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            NNLPlayerEntity entity = nnlPlayerDB.findEntity(player);
            if (!entity.isLogin) return ActionResult.FAIL;
            return ActionResult.PASS;
        });//攻击方块时候取消事件
    }

    private static void attackEntity() {

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            NNLPlayerEntity nnlPlayerEntity = nnlPlayerDB.findEntity(player);
            if (!nnlPlayerEntity.isLogin) return ActionResult.FAIL;
            return ActionResult.PASS;
        });//没有登录之前取消攻击实体的功能
    }

    /**
     * @since 使用实体回调
     */
    private static void interactEntity() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            NNLPlayerEntity nnlPlayerEntity = nnlPlayerDB.findEntity(player);
            if (!nnlPlayerEntity.isLogin) return ActionResult.FAIL;
            return ActionResult.PASS;
        });//使用实体的时候取消事件
    }

    /**
     * @since 使用物品事件
     */
    private static void interactItem() {
        UseItemCallback.EVENT.register((player, world, hand) -> {
            NNLPlayerEntity entity = nnlPlayerDB.findEntity(player);
            if (!entity.isLogin) return TypedActionResult.fail(player.getStackInHand(hand));
            return TypedActionResult.pass(player.getStackInHand(hand));
        });//使用物品的时候取消事件
    }

    /**
     * @since 使用方块回调
     */
    private static void interactBlock() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            NNLPlayerEntity entity = nnlPlayerDB.findEntity(player);
            if (!entity.isLogin) return ActionResult.FAIL;
            return ActionResult.PASS;
        });//使用方块取消事件
    }

    /**
     * @since 破坏方块事件
     */
    private static void blockBreak() {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            NNLPlayerEntity entity = nnlPlayerDB.findEntity(player);
            return entity.isLogin;
        });//没有登录之前取消方块破坏
    }

    /**
     * @since 玩家退出服务器事件
     */
    private static void playerLoginOut() {
        ServerPlayerEvents.LOGIN_OUT_EVENT.register(player -> {
            NNLPlayerEntity entity = nnlPlayerDB.findEntity(player);
            entity.isLogin = false;
        });
    }

    /**
     * @since 世界滴答事件
     */
    private static void worldTick() {
        ServerTickEvents.START_WORLD_TICK.register(world -> {
            List<ServerPlayerEntity> players = world.getPlayers();
            for (ServerPlayerEntity player : players) {
                NNLPlayerEntity entity = nnlPlayerDB.findEntity(player);
                if (!entity.isLogin) {
                    player.teleport(entity.x, entity.y, entity.z);
                    player.setHealth(entity.health);
                }
            }
        });
    }

    /**
     * @since 玩家登录服务器事件
     */
    private static void playerLogin() {
        ServerPlayerEvents.LOGIN_EVENT.register(player -> {
            NNLPlayerEntity entity = nnlPlayerDB.findEntity(player);
            if (entity == null) {
                nnlPlayerDB.add(player);
                NNLPlayerEntity entity1 = nnlPlayerDB.findEntity(player);
                entity1.isLogin = false;
                entity1.first_join_time = player.server.getTimeReference();
                entity1.Last_join_time = player.server.getTimeReference();
                entity1.health = player.getHealth();
            } else {
                entity.x = player.getX();
                entity.y = player.getY();
                entity.z = player.getZ();
                entity.Last_join_time = player.server.getTimeReference();
                entity.health = player.getHealth();
            }
        });
    }

    /**
     * @since 拾取事件
     */
    private static void pickupItem() {
        ItemEvents.PICK_ITEM_EVENT.register((player, entity) -> {
            NNLPlayerEntity entity1 = nnlPlayerDB.findEntity(player);
            if (!entity1.isLogin) {
                return ActionResult.FAIL;
            }
            return ActionResult.PASS;
        });
    }

    /**
     * @since 服务器启动事件
     */
    private static void serverStarted() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            nnlPlayerDB = new NNLPlayerDB<>("login", NNLPlayerEntity.class);
        });
    }

    /**
     * @since 服务器停止事件
     */
    private static void serverStopped() {
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            nnlPlayerDB.close();
        });
    }
}

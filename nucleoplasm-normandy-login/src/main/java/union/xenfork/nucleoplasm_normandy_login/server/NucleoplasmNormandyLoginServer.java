package union.xenfork.nucleoplasm_normandy_login.server;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.player.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;
import union.xenfork.nucleoplasm_normandy_login.quickio.nnl.NNLPlayerDB;
import union.xenfork.nucleoplasm_normandy_login.quickio.nnl.NNLPlayerEntity;

import java.util.List;

public class NucleoplasmNormandyLoginServer implements DedicatedServerModInitializer {
    public static NNLPlayerDB<NNLPlayerEntity> nnlPlayerDB;
    @Override
    public void onInitializeServer() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            nnlPlayerDB = new NNLPlayerDB<>("login", NNLPlayerEntity.class);
        });

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

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            NNLPlayerEntity entity = nnlPlayerDB.findEntity(player);
            if (!entity.isLogin) return ActionResult.FAIL;
            return ActionResult.PASS;
        });//使用方块取消事件

        UseItemCallback.EVENT.register((player, world, hand) -> {
            NNLPlayerEntity entity = nnlPlayerDB.findEntity(player);
            if (!entity.isLogin) return TypedActionResult.fail(player.getStackInHand(hand));
            return TypedActionResult.pass(player.getStackInHand(hand));
        });//使用物品的时候取消事件

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            NNLPlayerEntity nnlPlayerEntity = nnlPlayerDB.findEntity(player);
            if (!nnlPlayerEntity.isLogin) return ActionResult.FAIL;
            return ActionResult.PASS;
        });//使用实体的时候取消事件

        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            NNLPlayerEntity entity = nnlPlayerDB.findEntity(player);
            if (!entity.isLogin) return ActionResult.FAIL;
            return ActionResult.PASS;
        });//攻击方块时候取消事件

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            NNLPlayerEntity nnlPlayerEntity = nnlPlayerDB.findEntity(player);
            if (!nnlPlayerEntity.isLogin) return ActionResult.FAIL;
            return ActionResult.PASS;
        });//没有登录之前取消攻击实体的功能

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            NNLPlayerEntity entity = nnlPlayerDB.findEntity(player);
            return entity.isLogin;
        });//没有登录之前取消方块破坏

        ServerPlayerEvents.LOGIN_OUT_EVENT.register(player -> {
            NNLPlayerEntity entity = nnlPlayerDB.findEntity(player);
            entity.isLogin = false;
        });

        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            nnlPlayerDB.close();
        });

    }
}

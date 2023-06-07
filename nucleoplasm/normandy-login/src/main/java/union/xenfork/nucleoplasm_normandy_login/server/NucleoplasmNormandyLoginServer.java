package union.xenfork.nucleoplasm_normandy_login.server;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.*;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import union.xenfork.nucleoplasm_normandy_login.common.Impl;
import union.xenfork.nucleoplasm_normandy_login.common.LoginCommon;
import union.xenfork.nucleoplasm_normandy_login.common.RegisterCommon;
import union.xenfork.nucleoplasm_normandy_login.event.Server;
import union.xenfork.nucleoplasm_normandy_login.quickio.nnl.NNLPlayerDB;
import union.xenfork.nucleoplasm_normandy_login.quickio.nnl.NNLPlayerEntity;

public class NucleoplasmNormandyLoginServer implements DedicatedServerModInitializer {
    public static final String mod_id = "nucleoplasm_normandy_login";
    public static final Logger logger = LoggerFactory.getLogger(mod_id);
    public static NNLPlayerDB<NNLPlayerEntity> nnlPlayerDB;
    @Override
    public void onInitializeServer() {
        Server.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {

            registerAll(dispatcher, new RegisterCommon(), new LoginCommon());
        });
    }

    @SafeVarargs
    public static <T extends Impl> void registerAll(CommandDispatcher<ServerCommandSource> dispatcher, T... t) {
        for (T t1 : t) {
            t1.register(dispatcher);
        }
    }
}

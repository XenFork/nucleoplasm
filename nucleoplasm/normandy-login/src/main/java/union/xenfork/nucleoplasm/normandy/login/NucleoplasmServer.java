package union.xenfork.nucleoplasm.normandy.login;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import union.xenfork.nucleoplasm.normandy.login.common.LoginCommon;
import union.xenfork.nucleoplasm.normandy.login.common.RegisterCommon;
import union.xenfork.nucleoplasm.normandy.login.event.Server;
import union.xenfork.nucleoplasm.normandy.login.quickio.nnl.NNLPlayerDB;
import union.xenfork.nucleoplasm.normandy.login.quickio.nnl.NNLPlayerEntity;

public class NucleoplasmServer implements DedicatedServerModInitializer {

    public static final String mod_id = "nucleoplasm_normandy_login";
    public static final Logger logger = LoggerFactory.getLogger(mod_id);
    public static NNLPlayerDB<NNLPlayerEntity> nnlPlayerDB;
    @Override
    public void onInitializeServer() {
        Server.init();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            LoginCommon.register(dispatcher);
            RegisterCommon.register(dispatcher);
        });
    }
}

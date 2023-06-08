package union.xenfork.nucleoplasm.normandy.login;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import union.xenfork.nucleoplasm.normandy.login.common.LoginCommon;
import union.xenfork.nucleoplasm.normandy.login.common.RegisterCommon;

public class Nucleoplasm implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            LoginCommon.register(dispatcher);
            RegisterCommon.register(dispatcher);
        });
    }
}

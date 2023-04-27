package union.xenfork.nucleoplasm_normandy_login.server;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.player.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import union.xenfork.nucleoplasm_normandy_login.event.Server;
import union.xenfork.nucleoplasm_normandy_login.quickio.nnl.NNLPlayerDB;
import union.xenfork.nucleoplasm_normandy_login.quickio.nnl.NNLPlayerEntity;

public class NucleoplasmNormandyLoginServer implements DedicatedServerModInitializer {
    public static NNLPlayerDB<NNLPlayerEntity> nnlPlayerDB;
    @Override
    public void onInitializeServer() {
        Server.init();
    }
}

package union.xenfork.nucleoplasm.lib.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.lib.event.lifecycle.LoadServerWorldEvents;
import union.xenfork.nucleoplasm.lib.event.lifecycle.ServerStartEvents;
import union.xenfork.nucleoplasm.lib.event.lifecycle.ServerStopEvents;
import union.xenfork.nucleoplasm.lib.event.lifecycle.ServerTickEvents;

import java.util.Iterator;
import java.util.Map;
import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {


    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setupServer()Z"), method = "runServer")
    private void starting(CallbackInfo info) {
        ServerStartEvents.SERVER_START_EVENT.invoker().onServerStarting((MinecraftServer) (Object) this);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;createMetadata()Lnet/minecraft/server/ServerMetadata;", ordinal = 0), method = "runServer")
    private void started(CallbackInfo info) {
        ServerStartEvents.SERVER_STARTED_EVENT.invoker().onServerStarted((MinecraftServer) (Object) this);
    }

    @Inject(at = @At("HEAD"), method = "shutdown")
    private void stopping(CallbackInfo info) {
        ServerStopEvents.SERVER_STOPPING_EVENT.invoker().onServerStopping((MinecraftServer) (Object) this);
    }

    @Inject(at = @At("TAIL"), method = "shutdown")
    private void stopped(CallbackInfo info) {
        ServerStopEvents.SERVER_STOPPED_EVENT.invoker().onServerStopped((MinecraftServer) (Object) this);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;tickWorlds(Ljava/util/function/BooleanSupplier;)V"), method = "tick")
    private void tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        ServerTickEvents.SERVER_TICK_EVENT.invoker().onServerTick((MinecraftServer) (Object) this);
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void endTick(BooleanSupplier shouldKeepTicking, CallbackInfo info) {
        ServerTickEvents.END_SERVER_TICK_EVENT.invoker().onEndServerTick((MinecraftServer) (Object) this);
    }

    @Redirect(method = "createWorlds", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private <K, V> V load(Map<K, V> worlds, K registryKey, V serverWorld) {
        final V result = worlds.put(registryKey, serverWorld);
        LoadServerWorldEvents.LOAD.invoker().onWorldLoad((MinecraftServer) (Object) this, (ServerWorld) serverWorld);

        return result;
    }

    @Inject(method = "shutdown", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;close()V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void unload(CallbackInfo ci, Iterator<ServerWorld> worlds, ServerWorld world) {
        LoadServerWorldEvents.UNLOAD.invoker().onWorldUnload((MinecraftServer) (Object) this, world);
    }

}

package union.xenfork.nucleoplasm.command.level;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.api.event.ItemEvents;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;
import union.xenfork.nucleoplasm.command.level.core.GroupEntityImpl;
import union.xenfork.nucleoplasm.command.level.face.EntityAccessor;
import union.xenfork.nucleoplasm.command.level.face.EntityImplAccessor;

import java.util.List;

public final class NucleoplasmServer implements DedicatedServerModInitializer {
    public static final GroupEntityImpl impl = new GroupEntityImpl(FabricLoader.getInstance().getGameDir().resolve("nucleoplasm/data"));

    @Override
    public void onInitializeServer() {
        ServerLifecycleEvents.SERVER_STOPPING.register(impl::save);
        ServerLifecycleEvents.SERVER_STOPPED.register(impl::close);
        ServerTickEvents.START_WORLD_TICK.register(impl::tick);
        for (Item item : Registries.ITEM) {
            ItemEvents.inventoryTick(item, (stack, world, entity, slot, selected) -> {
                if (entity instanceof PlayerEntity player) {
                    var accessor = (EntityImplAccessor)union.xenfork.nucleoplasm.api.NucleoplasmServer.impl;
                    List<String> permissions = accessor.getPermissions();
                    Identifier id = Registries.ITEM.getId(stack.getItem());
                    if (permissions.contains("ban.inv.%s.%s".formatted(id.getNamespace(), id.getPath()))) {
                        stack.setCount(0);
                    }
                }
            });
        }
    }
}

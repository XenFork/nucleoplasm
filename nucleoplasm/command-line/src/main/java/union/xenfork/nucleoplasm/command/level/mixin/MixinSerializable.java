package union.xenfork.nucleoplasm.command.level.mixin;

import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerEntityManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.SleepManager;
import net.minecraft.world.EntityList;
import net.minecraft.world.PortalForcer;
import net.minecraft.world.event.listener.GameEventDispatchManager;
import net.minecraft.world.level.ServerWorldProperties;
import net.minecraft.world.tick.WorldTickScheduler;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serializable;

@Mixin(value = {
        ServerWorld.class
        })
public class MixinSerializable implements Serializable {

}

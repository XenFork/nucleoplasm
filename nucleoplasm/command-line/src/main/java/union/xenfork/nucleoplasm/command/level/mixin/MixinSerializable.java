package union.xenfork.nucleoplasm.command.level.mixin;

import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serializable;

@Mixin(value = {
        ServerWorld.class
        })
public class MixinSerializable implements Serializable {

}

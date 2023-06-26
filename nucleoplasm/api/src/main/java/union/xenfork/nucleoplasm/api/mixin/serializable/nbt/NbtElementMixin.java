package union.xenfork.nucleoplasm.api.mixin.serializable.nbt;

import net.minecraft.nbt.NbtElement;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serializable;

@Debug(export = true)
@Mixin(NbtElement.class)
public interface NbtElementMixin extends Serializable {

}

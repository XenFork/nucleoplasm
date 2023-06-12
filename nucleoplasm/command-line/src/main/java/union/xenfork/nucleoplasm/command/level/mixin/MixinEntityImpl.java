package union.xenfork.nucleoplasm.command.level.mixin;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.api.core.EntityImpl;
import union.xenfork.nucleoplasm.command.level.core.GroupEntity;
import union.xenfork.nucleoplasm.command.level.face.EntityAccessor;
import union.xenfork.nucleoplasm.command.level.face.EntityImplAccessor;

import java.nio.file.Path;

@Mixin(value = EntityImpl.class, remap = false)
public class MixinEntityImpl implements EntityImplAccessor {


    @Shadow @Final private DB db;

    @Inject(method = "lambda$create$1", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;getUuid()Ljava/util/UUID;"))
    private static void of(ServerPlayerEntity player, Entity e, CallbackInfo ci) {
        EntityAccessor entity = (EntityAccessor) e;
        entity.addGroup("default");
        entity.addGroup("builder");
    }

    @Override
    public void save(Entity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        collection.save(entity);
    }
}

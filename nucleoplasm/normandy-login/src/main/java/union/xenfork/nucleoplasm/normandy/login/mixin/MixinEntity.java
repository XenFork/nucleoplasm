package union.xenfork.nucleoplasm.normandy.login.mixin;

import org.spongepowered.asm.mixin.Mixin;
import union.xenfork.nucleoplasm.api.core.Entity;

@Mixin(Entity.class)
public class MixinEntity {
    public boolean is_login;
    public String password;

}

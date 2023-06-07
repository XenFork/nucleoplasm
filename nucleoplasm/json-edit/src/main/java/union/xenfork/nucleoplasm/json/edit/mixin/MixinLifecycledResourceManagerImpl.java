package union.xenfork.nucleoplasm.json.edit.mixin;

import net.minecraft.resource.LifecycledResourceManagerImpl;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.ResourceFilter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mixin(LifecycledResourceManagerImpl.class)
public abstract class MixinLifecycledResourceManagerImpl {



    @ModifyVariable(method = "<init>", at = @At(value = "HEAD"), argsOnly = true)
    private static List<ResourcePack> init(List<ResourcePack> packs, ResourceType type, List<ResourcePack> packs0) {
        List<ResourcePack> copy = new ArrayList<>(packs);
        for (ResourcePack resourcePack : copy) {
            System.out.println(resourcePack.getClass());
            Set<String> namespaces = resourcePack.getNamespaces(type);
        }
        return copy;
    }
}

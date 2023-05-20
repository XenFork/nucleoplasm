package union.xenfork.nucleoplasm.json.edit.mixin;

import com.google.gson.JsonElement;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static union.xenfork.nucleoplasm.json.edit.Nucleoplasm.*;

@Mixin(RecipeManager.class)
public class MixinRecipeManage {
    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"))
    private void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        outputJson(map, recipe);

//        map.clear();
//        Map<Identifier, File> iFile = new HashMap<>();
//        load(recipe.toFile(), new StringBuilder(), iFile);
//        parser(map, iFile);
    }
}

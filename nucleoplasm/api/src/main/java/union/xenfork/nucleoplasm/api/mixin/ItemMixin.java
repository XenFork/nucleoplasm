package union.xenfork.nucleoplasm.api.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.api.event.ItemEvents;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    public void tick(
            ItemStack stack,
            World world,
            Entity entity,
            int slot,
            boolean selected,
            CallbackInfo ci
    ) {
        ItemEvents.invoke(stack, world, entity, slot, selected);
    }
}

package union.xenfork.nucleoplasm.api.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.api.event.InventoryEvent;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void inventoryTick(ItemStack stack,
                               World world,
                               Entity entity,
                               int slot,
                               boolean selected,
                               CallbackInfo ci) {
        InventoryEvent.ITEM_INVENTORY_EVENT.invoker().inventory(stack, world, entity, slot, selected);
    }
}

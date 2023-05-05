package union.xenfork.nucleoplasm.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class ItemEvents {

    public static final Event<PickItem> PICK_ITEM_EVENT
            = EventFactory.createArrayBacked(PickItem.class,
            callbacks -> (player, entity) -> {
                for (PickItem callback : callbacks) {
                    ActionResult interact = callback.interact(player, entity);
                    if (interact != ActionResult.PASS) {
                        return interact;
                    }
                }
                return ActionResult.PASS;
            });
    private static final Event<InventoryTick> INVENTORY_TICK_EVENT
            = EventFactory.createArrayBacked(InventoryTick.class,
            callbacks -> (stack, world, entity, slot, selected) -> {
                for (InventoryTick callback : callbacks) {
                    callback.inventoryTick(stack, world, entity, slot, selected);
                }
            });

    public static void invoke(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        INVENTORY_TICK_EVENT.invoker().inventoryTick(stack, world, entity, slot, selected);
    }

    public static void inventoryTick(Item item, InventoryTick tick) {
        INVENTORY_TICK_EVENT.register((stack, world, entity, slot, selected) -> {
            if (stack.getItem().equals(item)) {
                tick.inventoryTick(stack, world, entity, slot, selected);
            }
        });
    }


    public interface PickItem {
        ActionResult interact(PlayerEntity player, ItemEntity entity);
    }

    public interface InventoryTick {
        void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected);
    }
}

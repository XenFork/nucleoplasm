package union.xenfork.nucleoplasm.api.event;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface InventoryEvent {

    Event<ItemInventory> ITEM_INVENTORY_EVENT = EventFactory.createLoop();

    interface ItemInventory {
        void inventory(ItemStack stack,
                       World world,
                       Entity entity,
                       int slot,
                       boolean selected);
    }
}

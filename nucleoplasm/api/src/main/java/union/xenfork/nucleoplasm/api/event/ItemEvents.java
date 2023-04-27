package union.xenfork.nucleoplasm.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

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



    public interface PickItem {
        ActionResult interact(PlayerEntity player, ItemEntity entity);
    }
}

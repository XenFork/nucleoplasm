package union.xenfork.nucleoplasm.lib.event;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import union.xenfork.nucleoplasm.lib.event.impl.Event;
import union.xenfork.nucleoplasm.lib.event.impl.EventFactory;

public class ActionEvents {
    public static final Event<DropItem> DROP_ITEM_EVENT = EventFactory.createArrayBacked(
            DropItem.class,
            dropItems -> (player, stack) -> {
                for (DropItem dropItem : dropItems) {
                    ActionResult interact = dropItem.interact(player, stack);
                    if (interact != ActionResult.PASS)
                        return interact;
                }
                return ActionResult.PASS;
            }
    );

    public static final Event<PickItem> PICK_ITEM_EVENT = EventFactory.createArrayBacked(
            PickItem.class,
            pickItems -> (player, entity) -> {
                for (PickItem pickItem : pickItems) {
                    ActionResult interact = pickItem.interact(player, entity);
                    if (interact != ActionResult.PASS)
                        return interact;
                }
                return ActionResult.PASS;
            }
    );

    @FunctionalInterface
    public interface DropItem {
        ActionResult interact(ServerPlayerEntity player, ItemStack stack);
    }

    @FunctionalInterface
    public interface PickItem {
        ActionResult interact(PlayerEntity player, ItemEntity entity);
    }
}

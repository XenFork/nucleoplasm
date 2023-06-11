package union.xenfork.nucleoplasm.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.collection.DefaultedList;

public final class ServerPlayerEvents {
    public static final Event<ServerPlayerLogin> LOGIN_EVENT
            = EventFactory.createArrayBacked(ServerPlayerLogin.class,
            callbacks -> player -> {
                for (ServerPlayerLogin callback : callbacks) {
                    callback.login(player);
                }
            });
    public static final Event<ServerPlayerLoginOut> LOGIN_OUT_EVENT
            = EventFactory.createArrayBacked(ServerPlayerLoginOut.class,
            callbacks -> player -> {
                for (ServerPlayerLoginOut callback : callbacks) {
                    callback.login_out(player);
                }
            });
    public static final Event<ServerPlayerInventory> SERVER_PLAYER_INVENTORY_EVENT
            = EventFactory.createArrayBacked(ServerPlayerInventory.class,
            callbacks -> (player, mainHand, offHand, armor) -> {
                for (ServerPlayerInventory callback : callbacks) {
                    callback.inventory(player, mainHand, offHand, armor);
                }
            });

    public static final Event<DropItem> DROP_ITEM_EVENT =
            EventFactory.createArrayBacked(DropItem.class,
                    callbacks -> (player) -> {
                        for (DropItem dropItem : callbacks) {
                            ActionResult result = dropItem.interact(player);
                            if (result != ActionResult.PASS) {
                                return result;
                            }
                        }
                        return ActionResult.PASS;
                    });

    public static final Event<Chat> CHAT_EVENT =
            EventFactory.createArrayBacked(Chat.class,
                    callbacks -> player -> {
                        for (Chat chat : callbacks) {
                            ActionResult result = chat.chat(player);
                            if (result != ActionResult.PASS)
                                return result;
                        }
                        return ActionResult.PASS;
                    });

    public static final Event<Take> TAKE_EVENT =
            EventFactory.createArrayBacked(Take.class,
                    callbacks -> player -> {
                        for (Take manOffHandSwap : callbacks) {
                            ActionResult result = manOffHandSwap.take(player);
                            if (result != ActionResult.PASS)
                                return result;
                        }
                        return ActionResult.PASS;
                    });

    public static final Event<Move> MOVE_EVENT =
            EventFactory.createArrayBacked(Move.class,
                    moves -> player -> {
                        for (Move move : moves) {
                            ActionResult result = move.move(player);
                            if (result != ActionResult.PASS) return result;
                        }
                        return ActionResult.PASS;
                    });

    public interface ServerPlayerInventory {
        void inventory(ServerPlayerEntity player, DefaultedList<ItemStack> mainHand, DefaultedList<ItemStack> offHand, DefaultedList<ItemStack> armor);
    }
    public interface ServerPlayerLogin {
        void login(ServerPlayerEntity player);
    }
    public interface ServerPlayerLoginOut {
        void login_out(ServerPlayerEntity player);
    }

    public interface DropItem {
        ActionResult interact(ServerPlayerEntity player);
    }

    public interface Chat {
        ActionResult chat(ServerPlayerEntity player);
    }

    public interface Take {
        ActionResult take(ServerPlayerEntity player);
    }

    public interface Move {
        ActionResult move(ServerPlayerEntity player);
    }
}

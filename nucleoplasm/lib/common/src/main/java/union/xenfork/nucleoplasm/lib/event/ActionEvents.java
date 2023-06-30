package union.xenfork.nucleoplasm.lib.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
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

    public static final Event<AttackBlock> ATTACK_BLOCK_EVENT = EventFactory.createArrayBacked(
            AttackBlock.class,
            attackBlocks -> (player, world, hand, entity, hitResult) -> {
                for (AttackBlock attackBlock : attackBlocks) {
                    ActionResult interact = attackBlock.interact(player, world, hand, entity, hitResult);
                    if (interact != ActionResult.PASS)
                        return interact;
                }
                return ActionResult.PASS;
            }
    );

    public static final Event<AttackEntity> ATTACK_ENTITY_EVENT = EventFactory.createArrayBacked(
            AttackEntity.class,
             attackEntities -> (player, world, hand, entity, hitResult) -> {
                 for (AttackEntity attackEntity : attackEntities) {
                     ActionResult result = attackEntity.interact(player, world, hand, entity, hitResult);
                     if (result != ActionResult.PASS)
                         return result;
                 }
                 return ActionResult.PASS;
             }
    );

    public static final Event<UseEntity> USE_ENTITY_EVENT = EventFactory.createArrayBacked(
            UseEntity.class,
            useEntities -> (player, world, hand, entity, hitResult) -> {
                for (UseEntity useEntity : useEntities) {
                    ActionResult result = useEntity.interact(player, world, hand, entity, hitResult);
                    if (result != ActionResult.PASS)
                        return result;
                }
                return ActionResult.PASS;
            }
    );

    public static final Event<UseItem> USE_ITEM_EVENT = EventFactory.createArrayBacked(
            UseItem.class,
            useItems -> (player, world, hand) -> {
                for (UseItem useItem : useItems) {
                    TypedActionResult<ItemStack> result = useItem.interact(player, world, hand);
                    if (result.getResult() != ActionResult.PASS) {
                        return result;
                    }
                }
                return TypedActionResult.pass(ItemStack.EMPTY);
            }
    );

    public static final Event<UseBlock> USE_BLOCK_EVENT = EventFactory.createArrayBacked(
            UseBlock.class,
            useBlocks -> (player, world, hand, hitResult) -> {
                for (UseBlock useBlock : useBlocks) {
                    ActionResult result = useBlock.interact(player, world, hand, hitResult);
                    if (result != ActionResult.PASS)
                        return result;
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
    @FunctionalInterface
    public interface AttackBlock {
        ActionResult interact(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction);
    }
    @FunctionalInterface
    public interface AttackEntity {
        ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult);
    }
    @FunctionalInterface
    public interface UseEntity {
        ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult);
    }

    @FunctionalInterface
    public interface UseItem {
        TypedActionResult<ItemStack> interact(PlayerEntity player, World world, Hand hand);
    }

    @FunctionalInterface
    public interface UseBlock {
        ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult);
    }
}

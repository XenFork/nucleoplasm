package union.xenfork.nucleoplasm.lib.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import union.xenfork.nucleoplasm.lib.event.impl.Event;
import union.xenfork.nucleoplasm.lib.event.impl.EventFactory;

public class BlockEvents {
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
    public interface UseBlock {
        ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult);
    }

    @FunctionalInterface
    public interface AttackBlock {
        ActionResult interact(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction);
    }
}

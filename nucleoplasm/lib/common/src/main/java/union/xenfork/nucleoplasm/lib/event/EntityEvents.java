package union.xenfork.nucleoplasm.lib.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import union.xenfork.nucleoplasm.lib.event.impl.Event;
import union.xenfork.nucleoplasm.lib.event.impl.EventFactory;

public class EntityEvents {

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
    
    @FunctionalInterface
    public interface AttackEntity {
        ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult);
    }
    @FunctionalInterface
    public interface UseEntity {
        ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult);
    }

}

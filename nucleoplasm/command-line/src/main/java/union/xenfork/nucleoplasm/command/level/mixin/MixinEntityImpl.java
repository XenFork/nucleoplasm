package union.xenfork.nucleoplasm.command.level.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.api.core.EntityImpl;
import union.xenfork.nucleoplasm.command.level.NucleoplasmServer;
import union.xenfork.nucleoplasm.command.level.face.EntityAccessor;
import union.xenfork.nucleoplasm.command.level.face.EntityImplAccessor;

import java.util.List;

@Debug(export = true)
@Mixin(value = EntityImpl.class, remap = false, priority = 2147483646)
public abstract class MixinEntityImpl implements EntityImplAccessor {
    @Shadow public abstract Entity find(ServerPlayerEntity player);

    @Shadow public abstract Entity find(PlayerEntity player);

    @Inject(method = "lambda$create$1", at = @At("RETURN"))
    private static void of(ServerPlayerEntity player, Entity entity, CallbackInfo ci) {
        EntityAccessor accessor = (EntityAccessor) entity;
        accessor.addGroup("default");
        accessor.addGroup("builder");
    }

    public List<String> getPermissions(PlayerEntity player) {
        var accessor = (EntityAccessor)find(player);
        List<String> permissions = NucleoplasmServer.impl.getPermission(accessor.getGroups().toArray(new String[0]));
        permissions.addAll(accessor.getPermissions());
        return permissions;
    }

    @Inject(method = "attackBlock", at = @At("RETURN"), cancellable = true)
    private void attackBlock(PlayerEntity player,
                             World world,
                             Hand hand,
                             BlockPos blockPos,
                             Direction direction,
                             CallbackInfoReturnable<ActionResult> cir) {
        List<String> permissions = getPermissions(player);
        if (
                !(permissions.contains("minecraft.attack.block")
                        || permissions.contains("minecraft.attack.*")
                        || permissions.contains("minecraft.*")
                        || permissions.contains("*"))
        ) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

    @Inject(method = "attackEntity", at = @At("RETURN"), cancellable = true)
    private void attackEntity(PlayerEntity player,
                              World world,
                              Hand hand,
                              net.minecraft.entity.Entity entity,
                              EntityHitResult entityHitResult,
                              CallbackInfoReturnable<ActionResult> cir) {
        List<String> permissions = getPermissions(player);
        if (
                !(permissions.contains("minecraft.attack.entity")
                        || permissions.contains("minecraft.attack.*")
                        || permissions.contains("minecraft.*")
                        || permissions.contains("*"))
        ) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

    @Inject(method = "pickupItem", at = @At("RETURN"), cancellable = true)
    private void pickupItem(PlayerEntity player,
                            ItemEntity itemEntity,
                            CallbackInfoReturnable<ActionResult> cir) {
        List<String> permissions = getPermissions(player);
        if (
                !(permissions.contains("minecraft.pick.up")
                        || permissions.contains("minecraft:*")
                        || permissions.contains("*"))
        ) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

    @Inject(method = "blockBreak", at = @At("RETURN"), cancellable = true)
    private void blockBreak(World world,
                            PlayerEntity player,
                            BlockPos blockPos,
                            BlockState blockState,
                            BlockEntity blockEntity,
                            CallbackInfoReturnable<Boolean> cir) {
        List<String> permissions = getPermissions(player);
        if (
                !(permissions.contains("minecraft.break.block")
                        || permissions.contains("minecraft:*")
                        || permissions.contains("*"))
        ) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "interactBlock", at = @At("RETURN"), cancellable = true)
    private void interactBlock(PlayerEntity player,
                               World world,
                               Hand hand,
                               BlockHitResult blockHitResult,
                               CallbackInfoReturnable<ActionResult> cir) {

        List<String> permissions = getPermissions(player);

        if (
                !(permissions.contains("minecraft.use.*")
                        || permissions.contains("minecraft.use.block"))
        ) {
            cir.setReturnValue(ActionResult.FAIL);
        }
        Item item = player.getStackInHand(hand).getItem();
        if (item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            Identifier id = Registries.BLOCK.getId(block);
            if (!permissions.contains("use.block.%s.%s".formatted(id.getNamespace(), id.getPath()))) {
                cir.setReturnValue(ActionResult.FAIL);
            }
        }

    }

    @Inject(method = "interactItem", at = @At("RETURN"), cancellable = true)
    private void interactItem(PlayerEntity player,
                              World world,
                              Hand hand,
                              CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        List<String> permissions = getPermissions(player);
        Identifier id = Registries.ITEM.getId(player.getStackInHand(hand).getItem());
        if (
                !(permissions.contains("minecraft.use.*")
                        || permissions.contains("minecraft.use.item")
                        || permissions.contains("use.item.%s.%s".formatted(id.getNamespace(), id.getPath())))
        ) {
            cir.setReturnValue(TypedActionResult.fail(player.getStackInHand(hand)));
        }
    }

    @Inject(method = "interactEntity", at = @At("RETURN"), cancellable = true)
    private void interactEntity(PlayerEntity player,
                                World world,
                                Hand hand,
                                net.minecraft.entity.Entity entity,
                                EntityHitResult entityHitResult,
                                CallbackInfoReturnable<ActionResult> cir) {
        List<String> permissions = getPermissions(player);
        if (
                !(permissions.contains("minecraft.use.*")
                        || permissions.contains("minecraft.use.entity"))
        ) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }
}

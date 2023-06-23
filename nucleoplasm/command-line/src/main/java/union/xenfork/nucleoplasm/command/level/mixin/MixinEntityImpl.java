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
import union.xenfork.nucleoplasm.command.level.permissions.PermissionUtils;

import java.util.List;

import static union.xenfork.nucleoplasm.command.level.permissions.PermissionUtils.*;

@Debug(export = true)
@Mixin(value = EntityImpl.class)
public abstract class MixinEntityImpl implements EntityImplAccessor {

    @Shadow private List<Entity> all;

    @Inject(method = "lambda$create$1", at = @At("RETURN"))
    private static void of(ServerPlayerEntity player, Entity entity, CallbackInfo ci) {
        EntityAccessor accessor = (EntityAccessor) entity;
        accessor.addGroup("default");
        accessor.addGroup("builder");
    }

    public List<String> getPermissions(PlayerEntity player) {
        var accessor = (EntityAccessor)((EntityImpl) (Object) this).find(player);
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
        for (String permission : permissions) {
            switch (permission) {
                case attack_block, attack_all, minecraft_all, PermissionUtils.all -> {}
                default -> cir.setReturnValue(ActionResult.FAIL);
            }
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
        for (String permission : permissions) {
            switch (permission) {
                case attack_entity, attack_all, minecraft_all, PermissionUtils.all -> {}
                default -> cir.setReturnValue(ActionResult.FAIL);
            }
        }
    }

    @Inject(method = "pickupItem", at = @At("RETURN"), cancellable = true)
    private void pickupItem(PlayerEntity player,
                            ItemEntity itemEntity,
                            CallbackInfoReturnable<ActionResult> cir) {
        List<String> permissions = getPermissions(player);
        for (String permission : permissions) {
            switch (permission) {
                case pick_up, minecraft_all, PermissionUtils.all -> {}
                default -> cir.setReturnValue(ActionResult.FAIL);
            }
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
        for (String permission : permissions) {
            switch (permission) {
                case PermissionUtils.all, minecraft_all, breakBlock -> {}
                default -> cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "interactBlock", at = @At("RETURN"), cancellable = true)
    private void interactBlock(PlayerEntity player,
                               World world,
                               Hand hand,
                               BlockHitResult blockHitResult,
                               CallbackInfoReturnable<ActionResult> cir) {

        List<String> permissions = getPermissions(player);
        for (String permission : permissions) {
            switch (permission) {
                case use_all, use_block, PermissionUtils.all -> {}
                default -> cir.setReturnValue(ActionResult.FAIL);
            }
        }
        Item item = player.getStackInHand(hand).getItem();
        if (item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            Identifier id = Registries.BLOCK.getId(block);
            if (permissions.contains("ban.use.block.%s.%s".formatted(id.getNamespace(), id.getPath()))) {
                cir.setReturnValue(ActionResult.FAIL);
            }//禁止使用方块
        }

    }

    @Inject(method = "interactItem", at = @At("RETURN"), cancellable = true)
    private void interactItem(PlayerEntity player,
                              World world,
                              Hand hand,
                              CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        List<String> permissions = getPermissions(player);
        Identifier id = Registries.ITEM.getId(player.getStackInHand(hand).getItem());
        for (String permission : permissions) {
            switch (permission) {
                case use_item, use_all, minecraft_all, PermissionUtils.all -> {}
                default -> {
                    if (permission.equals("ban.use.item.%s.%s".formatted(id.getNamespace(), id.getPath()))) {
                        cir.setReturnValue(TypedActionResult.fail(player.getStackInHand(hand)));
                    }
                }
            }
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
        for (String permission : permissions) {
            switch (permission) {
                case use_entity, use_all, minecraft_all, PermissionUtils.all -> {}
                default -> cir.setReturnValue(ActionResult.FAIL);
            }
        }
    }
}

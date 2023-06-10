package union.xenfork.nucleoplasm.normandy.login.mixin;

import com.github.artbits.quickio.api.Collection;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.api.core.EntityImpl;

import java.util.Objects;

@Mixin(EntityImpl.class)
public abstract class MixinSQL {
    @Shadow public abstract void create(ServerPlayerEntity entity);

    @Inject(method = "attackBlock", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void attackBlock(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction, CallbackInfoReturnable<ActionResult> cir, Collection<Entity> collection, Entity one) {
        try {
            boolean is_login = (boolean) one.getClass().getDeclaredField("is_login").get(one);
            if (!is_login) cir.setReturnValue(ActionResult.FAIL);
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }

    @Inject(method = "attackEntity", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void attackEntity(PlayerEntity player, World world, Hand hand, net.minecraft.entity.Entity entity, EntityHitResult hitResult, CallbackInfoReturnable<ActionResult> cir, Collection<Entity> collection, Entity one) {
        try {
            boolean is_login = (boolean) one.getClass().getDeclaredField("is_login").get(one);
            if (!is_login) cir.setReturnValue(ActionResult.FAIL);
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }

    @Inject(method = "interactEntity", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void interactEntity(PlayerEntity player, World world, Hand hand, net.minecraft.entity.Entity entity, EntityHitResult hitResult, CallbackInfoReturnable<ActionResult> cir, Collection<Entity> collection, Entity one) {
        try {
            boolean is_login = (boolean) one.getClass().getDeclaredField("is_login").get(one);
            if (!is_login) cir.setReturnValue(ActionResult.FAIL);
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }

    @Inject(method = "interactItem", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void interactItem(PlayerEntity player,
                              World world,
                              Hand hand,
                              CallbackInfoReturnable<TypedActionResult<ItemStack>> cir,
                              Collection<Entity> collection,
                              Entity one) {
        try {
            boolean is_login = (boolean) one.getClass().getDeclaredField("is_login").get(one);
            if (!is_login) cir.setReturnValue(TypedActionResult.fail(player.getStackInHand(hand)));
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }

    @Inject(method = "interactBlock", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void interactBlock(PlayerEntity player,
                               World world,
                               Hand hand,
                               BlockHitResult hitResult,
                               CallbackInfoReturnable<ActionResult> cir,
                               Collection<Entity> collection,
                               Entity one) {
        try {
            boolean is_login = (boolean) one.getClass().getDeclaredField("is_login").get(one);
            if (!is_login) cir.setReturnValue(ActionResult.FAIL);
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }

    @Inject(method = "blockBreak", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void blockBreak(World world,
                            PlayerEntity player,
                            BlockPos pos,
                            BlockState state,
                            BlockEntity blockEntity,
                            CallbackInfoReturnable<Boolean> cir,
                            Collection<Entity> collection,
                            Entity one) {
        try {
            cir.setReturnValue((Boolean) one.getClass().getDeclaredField("is_login").get(one));
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }


    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void tick(ServerPlayerEntity player,
                      CallbackInfo ci,
                      Collection<Entity> collection,
                      Entity one) {
        try {
            boolean is_login = (boolean) one.getClass().getDeclaredField("is_login").get(one);
            if (!is_login) {
                double x = (double) one.getClass().getDeclaredField("x").get(one);
                double y = (double) one.getClass().getDeclaredField("y").get(one);
                double z = (double) one.getClass().getDeclaredField("z").get(one);
                player.teleport(x, y, z);
                player.setInvulnerable(true);
                player.changeGameMode(GameMode.SURVIVAL);
                if (Objects.requireNonNull(player.getServer()).getTimeReference() % 996 == 0) {
                    String password = (String) one.getClass().getDeclaredField("password").get(one);
                    if (password == null || password.isEmpty()) {
                        player.sendMessage(Text.literal("Please enter/register password confirm-password, for registration"));
                    } else {
                        player.sendMessage(Text.literal("Please enter the/login password, to log in"));
                    }
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }

    @Inject(method = "login", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void login(ServerPlayerEntity player,
                       CallbackInfo ci,
                       Collection<Entity> collection,
                       Entity one) {
        try {
            one.getClass().getDeclaredField("is_login").set(one, false);
            one.getClass().getDeclaredField("x").set(one, player.getX());
            one.getClass().getDeclaredField("y").set(one, player.getY());
            one.getClass().getDeclaredField("z").set(one, player.getZ());
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }

    @Inject(method = "logout", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void logout(ServerPlayerEntity player,
                        CallbackInfo ci,
                        Collection<Entity> collection,
                        Entity one) {
        try {
            one.getClass().getDeclaredField("is_login").set(one, false);
            one.getClass().getDeclaredField("x").set(one, player.getX());
            one.getClass().getDeclaredField("y").set(one, player.getY());
            one.getClass().getDeclaredField("z").set(one, player.getZ());
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }

    @Inject(method = "lambda$create$3", at = @At("RETURN"))
    private static void of(ServerPlayerEntity player, Entity e, CallbackInfo ci) {
        try {
            e.getClass().getDeclaredField("is_login").set(e, false);
            e.getClass().getDeclaredField("password").set(e, "");
            e.getClass().getDeclaredField("x").set(e, player.getX());
            e.getClass().getDeclaredField("y").set(e, player.getY());
            e.getClass().getDeclaredField("z").set(e, player.getZ());
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }

    @Inject(method = "pickupItem", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void pickupItem(PlayerEntity entity,
                            CallbackInfo ci,
                            Collection<Entity> collection,
                            Entity one) {
        try {
            boolean is_login = (boolean) one.getClass().getDeclaredField("is_login").get(one);
            if (!is_login) 
        } catch (IllegalAccessException | NoSuchFieldException ignored) {
        }
    }
}

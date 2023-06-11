package union.xenfork.nucleoplasm.normandy.login.mixin;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
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
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.api.core.EntityImpl;
import union.xenfork.nucleoplasm.normandy.login.face.EntityAccessor;
import union.xenfork.nucleoplasm.normandy.login.face.EntityImplAccess;

import java.util.Objects;

@Debug(export = true)
@Mixin(value = EntityImpl.class, remap = false)
public abstract class MixinSQL implements EntityImplAccess {
    @Shadow
    public abstract void create(ServerPlayerEntity entity);

    @Shadow @Final private DB db;

    @Override
    public void save(Entity entity) {
        Collection<Entity> collection = db.collection(Entity.class);
        collection.save(entity);
    }

    @Inject(method = "dropItem", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void dropItem(ServerPlayerEntity player, ItemStack stack, CallbackInfoReturnable<ActionResult> cir, Collection<Entity> collection, Entity one) {
        boolean is_login = ((EntityAccessor) one).getIsLogin();
        if (!is_login) cir.setReturnValue(ActionResult.FAIL);
    }

    @Inject(method = "attackBlock", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void attackBlock(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction, CallbackInfoReturnable<ActionResult> cir, Collection<Entity> collection, Entity one) {
        boolean is_login = ((EntityAccessor) one).getIsLogin();
        if (!is_login) cir.setReturnValue(ActionResult.FAIL);
    }

    @Inject(method = "attackEntity", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void attackEntity(PlayerEntity player, World world, Hand hand, net.minecraft.entity.Entity entity, EntityHitResult hitResult, CallbackInfoReturnable<ActionResult> cir, Collection<Entity> collection, Entity one) {
        boolean is_login = ((EntityAccessor) one).getIsLogin();
        if (!is_login) cir.setReturnValue(ActionResult.FAIL);
    }

    @Inject(method = "interactEntity", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void interactEntity(PlayerEntity player, World world, Hand hand, net.minecraft.entity.Entity entity, EntityHitResult hitResult, CallbackInfoReturnable<ActionResult> cir, Collection<Entity> collection, Entity one) {
        boolean is_login = ((EntityAccessor) one).getIsLogin();
        if (!is_login) cir.setReturnValue(ActionResult.FAIL);
    }

    @Inject(method = "interactItem", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void interactItem(PlayerEntity player,
                              World world,
                              Hand hand,
                              CallbackInfoReturnable<TypedActionResult<ItemStack>> cir,
                              Collection<Entity> collection,
                              Entity one) {
        boolean is_login = ((EntityAccessor) one).getIsLogin();
        if (!is_login) cir.setReturnValue(TypedActionResult.fail(player.getStackInHand(hand)));
    }

    @Inject(method = "interactBlock", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void interactBlock(PlayerEntity player,
                               World world,
                               Hand hand,
                               BlockHitResult hitResult,
                               CallbackInfoReturnable<ActionResult> cir,
                               Collection<Entity> collection,
                               Entity one) {
        boolean is_login = ((EntityAccessor) one).getIsLogin();
        if (!is_login) cir.setReturnValue(ActionResult.FAIL);
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
        cir.setReturnValue(((EntityAccessor) one).getIsLogin());
    }


    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void tick(ServerPlayerEntity player,
                      CallbackInfo ci,
                      Collection<Entity> collection,
                      Entity one) {
        var accessor = ((EntityAccessor) one);
        boolean is_login = accessor.getIsLogin();
        if (!is_login) {
            player.teleport(player.getServerWorld() ,accessor.getX(), accessor.getY(), accessor.getZ(), accessor.getYaw(), accessor.getPitch());
            player.setInvulnerable(true);
            player.changeGameMode(GameMode.SURVIVAL);
            if (Objects.requireNonNull(player.getServer()).getTimeReference() % 996 == 0) {
                String password = accessor.getPassword();
                if (password == null || password.isEmpty()) {
                    player.sendMessage(Text.literal("Please use /register <password> <confirm password> to register"));
                } else {
                    player.sendMessage(Text.literal("Please use /login <password> to login"));
                }
            }
        }
    }

    @Inject(method = "login", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void login(ServerPlayerEntity player,
                       CallbackInfo ci,
                       Collection<Entity> collection,
                       Entity one) {
        var accessor = ((EntityAccessor) one);
        accessor.setIsLogin(false);
        accessor.setX(player.getX());
        accessor.setY(player.getY());
        accessor.setZ(player.getZ());
        accessor.setYaw(player.getYaw());
        accessor.setPitch(player.getPitch());
    }

    @Inject(method = "logout", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void logout(ServerPlayerEntity player,
                        CallbackInfo ci,
                        Collection<Entity> collection,
                        Entity one) {
        var accessor = ((EntityAccessor) one);
        accessor.setIsLogin(false);
        accessor.setX(player.getX());
        accessor.setY(player.getY());
        accessor.setZ(player.getZ());
        accessor.setYaw(player.getYaw());
        accessor.setPitch(player.getPitch());
    }

    @Inject(method = "lambda$create$3", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;getUuid()Ljava/util/UUID;"))
    private static void of(ServerPlayerEntity player, Entity e, CallbackInfo ci) {
        var accessor = ((EntityAccessor) e);
        accessor.setIsLogin(false);
        accessor.setPassword("");
        accessor.setX(player.getX());
        accessor.setY(player.getY());
        accessor.setZ(player.getZ());
        accessor.setYaw(player.getYaw());
        accessor.setPitch(player.getPitch());
    }

    @Inject(method = "pickupItem", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void pickupItem(PlayerEntity player,
                            ItemEntity entity,
                            CallbackInfoReturnable<ActionResult> cir,
                            Collection<Entity> collection,
                            Entity one) {
        boolean is_login = ((EntityAccessor) one).getIsLogin();
        if (!is_login) cir.setReturnValue(ActionResult.FAIL);
    }
}

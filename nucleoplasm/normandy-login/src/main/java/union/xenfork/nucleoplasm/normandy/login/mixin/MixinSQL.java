package union.xenfork.nucleoplasm.normandy.login.mixin;

import com.github.artbits.quickio.api.DB;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
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
import union.xenfork.nucleoplasm.normandy.login.face.EntityImplAccessor;

import java.util.List;
import java.util.Objects;

@Debug(export = true)
@Mixin(value = EntityImpl.class, remap = false)
public abstract class MixinSQL implements EntityImplAccessor {

    @Shadow @Final private DB db;

    @Shadow public abstract void logout(ServerPlayerEntity player);


    @Shadow private List<Entity> all;

    @Shadow public abstract Entity create(ServerPlayerEntity player);

    @Inject(method = "attackBlock", at = @At(value = "HEAD"), cancellable = true)
    private void attackBlock(PlayerEntity player, World world, Hand hand, BlockPos blockPos, Direction direction, CallbackInfoReturnable<ActionResult> cir) {
        var accessor = (EntityAccessor)((EntityImpl)(Object)this).find(player);
        if (!accessor.getIsLogin()) cir.setReturnValue(ActionResult.FAIL);
    }

    @Inject(method = "attackEntity", at = @At(value = "HEAD"), cancellable = true)
    private void attackEntity(PlayerEntity player, World world, Hand hand, net.minecraft.entity.Entity entity, EntityHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        var accessor = (EntityAccessor)((EntityImpl)(Object)this).find(player);
        if (!accessor.getIsLogin()) cir.setReturnValue(ActionResult.FAIL);
    }

    @Inject(method = "interactEntity", at = @At(value = "HEAD"), cancellable = true)
    private void interactEntity(PlayerEntity player, World world, Hand hand, net.minecraft.entity.Entity entity, EntityHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        var accessor = (EntityAccessor)((EntityImpl)(Object)this).find(player);
        if (!accessor.getIsLogin()) cir.setReturnValue(ActionResult.FAIL);
    }

    @Inject(method = "interactItem", at = @At(value = "HEAD"), cancellable = true)
    private void interactItem(PlayerEntity player,
                              World world,
                              Hand hand,
                              CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        var accessor = (EntityAccessor)((EntityImpl)(Object)this).find(player);
        if (!accessor.getIsLogin()) cir.setReturnValue(TypedActionResult.fail(player.getStackInHand(hand)));
    }

    @Inject(method = "interactBlock", at = @At(value = "HEAD"), cancellable = true)
    private void interactBlock(PlayerEntity player,
                               World world,
                               Hand hand,
                               BlockHitResult hitResult,
                               CallbackInfoReturnable<ActionResult> cir) {
        var accessor = (EntityAccessor)((EntityImpl)(Object)this).find(player);
        if (!accessor.getIsLogin()) cir.setReturnValue(ActionResult.FAIL);
    }

    @Inject(method = "blockBreak", at = @At("HEAD"), cancellable = true)
    private void blockBreak(World world,
                            PlayerEntity player,
                            BlockPos pos,
                            BlockState state,
                            BlockEntity blockEntity,
                            CallbackInfoReturnable<Boolean> cir) {
        var accessor = (EntityAccessor)((EntityImpl)(Object)this).find(player);
        cir.setReturnValue(accessor.getIsLogin());
    }


    @Inject(method = "tick(Lnet/minecraft/server/network/ServerPlayerEntity;)V", at = @At(value = "HEAD"))
    private void tick(ServerPlayerEntity player,
                      CallbackInfo ci) {
        var accessor = (EntityAccessor)((EntityImpl)(Object)this).find(player);
        if (!accessor.getIsLogin()) {
            player.teleport(accessor.getX(), accessor.getY(), accessor.getZ());
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

    @Inject(method = "login", at = @At(value = "HEAD"))
    private void login(ServerPlayerEntity player,
                       CallbackInfo ci) {
        List<Entity> list = all.stream().filter(entity -> entity.player_name.equals(player.getEntityName())).toList();
        EntityAccessor accessor;
        if (list.isEmpty()) {
            accessor = (EntityAccessor) create(player);
        } else {
            accessor = (EntityAccessor) all.get(0);
        }
        accessor.setIsLogin(false);
    }

    @Inject(method = "logout", at = @At(value = "HEAD"))
    private void logout(ServerPlayerEntity player,
                        CallbackInfo ci) {
        var accessor = (EntityAccessor)((EntityImpl)(Object)this).find(player);
        accessor.setIsLogin(false);
        accessor.setX(player.getX());
        accessor.setY(player.getY());
        accessor.setZ(player.getZ());
        accessor.setYaw(player.getYaw());
        accessor.setPitch(player.getPitch());
    }

    @Inject(method = "lambda$create$1", at = @At(value = "HEAD"))
    private static void of(ServerPlayerEntity player, Entity e, CallbackInfo ci) {
        var accessor = ((EntityAccessor) e);
        accessor.setIsLogin(false);
        accessor.setPassword("");
        accessor.setX(player.getX());
        accessor.setY(player.getY());
        accessor.setZ(player.getZ());
        accessor.setPitch(player.getPitch());
        accessor.setYaw(player.getYaw());
    }

    @Inject(method = "pickupItem", at = @At(value = "HEAD"), cancellable = true)
    private void pickupItem(PlayerEntity player,
                            ItemEntity entity,
                            CallbackInfoReturnable<ActionResult> cir) {
        var accessor = (EntityAccessor)((EntityImpl)(Object)this).find(player);
        if (!accessor.getIsLogin()) cir.setReturnValue(ActionResult.FAIL);
    }

    @Inject(method = "save(Lnet/minecraft/server/MinecraftServer;)V", at = @At(value = "HEAD"))
    private void save(MinecraftServer server, CallbackInfo ci) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) logout(player);
    }
}

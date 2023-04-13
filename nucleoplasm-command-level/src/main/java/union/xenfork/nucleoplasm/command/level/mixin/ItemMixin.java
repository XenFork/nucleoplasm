package union.xenfork.nucleoplasm.command.level.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.command.level.server.NucleoplasmCommandLevelServer;

import java.util.ArrayList;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow public abstract void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected);

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (entity instanceof PlayerEntity player) {
            ArrayList<String> groups =
                    NucleoplasmCommandLevelServer.playerDB.getGroups(player.getEntityName());
            double capeX = player.capeX;
            double capeY = player.capeY;
            double capeZ = player.capeZ;
            boolean hasMove = false;
            for (String group : groups) {
                ArrayList<String> groups1 = NucleoplasmCommandLevelServer.groupDB.getGroups(group);
                for (String s : groups1) {
                    if (s.equals("minecraft.move")) {
                        hasMove = true;
                        break;
                    }
                }
            }
            if (!hasMove) {
                player.move(MovementType.SELF, new Vec3d(capeX, capeY, capeZ));
            }

        }
    }
}

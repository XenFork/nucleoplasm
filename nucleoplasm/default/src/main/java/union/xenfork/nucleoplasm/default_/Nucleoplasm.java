package union.xenfork.nucleoplasm.default_;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.C2SPlayChannelEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.S2CPlayChannelEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.PlayerActionResponseS2CPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.joml.Vector3f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import union.xenfork.nucleoplasm.default_.machine.CraftTableBlock;
import union.xenfork.nucleoplasm.default_.machine.CraftTableBlockEntity;
import union.xenfork.nucleoplasm.default_.machine.Register;


public class Nucleoplasm implements ModInitializer {
    public static final String MODID = "nucleoplasm_default";

    public static final Logger logger = LoggerFactory.getLogger(MODID);


    @Override
    public void onInitialize() {
        try {
            Class.forName(Register.class.getName());
        } catch (ClassNotFoundException e) {
            logger.error("fail to load register\n", e);
        }
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            BlockState state = world.getBlockState(pos);
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (state.getBlock() instanceof CraftTableBlock block && blockEntity instanceof CraftTableBlockEntity entity) {
                float eyeHeight = player.getEyeHeight(player.getPose());
                Vec2f rotationClient = player.getRotationClient();
                float yaw = player.getYaw();
                float pitch = player.getPitch();
                Vec3d start = player.getCameraPosVec(1.0f);
                Vec3d end = start.add(player.getRotationVec(1.0f).multiply(5.0f));
                BlockHitResult result = world.raycast(new RaycastContext(start, end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player));


                System.out.printf("眼睛高低:%f", eyeHeight);
                System.out.printf("左右向量: %f", yaw);
                System.out.printf("上下向量: %f", pitch);
                System.out.printf("朝向: %s",result.getSide());
                System.out.println();
                System.out.println();

            }
            return ActionResult.PASS;
        });
    }
}

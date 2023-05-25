package union.xenfork.nucleoplasm.default_;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.C2SPlayChannelEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec2f;
import org.joml.Vector3f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import union.xenfork.nucleoplasm.default_.machine.CraftTableBlock;
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
            ClientPlayerEntity entity = MinecraftClient.getInstance().player;
            if (state.getBlock() instanceof CraftTableBlock block) {
                if (entity != null) {
                    float eyeHeight = entity.getEyeHeight(entity.getPose());
                    Vec2f rotationClient = entity.getRotationClient();

                    float yaw = entity.getYaw();
                    float pitch = player.getPitch();
                    System.out.printf("眼睛高低:%f", eyeHeight);
                    System.out.printf("左右向量: %f", yaw);
                    System.out.printf("上下向量: %f", pitch);
                    System.out.println();
                }

            }
            return ActionResult.PASS;
        });
    }
}

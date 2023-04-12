package union.xenfork.nucleoplasm.command.level.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.GameModeArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.GameModeCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import union.xenfork.nucleoplasm.command.level.NucleoplasmCommandLevel;
import union.xenfork.nucleoplasm.command.level.quickio.GroupEntity;
import union.xenfork.nucleoplasm.command.level.quickio.PlayerEntity;
import union.xenfork.nucleoplasm.command.level.server.NucleoplasmCommandLevelServer;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

@Mixin(GameModeCommand.class)
public abstract class GameModeCommandMixin {

    /**
     * @author baka4n
     * @reason 重写命令
     */
    @Overwrite
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("gamemode").requires(source -> source.hasPermissionLevel(0))
                        .then(CommandManager.argument("gamemode", GameModeArgumentType.gameMode())).executes(context -> execute(context, Collections.singleton(context.getSource().getPlayerOrThrow()), GameModeArgumentType.getGameMode(context, "gamemode")))
                        .then(CommandManager.argument("target", EntityArgumentType.players()).executes(context -> execute(context, EntityArgumentType.getPlayers(context, "target"), GameModeArgumentType.getGameMode(context, "gamemode"))))
        );
    }

    private static int execute(CommandContext<ServerCommandSource> context, Collection<ServerPlayerEntity> targets, GameMode gameMode) {
        int i = 0;
        for (ServerPlayerEntity serverPlayerEntity : targets) {
            com.github.artbits.quickio.api.Collection<PlayerEntity> collection = NucleoplasmCommandLevelServer.player.collection(PlayerEntity.class);
            PlayerEntity one = collection.findOne(playerEntity -> playerEntity.player_name.equals(serverPlayerEntity.getEntityName()));
            for (String group : one.groups) {
                com.github.artbits.quickio.api.Collection<GroupEntity> collection1 = NucleoplasmCommandLevelServer.group.collection(GroupEntity.class);
                GroupEntity one1 = collection1.findOne(groupEntity -> groupEntity.group_name.equals(group));
                for (String s : one1.permission) {
                    if (s.equals("minecraft.gamemode") || s.equals("minecraft.*") || s.equals("*")) {
                        if (serverPlayerEntity.changeGameMode(gameMode)) {
                            sendFeedback(context.getSource(), serverPlayerEntity, gameMode);
                            ++i;
                        }
                    }
                }
            }
        }
        return i;
    }

    private static void sendFeedback(ServerCommandSource source, ServerPlayerEntity player, GameMode gameMode) {
        Text text = Text.translatable("gameMode." + gameMode.getName());
        if (source.getEntity() == player) {
            source.sendFeedback(Text.translatable("commands.gamemode.success.self", text), true);
        } else {
            if (source.getWorld().getGameRules().getBoolean(GameRules.SEND_COMMAND_FEEDBACK)) {
                player.sendMessage(Text.translatable("gameMode.changed", text));
            }

            source.sendFeedback(Text.translatable("commands.gamemode.success.other", player.getDisplayName(), text), true);
        }

    }
}

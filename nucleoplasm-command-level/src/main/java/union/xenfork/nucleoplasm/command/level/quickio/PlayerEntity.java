package union.xenfork.nucleoplasm.command.level.quickio;

import com.github.artbits.quickio.core.IOEntity;

import java.util.ArrayList;
import java.util.function.Consumer;

public class PlayerEntity extends IOEntity {
    public String player_name;
    public ArrayList<String> groups;

    public static PlayerEntity of(Consumer<PlayerEntity> consumer) {
        PlayerEntity playerEntity = new PlayerEntity();
        consumer.accept(playerEntity);
        return playerEntity;
    }
}

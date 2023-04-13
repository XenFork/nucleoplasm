package union.xenfork.nucleoplasm.api;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import union.xenfork.nucleoplasm.api.quickio.PlayerEntity;

import java.util.ArrayList;

public class NucleoplasmApi implements ModInitializer {
    public static final ArrayList<String> null_array = new ArrayList<>();
    public static final PlayerEntity null_player_entity = PlayerEntity.of(
            entity -> {

            });
    public static final String modid = "nucleoplasm_api";
    public static final Logger logger = LoggerFactory.getLogger(modid);
    @Override
    public void onInitialize() {

    }

    public static Identifier getIdentifier(String name) {
        return new Identifier(modid, name);
    }

}

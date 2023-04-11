package union.xenfork.nucleoplasm.api;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NucleoplasmApi implements ModInitializer {
    public static final String modid = "nucleoplasm_api";
    public static final Logger logger = LoggerFactory.getLogger(modid);
    @Override
    public void onInitialize() {

    }

    public static Identifier getIdentifier(String name) {
        return new Identifier(modid, name);
    }

}

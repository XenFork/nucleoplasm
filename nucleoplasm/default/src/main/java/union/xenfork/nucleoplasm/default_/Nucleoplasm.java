package union.xenfork.nucleoplasm.default_;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    }
}

package union.xenfork.nucleoplasm.json.edit.registry;

import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Path;

import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.slf4j.Logger;

public class RegistryJsonUtil {
    private final Path registry;

    public RegistryJsonUtil(Path registry, Logger logger) {
        this.registry = registry;
        init(logger, "item");
    }

    public void init(Logger logger, String title) {
        Path itemPath = registry.resolve(title);
        for (Item item : Registries.ITEM) {
            Identifier id = Registries.ITEM.getId(item);
            logger.info(id + "-->" + item.getClass().getName());
            Util.switchUtil(logger, item, itemPath);
//            Class<? extends Item> aClass = item.getClass();
//            var constructors = aClass.getDeclaredConstructors();
//            Identifier id = Registries.ITEM.getId(item);
//            Path resolve = itemPath.resolve(id.getNamespace()).resolve(id.getPath() + ".json");
//
//            logger.info(id.toString());
//
//            for (Constructor<?> constructor : constructors) {
//                //打印所有构造方法
//                logger.info(constructor.getName());
//                Class<?>[] parameterTypes = constructor.getParameterTypes();
//                for (Class<?> parameterType : parameterTypes) {
//                    logger.info(parameterType.getName());
//                }
//            }

        }
    }
}

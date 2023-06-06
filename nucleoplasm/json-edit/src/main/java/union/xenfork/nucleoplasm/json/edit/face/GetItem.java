package union.xenfork.nucleoplasm.json.edit.face;

import net.minecraft.item.Item;

public interface GetItem {
    default Item.Settings settings() {
        return new Item.Settings();
    }

    default void settings(Item.Settings settings) {
    }

    default void reInitSettings() {}
}

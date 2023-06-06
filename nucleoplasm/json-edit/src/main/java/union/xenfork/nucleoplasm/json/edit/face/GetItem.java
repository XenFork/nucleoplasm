package union.xenfork.nucleoplasm.json.edit.face;

import net.minecraft.item.Item;

public interface GetItem {
    default Item.Settings getSettings() {
        return new Item.Settings();
    }

    default void setSettings() {
    }
}

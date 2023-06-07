package union.xenfork.nucleoplasm.json.edit.face;

import net.minecraft.item.Item;

public interface GetSettings {
    default Item.Settings getSettings() {
        throw new AssertionError();
    }
}

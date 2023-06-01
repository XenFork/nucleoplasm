package union.xenfork.nucleoplasm.api.quickio.minecraft.nbt;

import com.github.artbits.quickio.core.IOEntity;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import union.xenfork.nucleoplasm.api.NucleoplasmApi;

public class INbt extends IOEntity {
    public String nbt;
    public INbt(NbtCompound nbt) {
        if (nbt != null) {
            this.nbt = nbt.asString();
        }

    }

    public NbtCompound get() {
        try {
            return StringNbtReader.parse(nbt);
        } catch (CommandSyntaxException e) {
            NucleoplasmApi.logger.error(e.getMessage());
        }
        return new NbtCompound();
    }
}

package io.github.xenfork.nucleoplasm.core;

import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

public interface SerializerImpl {

    void deserializer(@NotNull NbtCompound nbt);
    NbtCompound getNbt();
}

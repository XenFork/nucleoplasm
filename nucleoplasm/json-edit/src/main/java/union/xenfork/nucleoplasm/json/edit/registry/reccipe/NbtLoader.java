package union.xenfork.nucleoplasm.json.edit.registry.reccipe;

import com.google.gson.annotations.SerializedName;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.nbt.visitor.StringNbtWriter;
import union.xenfork.nucleoplasm.json.edit.Nucleoplasm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;

public class NbtLoader {
    @SerializedName("nbt")
    private String nbt;
    public NbtLoader(NbtCompound nbt) {
        String string = UUID.randomUUID().toString();
        Path nbtpath = Nucleoplasm.dir.resolve("nbt");
        if (Files.exists(nbtpath)) {
            try {
                Files.createDirectories(nbtpath);
            } catch (IOException ignored) {}
        }
        Path resolve = nbtpath.resolve(string);
        while (Files.exists(resolve)) {
            string = UUID.randomUUID().toString();
            resolve = nbtpath.resolve(string);
        }
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(resolve)) {
            bufferedWriter.write(nbt.asString());
        } catch (IOException ignored) {}
        this.nbt = resolve.toString();
    }

    public NbtCompound getNbt() {
        Path path = Path.of(nbt);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            bufferedReader.lines().forEach(sb::append);
        } catch (IOException ignored) {}
        if (!sb.isEmpty()) {
            try {
                return StringNbtReader.parse(sb.toString());
            } catch (CommandSyntaxException ignored) {}
        }
        return new NbtCompound();
    }
}

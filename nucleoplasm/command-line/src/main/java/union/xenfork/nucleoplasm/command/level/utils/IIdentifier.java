package union.xenfork.nucleoplasm.command.level.utils;

import net.minecraft.util.Identifier;

import java.io.Serial;
import java.io.Serializable;

public class IIdentifier implements Serializable {
    @Serial
    private static final long serialVersionUID = -2950170613323871786L;
    private final String namespace;
    private final String path;
    public IIdentifier(Identifier id) {
        namespace = id.getNamespace();
        path = id.getPath();
    }

    public String getNamespace() {
        return namespace;
    }

    public String getPath() {
        return path;
    }


}

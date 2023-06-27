package union.xenfork.nucleoplasm.registry.impl;

import java.util.function.Function;

public abstract class ModBase<K extends String, V extends Function<?, ?>> {
     public abstract void add(K k, V v);
}

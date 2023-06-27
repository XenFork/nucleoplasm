package union.xenfork.nucleoplasm.registry.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ModDef<K extends String, V extends Function<?,?>> {
    public final Map<K, V> map = new HashMap<>();
    public void add(K k, V v) {
        map.put(k, v);
    }
}

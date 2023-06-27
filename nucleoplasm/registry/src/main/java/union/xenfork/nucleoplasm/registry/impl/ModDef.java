package union.xenfork.nucleoplasm.registry.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ModDef<K extends String, V extends Function<?,?>> extends ModBase<K, V> {
    public final Map<K, V> map = new HashMap<>();
    @Override
    public void add(K k, V v) {
        map.put(k, v);
    }
}

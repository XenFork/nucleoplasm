package union.xenfork.nucleoplasm.api.quickio.utils;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.QuickIO;
import union.xenfork.nucleoplasm.api.NucleoplasmApi;
import union.xenfork.nucleoplasm.api.quickio.GroupEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GroupDB<T extends GroupEntity> {
    public final DB db;
    public final Collection<T> collection;
    public final Class<T> tClass;
    public GroupDB(String name, Class<T> tClass) {
        db = QuickIO.usingDB(name);
        this.tClass = tClass;
        collection = db.collection(tClass);
    }

    public void add(String group_name, ArrayList<String> permission, ArrayList<String> extends_group) {
        try {
            T t = tClass.getDeclaredConstructor().newInstance();
            t.group_name = group_name;
            t.extends_group = extends_group;
            t.permission = permission;
            collection.save(t);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            NucleoplasmApi.logger.info(e.getMessage());
        }
    }

    public void remove(String group_name) {
        collection.delete(t -> t.group_name.equals(group_name));
    }

    public T get(String group_name) {
        return collection.findOne(t -> t.group_name.equals(group_name));
    }

    public void close() {
        db.close();
    }
}

package union.xenfork.nucleoplasm.api.quickio.utils;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.QuickIO;
import union.xenfork.nucleoplasm.api.NucleoplasmApi;
import union.xenfork.nucleoplasm.api.quickio.GroupEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GroupDB<T extends GroupEntity> {
    private final DB db;
    private final Collection<T> collection;
    public GroupDB(String name, Class<T> t) {
        db = QuickIO.usingDB(name);
        collection = db.collection(t);
    }

    public boolean hasGroup(String groupName) {
        T one = collection.findOne(t -> t.group_name.equals(groupName));
        return one != null;
    }

    public Set<String> playerList() {
        Set<String> set = new HashSet<>();
        collection.findAll().forEach(t -> set.add(t.group_name));
        return set;
    }

    public ArrayList<String> getGroups(String groupName) {
        if (hasGroup(groupName)) {
            T one = collection.findOne(t -> t.group_name.equals(groupName));
            ArrayList<String> permission = new ArrayList<>(one.permission);
            for (String s : one.extends_group) {
                permission.addAll(getGroups(s));
            }
            return permission;
        }
        return NucleoplasmApi.null_array;
    }

    public long size() {
        return collection.count();
    }

    public void close() {
        db.close();
    }

    public void add(T t) {
        collection.save(t);
    }

    public void delete(String groupName) {
        collection.delete(t -> t.group_name.equals(groupName));
    }

}

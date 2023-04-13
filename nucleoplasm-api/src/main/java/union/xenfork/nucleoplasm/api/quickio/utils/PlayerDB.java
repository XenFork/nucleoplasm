package union.xenfork.nucleoplasm.api.quickio.utils;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.QuickIO;
import union.xenfork.nucleoplasm.api.NucleoplasmApi;
import union.xenfork.nucleoplasm.api.quickio.PlayerEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PlayerDB<T extends PlayerEntity> {
    private final DB db;
    private final Collection<T> collection;
    public PlayerDB(String name, Class<T> t) {
        db = QuickIO.usingDB(name);
        collection = db.collection(t);
    }

    public boolean hasPlayer(String playerName) {
        T one = collection.findOne(t -> t.player_name.equals(playerName));
        return one != null;
    }
    public Set<String> playerList() {
        Set<String> set = new HashSet<>();
        collection.findAll().forEach(t -> set.add(t.player_name));
        return set;
    }

    public ArrayList<String> getGroups(String playerName) {
        if (hasPlayer(playerName)) {
            return collection.findOne(t -> t.player_name.equals(playerName)).groups;
        }
        return NucleoplasmApi.null_array;
    }

    public PlayerEntity get(String playerName) {
        if (hasPlayer(playerName)) {
            return collection.findOne(t -> t.player_name.equals(playerName));
        }
        return NucleoplasmApi.null_player_entity;
    }

    public void setJoinServerTime(String playerName, long time) {
        get(playerName).join_time = time;
    }

    public void register(String playerName, String password) {
        get(playerName).password = password;
    }

    public void login(String playerName, String password) {
        PlayerEntity playerEntity = get(playerName);
        if (playerEntity.password.equals(playerName)) {
            playerEntity.isLogin = true;
        }
    }

    public void email(String playerName, String email) {
        get(playerName).email = email;
    }

    public void login_out(String playerName) {
        get(playerName).isLogin = false;
    }

    public void setLoginTime(String playerName, long time) {
        get(playerName).login_time = time;
    }

    public boolean isLogin(String playerName) {
        return get(playerName).isLogin;
    }

    public void loginTimePP(String playerName) {
        get(playerName).login_time++;
    }

    public void add(T t) {
        collection.save(t);
    }

    public void remove(String playerName) {
        collection.delete(t -> t.player_name.equals(playerName));
    }

    public void close() {
        db.close();
    }

    public long size() {
        return collection.count();
    }


}

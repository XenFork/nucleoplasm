package union.xenfork.nucleoplasm.api.quickio.utils;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.QuickIO;
import net.minecraft.server.PlayerManager;
import net.minecraft.util.math.Vec3d;
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

    public Vec3d getVec3d(net.minecraft.entity.player.PlayerEntity entity) {
        PlayerEntity playerEntity = get(entity);
        return new Vec3d(playerEntity.x, playerEntity.y, playerEntity.z);
    }

    public void setVec3d(net.minecraft.entity.player.PlayerEntity entity) {
        PlayerEntity playerEntity = get(entity);
        playerEntity.x = entity.getX();
        playerEntity.y = entity.getY();
        playerEntity.z = entity.getZ();
    }

    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    public boolean hasPlayer(String playerName) {
        T one = collection.findOne(t -> t.player_name.equals(playerName));
        return one != null;
    }

    public boolean hasPlayer(net.minecraft.entity.player.PlayerEntity entity) {
        T one = collection.findOne(t -> t.player_name.equals(entity.getEntityName()));
        return one != null;
    }
    public Set<String> playerList() {
        Set<String> set = new HashSet<>();
        collection.findAll().forEach(t -> set.add(t.player_name));
        return set;
    }

    @Deprecated
    public ArrayList<String> getGroups(String playerName) {
        if (hasPlayer(playerName)) {
            return collection.findOne(t -> t.player_name.equals(playerName)).groups;
        }
        return NucleoplasmApi.null_array;
    }

    public ArrayList<String> getGroups(net.minecraft.entity.player.PlayerEntity entity) {
        String entityName = entity.getEntityName();
        if (hasPlayer(entityName)) {
            return collection.findOne(t -> t.player_name.equals(entityName)).groups;
        }
        return NucleoplasmApi.null_array;
    }

    public PlayerEntity get(net.minecraft.entity.player.PlayerEntity entity) {
        String playerName = entity.getEntityName();
        if (hasPlayer(playerName)) {
            return collection.findOne(t -> t.player_name.equals(playerName));
        }
        return NucleoplasmApi.null_player_entity;
    }

    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    public PlayerEntity get(String playerName) {
        if (hasPlayer(playerName)) {
            return collection.findOne(t -> t.player_name.equals(playerName));
        }
        return NucleoplasmApi.null_player_entity;
    }

    public void setJoinServerTime(net.minecraft.entity.player.PlayerEntity entity, long time) {
        get(entity).join_time = time;
    }
    @Deprecated
    public void setJoinServerTime(String playerName, long time) {
        get(playerName).join_time = time;
    }

    public void register(net.minecraft.entity.player.PlayerEntity entity, String password) {
        get(entity).password = password;
    }
    @Deprecated
    public void register(String playerName, String password) {
        get(playerName).password = password;
    }

    public void login(net.minecraft.entity.player.PlayerEntity entity, String password) {
        String entityName = entity.getEntityName();
        PlayerEntity playerEntity = get(entityName);
        if (playerEntity.password.equals(entityName)) {
            playerEntity.isLogin = true;
        }
    }
    @Deprecated
    public void login(String playerName, String password) {
        PlayerEntity playerEntity = get(playerName);
        if (playerEntity.password.equals(playerName)) {
            playerEntity.isLogin = true;
        }
    }

    public void email(net.minecraft.entity.player.PlayerEntity entity, String email) {
        get(entity).email = email;
    }
    @Deprecated
    public void email(String playerName, String email) {
        get(playerName).email = email;
    }

    public void login_out(net.minecraft.entity.player.PlayerEntity entity) {
        get(entity).isLogin = false;
    }

    @Deprecated
    public void login_out(String playerName) {
        get(playerName).isLogin = false;
    }

    public void setLoginTime(net.minecraft.entity.player.PlayerEntity entity, long time) {
        get(entity).login_time = time;
    }
    @Deprecated
    public void setLoginTime(String playerName, long time) {
        get(playerName).login_time = time;
    }
    @Deprecated
    public boolean isLogin(String playerName) {
        return get(playerName).isLogin;
    }

    public boolean isLogin(net.minecraft.entity.player.PlayerEntity entity) {
        return get(entity).isLogin;
    }
    @Deprecated
    public void loginTimePP(String playerName) {
        get(playerName).login_time++;
    }

    public void loginTimePP(net.minecraft.entity.player.PlayerEntity entity) {
        get(entity).login_time++;
    }

    public void add(T t) {
        collection.save(t);
    }
    @Deprecated
    public void remove(String playerName) {
        collection.delete(t -> t.player_name.equals(playerName));
    }
    public void remove(net.minecraft.entity.player.PlayerEntity playerName) {
        collection.delete(t -> t.player_name.equals(playerName.getEntityName()));
    }

    public void close() {
        db.close();
    }

    public long size() {
        return collection.count();
    }


}

package union.xenfork.nucleoplasm.api.sql;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class NucleoplasmLoader<T extends NucleoplasmEntity> {
    public final Map<String, NucleoplasmEntity> playerEntity = new HashMap<>();
    public static final Path path = FabricLoader.getInstance().getConfigDir().resolve("nucleoplasm/data");
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path data;
    private final Class<T> tClass;
    public NucleoplasmLoader(String dataName, Class<T> tClass) {
        data = path.resolve(dataName);
        this.tClass = tClass;
        if (!Files.exists(data)) try {
            Files.createDirectories(data);
        } catch (IOException ignored) {}
        load();
    }

    public void load() {
        try (Stream<Path> list = Files.list(data)) {
            for (Path p : list.toList()) {
                try (BufferedReader br = Files.newBufferedReader(p)) {
                    String[] split = p.toString().replace("\\", "/").split("/");
                    playerEntity.put(de(split[split.length - 1]), gson.fromJson(br, tClass));
                }
            }
        } catch (IOException ignored) {}
    }

    public void save() {
        for (Map.Entry<String, NucleoplasmEntity> entry : playerEntity.entrySet()) {
            String entityName = entry.getKey();
            NucleoplasmEntity entity = entry.getValue();
            Path resolve = data.resolve(en(entityName));
            if (!Files.exists(resolve)) {
                try(BufferedWriter bw = Files.newBufferedWriter(resolve)) {
                    String json = gson.toJson(entity);
                    bw.write(json);
                } catch (IOException ignored) {}
            }
        }
    }

    public NucleoplasmEntity findEntity(ServerPlayerEntity entity) {
        String entityName = entity.getEntityName();
        if (!playerEntity.containsKey(entityName)) {
            NucleoplasmEntity t = T.of(it -> {
                it.uuid = entity.getUuid().toString();
                it.fly = entity.isFallFlying();
                it.login_time = Objects.requireNonNull(entity.getServer()).getTimeReference();
                it.first_join_time = Objects.requireNonNull(entity.getServer()).getTimeReference();
                it.gamemode = entity.interactionManager.getGameMode().name();
                it.is_invincible = entity.isInvulnerable();
                it.is_invisible = entity.isInvisible();
                it.is_login = false;
                it.password = "";
                it.x = entity.getX();
                it.y = entity.getY();
                it.z = entity.getZ();
                try {
                    KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
                    gen.initialize(1024, new SecureRandom());
                    KeyPair keyPair = gen.generateKeyPair();
                    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
                    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
                    it.rsaKeyPU = new String(Base64.encodeBase64(publicKey.getEncoded()));
                    it.rsaKeyPR = new String(Base64.encodeBase64(privateKey.getEncoded()));
                } catch (NoSuchAlgorithmException ignored) {}
            });
            playerEntity.put(entityName, t);
            return t;
        } else {
            return playerEntity.get(entityName);
        }
    }

    public String publicCrypt(ServerPlayerEntity entity, String password) throws Exception {
        NucleoplasmEntity entity1 = findEntity(entity);
        byte[] decoded = Base64.decodeBase64(entity1.rsaKeyPU);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(password.getBytes(StandardCharsets.UTF_8)));
    }

    public String decrypt(String password, ServerPlayerEntity entity) throws Exception{
        NucleoplasmEntity entity1 = findEntity(entity);
        byte[] inputByte = Base64.decodeBase64(password.getBytes(StandardCharsets.UTF_8));

        byte[] decoded = Base64.decodeBase64(entity1.rsaKeyPR);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    public NucleoplasmEntity findEntity(PlayerEntity entity) {
        return playerEntity.get(entity.getEntityName());
    }

    public String de(String message) {
        return message.replace(".json", "");
    }

    public String en(String message) {
        return message + ".json";
    }
}

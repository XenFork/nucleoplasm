package union.xenfork.nucleoplasm.api.db;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public interface SQLImpl extends Serializable {
    default void save(Path p, boolean isLock, String key) throws IOException {
        OutputStream out;
        ObjectOutputStream objOut;
        if (!Files.exists(p.getParent())) Files.createDirectories(p.getParent());
        if (Files.exists(p)) Files.delete(p);
        Files.createFile(p);
        out = Files.newOutputStream(p);
        objOut = new ObjectOutputStream(out);
        objOut.writeObject(this);
        objOut.close();
        out.close();
    }

    @SuppressWarnings("unchecked")
    default <T extends SQLImpl> SQLImpl load(Path p, boolean isLock, String key) throws IOException, ClassNotFoundException {
        T t;
        if (!Files.exists(p.getParent())) Files.createDirectories(p.getParent());
        if (Files.exists(p)) {
            InputStream in;
            ObjectInputStream objIn;
            in = Files.newInputStream(p);
            objIn = new ObjectInputStream(in);
            t = (T) objIn.readObject();
            objIn.close();
            in.close();
            return t;
        }
        return null;
    }

}

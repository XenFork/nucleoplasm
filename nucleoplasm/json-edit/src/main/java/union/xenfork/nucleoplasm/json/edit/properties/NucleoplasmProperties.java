package union.xenfork.nucleoplasm.json.edit.properties;

import oshi.util.FileUtil;
import union.xenfork.nucleoplasm.json.edit.Nucleoplasm;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class NucleoplasmProperties extends Properties {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Object> keyList = new ArrayList<>();
    public NucleoplasmProperties() {

    }

    public NucleoplasmProperties(String path) {
        try {
            InputStream is = new FileInputStream(path);
            this.load(is);
        } catch (FileNotFoundException e) {
            Nucleoplasm.logger.error("fail to load file, because file is null", e);
        } catch (IOException e) {
            Nucleoplasm.logger.error("fail load error,beause file isn't properties", e);
        }
    }

    public NucleoplasmProperties(Path path) {
        try {
            InputStream is = Files.newInputStream(path);
            this.load(is);
        } catch (FileNotFoundException e) {
            Nucleoplasm.logger.error("fail to load file, because file is null", e);
        } catch (IOException e) {
            Nucleoplasm.logger.error("fail load error,beause file isn't properties", e);
        }
    }

    public NucleoplasmProperties(File file) {
        try {
            InputStream is = new FileInputStream(file);
            this.load(is);
        } catch (FileNotFoundException e) {
            Nucleoplasm.logger.error("fail to load file, because file is null", e);
        } catch (IOException e) {
            Nucleoplasm.logger.error("fail load error,beause file isn't properties", e);
        }
    }

    @Override
    public synchronized Object put(Object key, Object value) {
        this.removeKeyIfExists(key);
        keyList.add(key);
        return super.put(key, value);
    }

    @Override
    public synchronized Object remove(Object key) {
        this.removeKeyIfExists(key);
        return super.remove(key);
    }

    public List<Object> getKeyList() {
        return keyList;
    }

    @Override
    public Enumeration<Object> keys() {
        return new EnumerationAdapter(keyList);
    }

    private static class EnumerationAdapter<T> implements Enumeration<T> {
        private int index = 0;
        private final List<T> list;
        private final boolean isEmpty;

        public EnumerationAdapter(List<T> list) {
            this.list = list;
            this.isEmpty = list.isEmpty();
        }

        public boolean hasMoreElements() {
            return !isEmpty && index < list.size();
        }

        public T nextElement() {
            if (this.hasMoreElements()) {
                return list.get(index++);
            }
            return null;
        }
    }

    public void store(String path) {
        this.store(path, StandardCharsets.UTF_8);
    }
    public void store(Path path) {
        this.store(path, StandardCharsets.UTF_8);
    }

    public void store(File file) {
        this.store(file, StandardCharsets.UTF_8);
    }

    public void store(File file, Charset charset) {
        if (file != null) {
            try {
                OutputStream os = new FileOutputStream(file);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, charset));
                this.store(bw, null);
                bw.close();
            } catch (IOException e) {
                Nucleoplasm.logger.error("fail to save", e);
            }
        } else {
            Nucleoplasm.logger.error("fail to save is null");
        }
    }

    public void store(String path, Charset charset) {
        if (path != null && !"".equals(path)) {
            try {
                OutputStream os = new FileOutputStream(path);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, charset));
                this.store(bw, null);
                bw.close();
            } catch (IOException e) {
                Nucleoplasm.logger.error("fail to save", e);
            }
        } else {
            Nucleoplasm.logger.error("fail to save is null");
        }
    }

    public void store(Path path, Charset charset) {
        if (path != null) {
            try {
                OutputStream os = Files.newOutputStream(path);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, charset));
                this.store(bw, null);
                bw.close();
            } catch (IOException e) {
                Nucleoplasm.logger.error("fail to save", e);
            }
        } else {
            Nucleoplasm.logger.error("fail to save is null");
        }
    }


    private void removeKeyIfExists(Object key) {
        keyList.remove(key);
    }
}

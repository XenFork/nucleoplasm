package union.xenfork.nucleoplasm.api.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Config {
    @SerializedName("authors")
    public ArrayList<String> authors;
    public boolean no_global;
    public static Config of(Consumer<Config> consumer) {
        Config config = new Config();
        consumer.accept(config);
        return config;
    }
}

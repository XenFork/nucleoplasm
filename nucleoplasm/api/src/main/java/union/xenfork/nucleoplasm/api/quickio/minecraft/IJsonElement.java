package union.xenfork.nucleoplasm.api.quickio.minecraft;

import com.github.artbits.quickio.core.IOEntity;
import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

public class IJsonElement extends IOEntity {
    private String json;
    public IJsonElement(JsonElement json) {
        this.json = json.toString();
    }

    public JsonElement get() {
        StringReader sr = new StringReader(json);
        JsonReader jr = new JsonReader(sr);
        jr.setLenient(true);
        return Streams.parse(jr);
    }
}

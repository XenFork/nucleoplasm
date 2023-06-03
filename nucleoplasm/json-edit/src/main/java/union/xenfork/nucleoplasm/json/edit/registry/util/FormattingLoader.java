package union.xenfork.nucleoplasm.json.edit.registry.util;

import com.google.gson.annotations.SerializedName;
import net.minecraft.util.Formatting;

import java.util.Locale;

/**
 * @author baka4n
 * {@link Formatting}
 */
public class FormattingLoader {
    @SerializedName("formatting")
    private String name;
    @SerializedName("code")
    private char code;
    @SerializedName("modifier")
    private boolean modifier;
    @SerializedName("colorIndex")
    private int colorIndex;
    @SerializedName("colorValue")
    private Integer colorValue;

    public static FormattingLoader create(Formatting formatting) {

        FormattingLoader formattingLoader = new FormattingLoader();
        formattingLoader.name = formatting.getName().toUpperCase(Locale.ROOT);
        formattingLoader.code = formatting.getCode();
        formattingLoader.modifier = formatting.isModifier();
        formattingLoader.colorIndex = formatting.getColorIndex();
        formattingLoader.colorValue = formatting.getColorValue();
        return formattingLoader;
    }
}

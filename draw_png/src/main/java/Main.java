import img.ImgCrt;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Path path = new File(System.getProperty("user.dir")).toPath();
        path = path
                .resolve("nucleoplasm/chemistry/src/main/resources/assets/nucleoplasm_chemistry/textures/element");

//        File items = new File(System.getProperty("user.dir"), "items");
        ImgCrt crt = new ImgCrt(path);

        IntStream.rangeClosed('a', 'z').forEach(i -> crt.create(String.valueOf((char) i)));
        IntStream.rangeClosed('A', 'Z').forEach(i -> crt.create(String.valueOf((char) i)));
        IntStream.rangeClosed(1, 9).forEach(i -> crt.create(String.valueOf(i)));
    }
}

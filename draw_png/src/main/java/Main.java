import img.ImgCrt;

import java.io.File;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        File items = new File(System.getProperty("user.dir"), "items");
        ImgCrt crt = new ImgCrt(items);
        IntStream.rangeClosed('a', 'z').forEach(i -> crt.create(String.valueOf((char) i)));
        IntStream.rangeClosed('A', 'Z').forEach(i -> crt.create(String.valueOf((char) i)));
        IntStream.rangeClosed(1, 9).forEach(i -> crt.create(String.valueOf(i)));
    }
}

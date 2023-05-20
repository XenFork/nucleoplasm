package img;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ImgCrt {
    public final List<File> files = new ArrayList<>();

    public ImgCrt(File... files) {
        add(files);
    }

    public ImgCrt(Path... paths) {
        add(paths);
    }

    public void add(File... files) {
        this.files.addAll(Arrays.asList(files));
    }

    public void add(Path... paths) {
        files.addAll(Arrays.stream(paths).map(Path::toFile).toList());
    }

    public void create(String... messages) {
        for (File items : files) {
            if (!items.exists()) items.mkdirs();
            for (String message : messages) {
                int width = 64, height = 64;
                BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D ig2 = bi.createGraphics();
                Font font = new Font("Default", Font.BOLD, 40);
                ig2.setFont(font);
                FontMetrics fontMetrics = ig2.getFontMetrics();
                int stringWidth = fontMetrics.stringWidth(message);
                int stringHeight = fontMetrics.getAscent();
                ig2.setPaint(Color.BLACK);
                ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 2 - height / 10);
                try {
                    ImageIO.write(bi, "PNG", new File(items, message + ".png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * @since 化合价小标Valence
     */
    public void createV() {
        IntStream.rangeClosed(1, 118).forEach(i -> {
            create("v", 256, 256, i + "+", 25, 256 - 20 - (14 * ((i + "+").length())), 30 + 20, Color.RED);
            create("v", 256, 256, i + "-", 25, 256 - 20 - (14 * ((i + "-").length())), 30 + 20, Color.RED);
        });
    }

    public void createNOA() {
        IntStream.rangeClosed(1, 20).forEach(i ->
            create("noa", 256, 256, String.valueOf(i), 25, 256 - 20 - (14 * (String.valueOf(i).length())), 256 - 20 - 28, Color.RED));
    }

    /**
     * @since 原子序数小标 atomic number
     */
    public void createAN() {
        IntStream.rangeClosed(1, 118).forEach(i ->
            create("an", 256, 256, String.valueOf(i), 25, 30, 256 - 20 - 28, Color.MAGENTA));
    }

    /**
     * @since 原子质量数的小标 The number of atomic masses
     */
    public void createTNOAM() {
        IntStream.rangeClosed(1, 294).forEach(i ->
            create("tnoam", 256, 256, String.valueOf(i), 25, 30, 30 + 20, Color.BLUE));
    }

    public void create(String filePath, int width, int height, String message, int fontSize, int x, int y, Color color) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D ig2 = bi.createGraphics();
        Font font = new Font("Default", Font.BOLD, fontSize);
        ig2.setFont(font);
        FontMetrics fontMetrics = ig2.getFontMetrics();
        for (File file : files) {
            File file1 = file.toPath().resolve(filePath).toFile();
            if (!file1.exists()) file1.mkdirs();
            int sw = fontMetrics.stringWidth(message);
            int sh = fontMetrics.getAscent();
            System.out.println(sw);
            System.out.println(sh);
            ig2.setPaint(color);
            ig2.drawString(message, x, y);
            try {
                ImageIO.write(bi, "PNG", new File(file1, message + ".png"));
            } catch (IOException ignored) {
            }
        }
    }
}

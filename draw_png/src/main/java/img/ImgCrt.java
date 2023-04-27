package img;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImgCrt {
    public final List<File> files = new ArrayList<>();

    public ImgCrt(File... files) {
        this.files.addAll(Arrays.asList(files));
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

}

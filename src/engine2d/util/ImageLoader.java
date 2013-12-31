package engine2d.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Zeejfps on 12/28/13.
 */
public class ImageLoader {

    private static String PNG = ".png";
    private static String JPEG = ".jpeg";
    private static String BMP = ".bmp";

    public static BufferedImage loadPNG(String location, String fileName) {
        return loadImage(location, fileName, PNG);
    }

    public static BufferedImage loadJPEG(String location, String fileName) {
        return loadImage(location, fileName, JPEG);
    }

    public static BufferedImage loadBMP(String location, String fileName) {
        return loadImage(location, fileName, BMP);
    }

    private static BufferedImage loadImage(String location, String filename, String extension) {

        if (!location.endsWith("/") && !location.endsWith("\\"))
            location = location+"/";
        try {
            final BufferedImage image = ImageIO.read(new File(location + filename + extension));
            return image;
        } catch (IOException e) {
            System.err.println("Failed to load Image!");
            e.printStackTrace();
        }

        return null;
    }

}

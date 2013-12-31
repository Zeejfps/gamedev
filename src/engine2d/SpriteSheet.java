package engine2d;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

/**
 * Created by Zeejfps on 12/28/13.
 */
public class SpriteSheet {

    public final int tileSize, width, height;
    public final int[] pixels;

    public SpriteSheet(BufferedImage image, int tileSize) {

        this.tileSize = tileSize;
        this.width = image.getWidth();
        this.height = image.getHeight();

        pixels = ((DataBufferInt)(image.getRaster().getDataBuffer())).getData();

    }

}

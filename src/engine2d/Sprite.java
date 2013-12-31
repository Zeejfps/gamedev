package engine2d;


import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Zeejfps on 12/28/13.
 */
public class Sprite {

    public final int[] pixels;
    public final int textureId;

    public Sprite(SpriteSheet sheet, int x, int y) {

        pixels = new int[sheet.tileSize * sheet.tileSize];

        final int startX = x*sheet.tileSize;
        final int startY = y*sheet.tileSize;

        for (int i = 0; i < sheet.tileSize; i ++) {

            for (int j = 0; j < sheet.tileSize; j++) {

                pixels[i*sheet.tileSize + j] = sheet.pixels[startY * sheet.tileSize + startX + j];

            }

        }

        IntBuffer data = BufferUtils.createIntBuffer(pixels.length);
        data.put(pixels);
        data.flip();

        textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, sheet.width, sheet.height, 0, GL_TEXTURE_BORDER, GL_UNSIGNED_INT, data);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

}

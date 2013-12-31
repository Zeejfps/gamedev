package projectx;

import projectx.util.Keyboard;

import java.awt.event.KeyEvent;

/**
 * Created by Zeejfps on 12/29/13.
 */
public class Player implements Renderable {

    private static SpriteSheet sheet = SpriteSheet.createSheet(Player.class.getResource("res/test1.png"), 8);
    private static Sprite sprite = new Sprite(sheet, 0, 0, 3, 3);

    public Player() {


    }

    public int x = 50, y =120/2;

    @Override
    public int[] getPixels() {
        return sprite.pixels;
    }

    @Override
    public int getWidth() {
        return sprite.width;
    }

    @Override
    public int getHeight() {
        return sprite.height;
    }

    @Override
    public int getPosX() {
        return x;
    }

    @Override
    public int getPosY() {
        return y;
    }

    public void update() {
        if (Keyboard.isKeyDown(KeyEvent.VK_W))
            y--;
        if (Keyboard.isKeyDown(KeyEvent.VK_S))
            y++;
        if (Keyboard.isKeyDown(KeyEvent.VK_D))
            x++;
        if (Keyboard.isKeyDown(KeyEvent.VK_A))
            x--;

    }

}

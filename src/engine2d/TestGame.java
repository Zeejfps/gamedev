package engine2d;

import engine2d.util.Clock;
import engine2d.util.Input;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.image.BufferedImage;

/**
 * Created by Zeejfps on 12/28/13.
 */
public class TestGame extends GameShell {

    public static final int WIDTH = 640, HEIGHT = 480;
    public static final String TITLE = "SomeShooter";
    public static final int TICKS_PER_SECOND = 30;

    public TestGame() {
        super(WIDTH, HEIGHT, TITLE, TICKS_PER_SECOND);
        start();
        clock.start();
    }

    float x = 0;
    float y = 0;
    float speed = 0;
    Clock clock = new Clock();

    @Override
    public void tick() {

        clock.tick();
        if (window.isCloseRequested()) {
            stop();
        }

        speed = 1f*clock.getDeltaTime();
        if (Input.isKeyDown(Keyboard.KEY_W)) {
            y += speed;
        }

        if (Input.isKeyDown(Keyboard.KEY_S)) {
            y -= speed;
        }

        if (Input.isKeyDown(Keyboard.KEY_D)) {
            x += speed;
        }

        if (Input.isKeyDown(Keyboard.KEY_A)) {
            x -= speed;
        }

    }

    @Override
    public void render(float interpolation) {

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        new Entity(new Sprite(new SpriteSheet(new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB), 16), 0, 0), 20, 20).draw();

        window.update();

    }

    public static void main(String[] args) {
        TestGame test = new TestGame();
    }

}

package engine2d.util;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * Created by Zeejfps on 12/14/13.
 */
public final class Input {

    private static boolean[] keysPressed = new boolean[256];
    private static boolean[] keysDown = new boolean[256];
    private static boolean[] keysReleased = new boolean[256];

    private static int mouseX = 0;
    private static int mouseY = 0;

    private Input(){}

    public static void create() throws LWJGLException {

        Keyboard.create();
        Mouse.create();

    }

    public static void destroy() {

        Keyboard.destroy();
        Mouse.destroy();

    }

    public static boolean isKeyPressed(int keyCode) {

        return keysPressed[keyCode];
    }

    public static boolean isKeyReleased(int keyCode) {

        return keysReleased[keyCode];
    }

    public static boolean isKeyDown(int keyCode) {

        return keysDown[keyCode];
    }

    public static void poll() {

        for (int i = 0; i < keysReleased.length; i ++) {
            keysReleased[i] = false;
        }

        boolean isDown;
        for (int i = 0; i < keysDown.length; i++) {

            isDown = Keyboard.isKeyDown(i);
            if (keysDown[i]) {

                keysReleased[i] = !isDown;

            }

            if (keysDown[i] && isDown) {

                keysPressed[i] = false;

            } else {

                keysPressed[i] = isDown;

            }

            keysDown[i] = isDown;

        }

        mouseX = Mouse.getX();
        mouseY = Mouse.getY();

    }

    public static int getMouseX() {
        return mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }

}

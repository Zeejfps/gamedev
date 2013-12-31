package engine2d.util;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Created by Zeejfps on 12/28/13.
 */
public class Window {

    private static Window instance;

    private final int width, height;
    private final String title;

    private Window(int width, int height, String title) {

        this.width = width;
        this.height = height;
        this.title = title;

    }

    public static Window getInstance(int width, int height, String title) {

        if (instance == null)
            instance = new Window(width, height, title);

        return instance;

    }

    public void create() {

        try {

            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(title);
            Display.create();
            Input.create();

        } catch (LWJGLException e) {
            e.printStackTrace();
        }

    }

    public void update() {
        Display.update();
    }

    public void destroy() {
        Input.destroy();
        Display.destroy();
    }

    public boolean isCloseRequested() {
        return Display.isCloseRequested();
    }

}

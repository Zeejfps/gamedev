package projectx;

import projectx.util.Keyboard;

import java.awt.*;

/**
 * Created by Zeejfps on 12/28/13.
 */
public class GameCanvas extends Canvas {

    private int width, height;

    public GameCanvas(int width, int height) {

        this.width = width;
        this.height = height;

        setFocusable(true);
        addKeyListener(Keyboard.getInstance());

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void update() {
        getBufferStrategy().show();
    }

    public Graphics getDrawGraphic() {
        return getBufferStrategy().getDrawGraphics();
    }

}

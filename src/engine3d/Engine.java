package engine3d;

import engine2d.util.Clock;
import engine2d.util.Input;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Created by Zeejfps on 12/10/13.
 */
public class Engine {

    private static final float DEFAULT_SECOND = 1000000000;
    private static final int DEFAULT_MAX_FRAME_SKIP = 10;

    private final float nsPerTick;
    private final int maxFrameSkip;

    private final GameContext context;
    private final Clock clock;

    private boolean running = false;

    public Engine(int width, int height, String title, float ticksPerSec, GameContext context) {

        nsPerTick = DEFAULT_SECOND / ticksPerSec;
        maxFrameSkip = DEFAULT_MAX_FRAME_SKIP;

        this.context = context;
        this.clock = new Clock();

        initDisplay(width, height, title);
    }

    public void start() {

        if (running) return;
        running = true;

        int skippedFrames;
        float interpolation;
        long runTime = 0;

        clock.start();
        context.onStart();
        while (running && !Display.isCloseRequested()) {

            clock.tick();
            runTime += clock.getTimePerTick();

            skippedFrames = 0;
            while (runTime >= nsPerTick && skippedFrames <= maxFrameSkip) {

                Input.poll();
                context.tick();
                runTime -= nsPerTick;
                skippedFrames ++;
            }

            interpolation = (float)runTime / nsPerTick;
            context.render(interpolation);

        }
        context.onEnd();
        cleanUp();

    }

    public void stop() {

        if (!running) return;
        running = false;
    }

    private void initDisplay(int width, int height, String title) {

        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(title);
            Display.create();
            Input.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private void cleanUp() {

        Input.destroy();
        Display.destroy();

    }

}

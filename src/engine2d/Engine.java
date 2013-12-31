package engine2d;

import engine2d.util.Clock;
import engine2d.util.Input;
import org.lwjgl.opengl.Display;

/**
 * Created by Zeejfps on 12/28/13.
 */
public class Engine implements Runnable {

    private static final int MAX_FRAME_SKIP = 10;

    private final float nsPerTick;

    private final GameContext gameContext;
    private final Clock engineClock = new Clock();

    private boolean running = false;

    public Engine(GameContext gameContext, int ticksPerSec) {
        this.gameContext = gameContext;

        nsPerTick = Clock.NS_IN_SEC / ticksPerSec;
    }

    public void start() {
        if (running) return;
        engineClock.start();
        running = true;
        new Thread(this).start();
    }

    @Override
    public void run() {

        gameContext.onStart();
        loop();
        gameContext.onStop();

    }

    public void stop() {
        if (!running) return;
        running = false;
        engineClock.stop();
    }

    private void loop() {

        float interpolation;
        int skippedFrames;
        long runTime = 0;

        while (running) {

            engineClock.tick();
            runTime += engineClock.getTimePerTick();

            skippedFrames = 0;
            while (runTime >= nsPerTick && skippedFrames <= MAX_FRAME_SKIP) {

                Input.poll();
                gameContext.tick();
                runTime -= nsPerTick;
                skippedFrames ++;
            }

            interpolation = (float)runTime / nsPerTick;
            gameContext.render(interpolation);

        }

    }

}

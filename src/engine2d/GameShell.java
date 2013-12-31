package engine2d;

import engine2d.util.Input;
import engine2d.util.Window;

/**
 * Created by Zeejfps on 12/28/13.
 */
public abstract class GameShell implements GameContext {

    protected Window window;
    protected Engine engine;

    public GameShell(int width, int height, String title, int ticksPerSecond) {

        window = Window.getInstance(width, height, title);
        engine = new Engine(this, ticksPerSecond);

    }

    public void start() {
        engine.start();
    }

    @Override
    public void onStart() {
        window.create();
    }

    @Override
    public void pollInput() {
        Input.poll();
    }

    public void stop() {
        engine.stop();
    }

    @Override
    public void onStop() {
        window.destroy();
    }

}

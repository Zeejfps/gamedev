package projectx;

import projectx.util.Clock;

/**
 * Created by Zeejfps on 12/28/13.
 */
public class SomeShooter extends GameShell {

    public static int WIDTH = 160, HEIGHT = 120;
    public static int SCALE = 4;
    public static String TITLE = "Some Shooter Yo!";

    private Clock gameClock = new Clock();
    private Camera camera = new Camera(WIDTH/2, HEIGHT/2, WIDTH, HEIGHT);
    Player player = new Player();

    public SomeShooter() {
        super(WIDTH, HEIGHT, SCALE, TITLE, 30, 0);

        start();
    }

    public void pause() {
        gameClock.pause();
    }

    @Override
    public void onStart() {
        gameClock.start();
    }

    @Override
    public void onStop() {
        gameClock.stop();
    }

    @Override
    public void update() {

        if (gameClock.isPaused())
            return;

        player.update();
        camera.update();

    }

    @Override
    public void render(int[] pixels) {

        camera.clear();

        camera.render(player);

        camera.display(pixels, WIDTH);

    }

    public static void main(String[] args) {
        new SomeShooter();
    }

}

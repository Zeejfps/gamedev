package projectx;

import projectx.util.Clock;
import projectx.util.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by Zeejfps on 12/28/13.
 */
public abstract class GameShell extends Canvas {

    protected final int width, height, scale;
    private JFrame gameWindow;
    private Engine engine;
    private Clock engineClock = new Clock();
    private Clock debugClock = new Clock();
    private Clock renderClock = new Clock();

    private volatile boolean running = false;

    private float fps;

    private static BufferedImage drawImage;
    private static int[] pixels;

    private int ticks = 0;
    private int frames = 0;

    public GameShell(final int width, final int height, final int scale, final String title, int tps, int fps) {

        this.width = width;
        this.height = height;
        this.scale = scale;
        setFps(fps);

        engine = new Engine(Clock.NS_IN_SEC / tps);

        drawImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)drawImage.getRaster().getDataBuffer()).getData();

        initDisplay(title);
    }

    public final synchronized void start() {

        if (running) return;

        running = true;
        engineClock.start();
        debugClock.start();
        renderClock.start();
        new Thread(engine).start();

    }

    public final synchronized void stop() {

        if (!running) return;

        engineClock.stop();
        debugClock.stop();
        renderClock.stop();
        running = false;

    }

    private void initDisplay(String title) {

        this.addKeyListener(Keyboard.getInstance());

        gameWindow = new JFrame(title);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setLayout(new BorderLayout());
        gameWindow.add(this, BorderLayout.CENTER);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

        this.requestFocusInWindow();

    }

    private void tick() {

        debugClock.tick();

        if (debugClock.getRunTimeMs() >= 1000) {

            System.out.println("Ticks: " + ticks + " Frames: " + frames);
            debugClock.reset();
            ticks = 0;
            frames = 0;

        }

        update();
        ticks ++;
    }

    private void render() {

        renderClock.tick();
        if (renderClock.getRunTimeNs() >= fps) {

            BufferStrategy bs = getBufferStrategy();
            if (bs == null) {
                createBufferStrategy(3);
                return;
            }

            render(pixels);
            Graphics g = bs.getDrawGraphics();
            g.drawImage(drawImage, 0, 0, getWidth(), getHeight(), null);
            g.dispose();

            bs.show();
            renderClock.reset();
            frames++;

        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width * scale, height * scale);
    }

    public void setFps(int fps) {
        if (fps < 1)
            fps = 9999;
        else
            this.fps = (float)Clock.NS_IN_SEC/(float)fps;
    }

    public abstract void onStart();

    public abstract void onStop();

    public abstract void update();

    public abstract void render(int[] pixels);

    private class Engine implements Runnable {

        private int MAX_FRAME_SKIP = 8;
        private final float nsPerTick;

        public Engine(float nsPerTick) {
            this.nsPerTick = nsPerTick;
        }

        @Override
        public void run() {

            int skippedFrames;
            long runTime = 0;

            onStart();
            while (running) {

                engineClock.tick();
                runTime += engineClock.getTimePerTick();

                skippedFrames = 0;
                while (runTime >= nsPerTick && skippedFrames <= MAX_FRAME_SKIP) {

                    tick();
                    runTime -= nsPerTick;
                    skippedFrames ++;
                }

                render();

            }
            onStop();

        }

    }

}

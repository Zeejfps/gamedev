package engine3d;

import engine3d.models.Mesh;
import engine2d.util.Clock;
import engine3d.shaders.Shader;
import engine3d.shaders.ShaderComponent;
import engine2d.util.Input;
import engine3d.util.mathOld.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;


/**
 * Created by Zeejfps on 12/14/13.
 */
public class Test implements GameContext {

    public static final String TITLE = "engine3d.Test Game";

    private float fps = 1000f / 60f;

    private final Engine engine;
    private final Clock gameClock;
    private final Clock renderClock;
    private final Clock debugClock;

    private Shader shader;
    private Mesh mesh;

    Camera camera;

    public Test() {

        engine = new Engine(800, 600, TITLE, 30, this);
        gameClock = new Clock();
        renderClock = new Clock();
        debugClock = new Clock();

        glViewport(0, 0, Display.getWidth(), Display.getHeight());

        shader = new Shader(

                new ShaderComponent(GL_VERTEX_SHADER, "BasicVertex.vert"),
                new ShaderComponent(GL_FRAGMENT_SHADER, "BasicFragment.frag")

        );

        mesh = new Mesh(vertices, indices);
        camera = new Camera(90f, 4f / 3f, 0.5f, 100.0f);
        //mesh.position.z = -3;

        shader.addUniforms("transform", "projection");
        glUseProgram(shader.getAddress());
        shader.setUniform("projection", camera.getProjectionMatrix());
        glUseProgram(0);

        glEnable(GL_CULL_FACE);
        glFrontFace(GL_CCW);

        Quaternion lol = new Quaternion(new Vector3f(1, 0, 0), 90);
        Quaternion lol2 = new Quaternion(new Vector3f(1, 0, 0), -90);
        System.out.println(lol);
        System.out.println(lol2);

        engine.start();
    }

    public static void main(String[] args) {

        new Test();

    }

    float[] vertices = {

            // front face
            0.0f, 0.5f, 0.0f,       // 0
            -0.5f, -0.5f, -0.5f,    // 1
            0.5f, -0.5f, -0.5f,     // 2
            0.5f, -0.5f, 0.5f,      // 3
            -0.5f, -0.5f, 0.5f      // 4

    };

    int[] indices = {

            // font face
            0, 1, 2,
            0, 2, 3,
            0, 4, 1,
            0, 3, 4,
            1, 4, 3,
            3, 2, 1

    };

    @Override
    public void onStart() {

        gameClock.start();
        debugClock.start();
        renderClock.start();

    }

    int ticks = 0;

    @Override
    public void tick() {

        ticks++;
        gameClock.tick();
        debugClock.tick();

        if (Input.isKeyPressed(Keyboard.KEY_PERIOD)) {
            System.out.println("key pressed");
            gameClock.stepForwards();
        }

        if (Input.isKeyPressed(Keyboard.KEY_COMMA)) {
            gameClock.stepBackwards();
        }

        if (Input.isKeyDown(Keyboard.KEY_UP)) {
            camera.rotateX(10 * gameClock.getDeltaTime());
        }
        if (Input.isKeyDown(Keyboard.KEY_DOWN)) {
            camera.rotateX(-10 * gameClock.getDeltaTime());
        }
        if (Input.isKeyDown(Keyboard.KEY_LEFT)) {
            camera.rotateY(10 * gameClock.getDeltaTime());
        }
        if (Input.isKeyDown(Keyboard.KEY_RIGHT)) {
            camera.rotateY(10 * gameClock.getDeltaTime());
        }

        //mesh.rotation.setY(mesh.rotation.getY() + 0.5f * gameClock.getDeltaTime());
        //System.out.println();

        glUseProgram(shader.getAddress());
        //shader.setUniform("transform", mesh.getTransform());
        shader.setUniform("transform", camera.getViewMatrix().mul(Matrix4f.genRotationMatrix(0, 20, 0)));
        glUseProgram(0);

        if (debugClock.getRunTimeMs() >= 1000) {

            Display.setTitle(TITLE + " | FPS: " + updates + " | TICKS: " + ticks + " |");
            ticks = 0;
            updates = 0;
            debugClock.reset();

        }

        //System.out.println("gameClock: " + gameClock.getDeltaTimeNs() + " renderClock: " + renderClock.getDeltaTimeNs());

    }

    int updates = 0;

    float temp = 0.2f;
    @Override
    public void render(float interpolation) {

        renderClock.tick();
        if (renderClock.getRunTimeMs() >= fps) {

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glUseProgram(shader.getAddress());
            //mesh.rotation.y += 0.5f * gameClock.getDeltaTime() * interpolation;



            updates++;
            mesh.draw();

            glUseProgram(0);

            Display.update();
            renderClock.reset();
        }

    }

    @Override
    public void onEnd() {

    }

}

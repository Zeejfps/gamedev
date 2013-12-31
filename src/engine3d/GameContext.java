package engine3d;

/**
 * Created by Zeejfps on 12/14/13.
 */
public interface GameContext {

    public void onStart();

    public void tick();

    public void render(float interpolation);

    public void onEnd();
}

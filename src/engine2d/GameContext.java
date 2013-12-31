package engine2d;

/**
 * Created by Zeejfps on 12/28/13.
 */
public interface GameContext {

    public void onStart();

    public void pollInput();

    public void tick();

    public void render(float interpolation);

    public void onStop();

}

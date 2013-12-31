package projectx;

import projectx.util.Matrix2f;

/**
 * Created by Zeejfps on 12/29/13.
 */
public abstract class Entity implements Locatable {

    private int posX, posY;
    public int rotX = 0, rotY = 0;
    public int width, height;
    public Matrix2f transform;

    public Entity(int posX, int posY, int width, int height) {

        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;

        transform = new Matrix2f();

    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

}

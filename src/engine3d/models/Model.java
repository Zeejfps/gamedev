package engine3d.models;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Zeejfps on 12/19/13.
 */
public class Model {

    private Matrix4f transform = new Matrix4f();
    private Vector3f position = new Vector3f(0.0f, 0.0f, 0.0f);
    private Vector3f rotation = new Vector3f(0.0f, 0.0f, 0.0f);
    private Vector3f scale = new Vector3f(0.0f, 0.0f, 0.0f);

    private Mesh mesh;

    public Model(Mesh mesh) {

        this.mesh = mesh;
        transform.setIdentity();
    }


    public void translate(float x, float y, float z) {
        translate(new Vector3f(x, y, z));
    }

    public void translate(Vector3f vec) {
        transform.translate(vec);
    }

    public void rotate(float x, float y, float z) {
        rotateX(x);
        rotateY(y);
        rotateZ(z);
    }

    public void rotateX(float angle) {
        transform.rotate(angle, new Vector3f(1f, 0.0f, 0.0f));
    }

    public void rotateY(float angle) {
        transform.rotate(angle, new Vector3f(0.0f, 1f, 0.0f));
    }

    public void rotateZ(float angle) {
        transform.rotate(angle, new Vector3f(0.0f, 0.0f, 1f));
    }

    public void scale(float x, float y, float z) {
        scale(new Vector3f(x, y, z));
    }

    public void scale(Vector3f vec) {
        transform.scale(vec);
    }

    public void draw() {

        mesh.draw();

    }

    public Matrix4f getTransform() {
        return transform;
    }

}

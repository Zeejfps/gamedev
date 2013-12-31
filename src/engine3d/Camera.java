package engine3d;

import engine3d.util.mathOld.Matrix4f;
import engine3d.util.mathOld.Vector3f;

/**
 * Created by Zeejfps on 12/19/13.
 */
public class Camera {

    private static final Vector3f Y_UP = new Vector3f(0, 1, 0);

    private Vector3f position, forward, up;

    private final float fov, aspect, zNear, zFar;
    private Matrix4f projectionMatrix = new Matrix4f();
    private Matrix4f viewMatrix = new Matrix4f();

    public Camera(float fov, float aspect, float zNear, float zFar) {

        this.fov = fov;
        this.aspect = aspect;
        this.zNear = zNear;
        this.zFar = zFar;

        position = new Vector3f(0,0,0);
        forward = new Vector3f(0,0,1);
        up = new Vector3f(0,1,0);

        createProjection();
        createView();

    }

    public void render() {

    }

    private void createProjection() {

        final float tanHalfFov = (float)Math.tan(Math.toRadians(fov * 0.5f));
        final float zRange = zFar - zNear;

        projectionMatrix.setZero();

        projectionMatrix.m[0][0] = 1.0f / tanHalfFov * aspect;
        projectionMatrix.m[1][1] = 1.0f / tanHalfFov;
        projectionMatrix.m[2][2] = -(zFar / zRange);
        projectionMatrix.m[3][2] = -1.0f;
        projectionMatrix.m[2][3] = -(zFar * zNear / zRange);

    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public void rotateX(float angle) {

        Vector3f hAxis = Y_UP.cross(forward);

        //Vector.rotate(angle, hAxis);
        forward.normalize();

        up = forward.cross(hAxis);
        up.normalize();

    }

    public void rotateY(float angle) {

        Vector3f hAxis = Y_UP.cross(forward);

        //Vector.rotate(angle, Y_UP);
        forward.normalize();

        up = forward.cross(hAxis);
        up.normalize();

    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getForward() {
        return forward;
    }

    public Vector3f getUp() {
        return up;
    }

    public Vector3f getLeft() {
        return forward.cross(up);
    }

    public Vector3f getRight() {
        return up.cross(forward);
    }

    public void move(Vector3f direction, float amount) {
        position.add(direction.mul(amount));
    }

    public void createView() {

        Vector3f u = forward.cross(up);

        viewMatrix.m[0][0] = u.getX();
        viewMatrix.m[0][1] = u.getY();
        viewMatrix.m[0][2] = u.getZ();

        viewMatrix.m[1][0] = up.getX();
        viewMatrix.m[1][1] = up.getY();
        viewMatrix.m[1][2] = up.getZ();

        viewMatrix.m[2][0] = forward.getX();
        viewMatrix.m[2][1] = forward.getY();
        viewMatrix.m[2][2] = forward.getZ();

    }

    public Matrix4f getViewMatrix() {

        Vector3f u = forward.cross(up);

        viewMatrix.m[0][0] = u.getX();
        viewMatrix.m[0][1] = u.getY();
        viewMatrix.m[0][2] = u.getZ();

        viewMatrix.m[1][0] = up.getX();
        viewMatrix.m[1][1] = up.getY();
        viewMatrix.m[1][2] = up.getZ();

        viewMatrix.m[2][0] = forward.getX();
        viewMatrix.m[2][1] = forward.getY();
        viewMatrix.m[2][2] = forward.getZ();

        return viewMatrix;
    }

}

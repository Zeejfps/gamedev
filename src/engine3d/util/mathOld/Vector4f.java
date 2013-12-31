package engine3d.util.mathOld;

/**
 * Created by Zeejfps on 12/20/13.
 */
public class Vector4f extends Vector {

    public Vector4f(float x, float y, float z, float w) {
        super(x, y, z, w);
    }

    public Vector4f(Vector3f vec, float w) {
        this(vec.getX(), vec.getY(), vec.getZ(), w);
    }

    public Vector4f(Vector4f vec) {
        this(vec.getX(), vec.getY(), vec.getZ(), vec.getW());
    }

    public Vector4f() {
        this(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public Vector4f add(Vector4f v) {

        final Vector4f newVec = new Vector4f();
        Vector.add(this, v, newVec);

        return newVec;
    }

    public Vector4f add(float value) {

        final Vector4f newVec = new Vector4f();
        Vector.add(this, value, newVec);

        return newVec;
    }

    public Vector4f sub(Vector4f v) {

        final Vector4f newVec = new Vector4f();
        Vector.sub(this, v, newVec);

        return newVec;
    }

    public Vector4f sub(float value) {

        final Vector4f newVec = new Vector4f();
        Vector.sub(this, value, newVec);

        return newVec;
    }

    public Vector4f mul(Vector4f v) {

        final Vector4f newVec = new Vector4f();
        Vector.mul(this, v, newVec);

        return newVec;
    }

    public Vector4f mul(float value) {

        final Vector4f newVec = new Vector4f();
        Vector.mul(this, value, newVec);

        return newVec;
    }

    public Vector4f div(Vector4f v) {

        final Vector4f newVec = new Vector4f();
        Vector.div(this, v, newVec);

        return newVec;
    }

    public Vector4f div(float value) {

        final Vector4f newVec = new Vector4f();
        Vector.div(this, value, newVec);

        return newVec;
    }

    public float getX() {
        return components[0];
    }

    public float getY() {
        return components[1];
    }

    public float getZ() {
        return components[2];
    }

    public float getW() {
        return components[3];
    }

    public void setX(float x) {
        components[0] = x;
    }

    public void setY(float y) {
        components[1] = y;
    }

    public void setZ(float z) {
        components[2] = z;
    }

    public void setW(float w) {
        components[3] = w;
    }

    @Override
    public String toString() {
        return "Vector4f[x: " + components[0] + " y: "+ components[1] + " z: " + components[2] + " w: " + components[3] + "]";
    }

}

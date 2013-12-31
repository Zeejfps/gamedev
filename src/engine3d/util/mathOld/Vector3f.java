package engine3d.util.mathOld;

/**
 * Created by Zeejfps on 12/20/13.
 */
public class Vector3f extends Vector {

    public Vector3f(float x, float y, float z) {
        super(x, y, z);
    }

    public Vector3f(Vector3f vec) {
        this(vec.getX(), vec.getY(), vec.getZ());
    }

    public Vector3f() {
        this(0.0f, 0.0f, 0.0f);
    }

    public Vector3f add(Vector3f v) {

        final Vector3f newVec = new Vector3f();
        Vector.add(this, v, newVec);

        return newVec;
    }

    public Vector3f add(float value) {

        final Vector3f newVec = new Vector3f();
        Vector.add(this, value, newVec);

        return newVec;
    }

    public Vector3f sub(Vector3f v) {

        final Vector3f newVec = new Vector3f();
        Vector.sub(this, v, newVec);

        return newVec;
    }

    public Vector3f sub(float value) {

        final Vector3f newVec = new Vector3f();
        Vector.sub(this, value, newVec);

        return newVec;
    }

    public Vector3f mul(Vector3f v) {

        final Vector3f newVec = new Vector3f();
        Vector.mul(this, v, newVec);

        return newVec;
    }

    public Vector3f mul(float value) {

        final Vector3f newVec = new Vector3f();
        Vector.mul(this, value, newVec);

        return newVec;
    }

    public Vector3f div(Vector3f v) {

        final Vector3f newVec = new Vector3f();
        Vector.div(this, v, newVec);

        return newVec;
    }

    public Vector3f div(float value) {

        final Vector3f newVec = new Vector3f();
        Vector.div(this, value, newVec);

        return newVec;
    }

    public Vector3f cross(Vector3f v) {

        final float x = this.getY() * v.getZ() - this.getZ() * v.getY();
        final float y = this.getZ() * v.getX() - this.getX() * v.getZ();
        final float z = this.getX() * v.getY() - this.getY() * v.getX();

        return new Vector3f(x, y, z);
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

    public void setX(float x) {
        components[0] = x;
    }

    public void setY(float y) {
        components[1] = y;
    }

    public void setZ(float z) {
        components[2] = z;
    }

    @Override
    public String toString() {
        return "Vector3f[x: " + components[0] + " y: "+ components[1] + " z: " + components[2] + "]";
    }

}

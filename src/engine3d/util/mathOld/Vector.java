package engine3d.util.mathOld;

/**
 * Created by Zeejfps on 12/20/13.
 */
public abstract class Vector {

    protected float[] components;

    public Vector(float... components) {
        this.components = components;
    }

    public boolean equal(Vector v) {

        if (v.findLength() != findLength()) {
            return false;
        }

        for (int i = 0; i < components.length; i ++) {

            if (components[i] != v.components[i]) {
                return false;
            }

        }

        return true;
    }

    public float dot(Vector v) {

        float total = 0;
        for (float component : components) {

            total += component * component;

        }

        return total;
    }

    public void normalize() {

        final float length = findLength();
        for (float component : components) {

            component /= length;

        }

    }

    public float[] getComponents() {
        return components;
    }

    public Vector test(Vector lol) {

        return lol;
    }

    public float findLength() {

        float length = 0;

        for (float component : components) {
            length += component*component;
        }
        length = (float)Math.sqrt(length);

        return length;
    }

    public static void add(Vector left, Vector right, Vector result) {

        for (int i = 0; i < left.components.length; i ++) {
            result.components[i] = left.components[i] + right.components[i];
        }

    }

    public static void add(Vector left, float value, Vector result) {

        for (int i = 0; i < left.components.length; i++) {
            result.components[i] = left.components[i] + value;
        }

    }

    public static void sub(Vector left, Vector right, Vector result) {

        for (int i = 0; i < left.components.length; i ++) {
            result.components[i] = left.components[i] - right.components[i];
        }

    }

    public static void sub(Vector left, float value, Vector result) {

        for (int i = 0; i < left.components.length; i++) {
            result.components[i] = left.components[i] - value;
        }

    }

    public static void mul(Vector left, Vector right, Vector result) {

        for (int i = 0; i < left.components.length; i ++) {
            result.components[i] = left.components[i] * right.components[i];
        }

    }

    public static void mul(Vector left, float value, Vector result) {

        for (int i = 0; i < left.components.length; i++) {
            result.components[i] = left.components[i] * value;
        }

    }

    public static void div(Vector left, Vector right, Vector result) {

        for (int i = 0; i < left.components.length; i ++) {
            result.components[i] = left.components[i] / right.components[i];
        }

    }

    /**
     * This method will divide the vector on the @param left by the @param value and store the results in @param result.
     *
     * @param left vector to be divided
     * @param value the value to divided by
     * @param result vector in which to store the results
     */
    public static void div(Vector left, float value, Vector result) {

        for (int i = 0; i < left.components.length; i++) {
            result.components[i] = left.components[i] / value;
        }

    }

}

package engine3d.util.mathOld;

import java.nio.FloatBuffer;

/**
 * Created by Zeejfps on 12/20/13.
 */
public class Matrix {

    public float[][] m;

    public Matrix(float[][] m) {
        this.m = m;
        setIdentity();
    }

    public Matrix(Matrix matrix) {
        this(matrix.m);
    }

    public void setIdentity() {

        for (int i = 0; i < m.length; i ++) {

            for (int j = 0; j < m[i].length; j ++) {

                if (j == i) {
                    m[i][j] = 1;
                } else {
                    m[i][j] = 0;
                }

            }

        }

    }

    public void setZero() {

        for (float[] f : m) {
            for (float value : f) {
                value = 0;
            }
        }
    }

    public void store(Matrix mat) {
        mat.m = this.m;
    }

    public void store(FloatBuffer fb) {

        for (float[] f : m) {

            for (float value : f) {
                fb.put(value);
            }

        }

    }

    public static void mul(Matrix left, Matrix right, Matrix result) {


        if (left.m[0].length != right.m.length) {
            System.err.println("Matrix is incompatible size!");
            return;
        }

        final Matrix temp = new Matrix(new float[left.m.length][right.m[0].length]);

        for (int k = 0; k < left.m.length; k++) {

            for (int i = 0; i < right.m[0].length; i ++) {

                float value = 0;
                for (int j = 0; j < right.m.length; j ++) {

                    value += left.m[k][j] * right.m[j][i];

                }
                temp.m[k][i] = value;

            }

        }

        temp.store(result);
    }

    public static void mul(Matrix mat, Vector vec, Vector result) {


        if (mat.m[0].length != vec.components.length || mat.m.length != vec.components.length) {
            System.err.println("Vector is incompatible size!");
            return;
        }

        for (int i = 0; i < mat.m.length; i ++) {

            float value = 0;
            for (int j = 0; j < vec.components.length; j ++) {

                value += mat.m[i][j] * vec.components[j];

            }
            result.components[i] = value;

        }

    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m.length; i ++) {

            for (int j = 0; j < m[i].length; j ++) {

                sb.append(m[i][j] + " ");

            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public float[][] getData() {
        return m;
    }

}

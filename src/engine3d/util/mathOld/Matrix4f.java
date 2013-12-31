package engine3d.util.mathOld;

/**
 * Created by Zeejfps on 12/20/13.
 */
public class Matrix4f extends Matrix {

    public Matrix4f() {
        super(new float[4][4]);
    }

    public Vector4f mul(Vector4f vec) {

        final Vector4f result = new Vector4f();
        Matrix.mul(this, vec, result);

        return result;
    }

    public Matrix4f mul(Matrix4f mat) {

        final Matrix4f result = new Matrix4f();
        Matrix.mul(this, mat, result);

        return result;
    }

    public static Matrix4f genTranslationMatrix(Vector3f vec) {
        return genTranslationMatrix(vec.getX(), vec.getY(), vec.getZ());
    }

    public static Matrix4f genTranslationMatrix(float x, float y, float z) {

        final Matrix4f theMatrix = new Matrix4f();

        theMatrix.m[0][3] = x;
        theMatrix.m[1][3] = y;
        theMatrix.m[2][3] = z;

        return theMatrix;

    }

    public static Matrix4f genRotationMatrix(Vector3f vec) {
        return genRotationMatrix(vec.getX(), vec.getY(), vec.getZ());
    }

    public static Matrix4f genRotationMatrix(float x, float y, float z) {

        x = (float) Math.toRadians(x);
        y = (float) Math.toRadians(y);
        z = (float) Math.toRadians(z);

        final Matrix4f rx = new Matrix4f();
        rx.m[0][0] = 1;
        rx.m[1][1] = (float)Math.cos(x);
        rx.m[1][2] = -(float)Math.sin(x);
        rx.m[2][1] = (float)Math.sin(x);
        rx.m[2][2] = (float)Math.cos(x);
        rx.m[3][3] = 1;

        final Matrix4f ry = new Matrix4f();
        ry.m[0][0] = (float)Math.cos(y);
        ry.m[0][2] = -(float)Math.sin(y);
        ry.m[1][1] = 1;
        ry.m[2][0] = (float)Math.sin(y);
        ry.m[2][2] = (float)Math.cos(y);
        ry.m[3][3] = 1;

        final Matrix4f rz = new Matrix4f();
        rz.m[0][0] = (float)Math.cos(z);
        rz.m[0][1] = -(float)Math.sin(z);
        rz.m[1][0] = (float)Math.sin(z);
        rz.m[1][1] = (float)Math.cos(z);
        rz.m[2][2] = 1;
        rz.m[3][3] = 1;

        final Matrix4f theFinalMatrix = rz.mul(ry.mul(rx));
        return theFinalMatrix;

    }

}

package engine3d.util;

import engine3d.util.mathOld.Vector3f;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Zeejfps on 12/18/13.
 */
public class Transform {

    public static Matrix4f genTranslationMatrix(Vector3f vec) {
        return Transform.genTranslationMatrix(vec.getX(), vec.getY(), vec.getZ());
    }

    public static Matrix4f genTranslationMatrix(float x, float y, float z) {

        Matrix4f theMatrix = new Matrix4f();

        theMatrix.m00 = 1;
        theMatrix.m11 = 1;
        theMatrix.m22 = 1;
        theMatrix.m33 = 1;

        theMatrix.m03 = x;
        theMatrix.m13 = y;
        theMatrix.m23 = z;

        return theMatrix;

    }

    public static Matrix4f genRotationMatrix(Vector3f vec) {
        return Transform.genRotationMatrix(vec.getX(), vec.getY(), vec.getZ());
    }

    public static Matrix4f genRotationMatrix(float x, float y, float z) {

        x = (float) Math.toRadians(x);
        y = (float) Math.toRadians(y);
        z = (float) Math.toRadians(z);

        Matrix4f rx = new Matrix4f();
        rx.m00 = 1;
        rx.m11 = (float)Math.cos(x);
        rx.m12 = -(float)Math.sin(x);
        rx.m21 = (float)Math.sin(x);
        rx.m22 = (float)Math.cos(x);
        rx.m33 = 1;

        Matrix4f ry = new Matrix4f();
        ry.m00 = (float)Math.cos(y);
        ry.m02 = -(float)Math.sin(y);
        ry.m11 = 1;
        ry.m20 = (float)Math.sin(y);
        ry.m22 = (float)Math.cos(y);
        ry.m33 = 1;

        Matrix4f rz = new Matrix4f();
        rz.m00 = (float)Math.cos(z);
        rz.m01 = -(float)Math.sin(z);
        rz.m10 = (float)Math.sin(z);
        rz.m11 = (float)Math.cos(z);
        rz.m22 = 1;
        rz.m33 = 1;

        Matrix4f theFinalMatrix = new Matrix4f();
        Matrix4f temp = new Matrix4f();
        Matrix4f.mul(ry, rx, temp);
        Matrix4f.mul(rz, temp, theFinalMatrix);

        return theFinalMatrix;

    }

}

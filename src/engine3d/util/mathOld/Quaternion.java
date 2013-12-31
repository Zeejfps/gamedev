package engine3d.util.mathOld;

/**
 * Created by Zeejfps on 12/20/13.
 */
public class Quaternion {

    private float x, y, z, w;

    public Quaternion(Vector3f axis, float angle) {

        angle = (float)Math.toRadians(angle);
        final float sinHalfAngle = (float)Math.sin(angle/2);

        this.x = axis.getX()*sinHalfAngle;
        this.y = axis.getY()*sinHalfAngle;
        this.z = axis.getZ()*sinHalfAngle;
        this.w = (float)Math.cos(angle/2);

    }

    public Quaternion() {

        x = 0;
        y = 0;
        z = 0;
        w = 0;

    }

    public float getW() {
        return w;
    }

    @Override
    public String toString() {

        return "Quaternion[" + "x: " + x + " y: " + y + " z: " + z + " w: " + w +"]";

    }

    public Quaternion getInverse() {

        Quaternion result = new Quaternion();
        result.x = -this.x;
        result.y = -this.y;
        result.z = -this.z;
        result.w = -this.w;

        return result;
    }

    public Quaternion mul(Quaternion q) {

        Quaternion result = new Quaternion();

        Vector3f vec = new Vector3f(x, y, z);
        Vector3f qVec = new Vector3f(q.x, q.y, q.z);

        result.w = w*q.w + vec.dot(qVec);
        vec = vec.mul(q.w).add(qVec.mul(w)).add(vec.cross(qVec));

        result.x = vec.getX();
        result.y = vec.getY();
        result.z = vec.getZ();

        return result;
    }

    public Vector3f mul(Vector3f vec) {

        Quaternion result = new Quaternion();
        result.w = 0;
        result.x = vec.getX();
        result.y = vec.getY();
        result.z = vec.getZ();

        Vector3f resultV = new Vector3f(x, y, z).cross(vec);
        return vec.add(resultV.mul(2*w)).add((new Vector3f(x, y, z).cross(resultV)).mul(2));
    }

}

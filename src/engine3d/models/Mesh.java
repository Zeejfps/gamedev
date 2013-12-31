package engine3d.models;

import engine3d.util.mathOld.Matrix4f;
import engine3d.util.mathOld.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by Zeejfps on 12/19/13.
 */
public class Mesh {

    public Vector3f position = new Vector3f(0.0f, 0.0f, 0.0f);
    public Vector3f rotation = new Vector3f(0.0f, 0.0f, 0.0f);
    public Vector3f scale    = new Vector3f(1.0f, 1.0f, 1.0f);
    private Matrix4f transform = new Matrix4f();

    private final float[] vertices;
    private final int[] indices;
    private int vbo;
    private int ibo;

    public Mesh(float[] vertices, int[] indices) {

        this.vertices = vertices;
        this.indices = indices;

        vbo = glGenBuffers();
        ibo = glGenBuffers();

        FloatBuffer fb = BufferUtils.createFloatBuffer(vertices.length);
        fb.put(vertices);
        fb.flip();

        IntBuffer ib = BufferUtils.createIntBuffer(indices.length);
        ib.put(indices);
        ib.flip();

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, fb, GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, ib, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

    }

    public void draw() {

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

    }

    public void delete() {

        glDeleteBuffers(vbo);
        glDeleteBuffers(ibo);
    }

    public float[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public Matrix4f getTransform() {

        transform.setIdentity();

        System.out.println(rotation);
        transform.mul(Matrix4f.genRotationMatrix(rotation));
/*
        transform.rotate(45, new Vector3f(1f, 0f, 0f));
        transform.rotate(rotation.y, new Vector3f(0f, 1f, 0f));
        transform.rotate(rotation.z, new Vector3f(0f, 0f, 1f));
*/
        transform = Matrix4f.genRotationMatrix(rotation).mul(transform);
        System.out.println(transform);
        return transform;
    }

}

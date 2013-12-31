package engine2d;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

/**
 * Created by Zeejfps on 12/28/13.
 */
public class Entity implements GameComponent {

    private final int vbo;
    private final int ibo;
    public float x;
    public float y;

    float[] vertices;
    int[] indices;

    public Entity(Sprite sprite, int width, int height) {

        vertices = new float[]{
                x,          y,
                x + width,  y,
                x + width,  y - height,
                x,          y - height
        };

        indices = new int[]{
                2, 1, 0,
                3, 2, 0
        };

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

    @Override
    public void update() {

    }

    @Override
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

}

package engine3d.shaders;

import engine3d.util.mathOld.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by Zeejfps on 12/16/13.
 */
public class Shader {

    private static final String DEFAULT_SHADER_PATH = "./res/shaders/";

    private final HashMap<String, Integer> uniforms = new HashMap<String, Integer>();
    private int programAddress = 0;

    public Shader(ShaderComponent... shaderComponentObjects) {

        programAddress = glCreateProgram();
        if (programAddress == 0) {
            System.err.println("Error allocating memory for shader program!");
            System.exit(1);
        }

        final ArrayList<Integer> shaderAddresses = new ArrayList<Integer>();
        for (ShaderComponent shaderComponent : shaderComponentObjects) {
            attachShader(shaderComponent, shaderAddresses);
        }

        glLinkProgram(programAddress);
        if (glGetProgrami(programAddress, GL_LINK_STATUS) == 0) {
            System.err.println(glGetProgramInfoLog(programAddress, 1024));
            System.exit(1);
        }

        for (Integer address : shaderAddresses) {
            glDetachShader(programAddress, address);
            glDeleteShader(address);
        }

        glValidateProgram(programAddress);
        if(glGetProgrami(programAddress, GL_VALIDATE_STATUS) == 0) {
            System.err.println(glGetProgramInfoLog(programAddress, 1024));
            System.exit(1);
        }

    }

    private void attachShader(ShaderComponent shaderComponent, ArrayList<Integer> addresses) {

        int shaderAddress = glCreateShader(shaderComponent.getType());
        if (shaderAddress == 0) {
            System.err.println("Error allocating memory for shaderComponent!");
            System.exit(1);
        }

        glShaderSource(shaderAddress, shaderComponent.getSource());
        glCompileShader(shaderAddress);
        if (glGetShaderi(shaderAddress, GL_COMPILE_STATUS) == 0) {
            System.err.println("Failed to compile shaderComponent!");
            System.err.println(glGetShaderInfoLog(shaderAddress, 1024));
            System.exit(1);
        }

        glAttachShader(programAddress, shaderAddress);
        addresses.add(shaderAddress);

    }

    public void addUniforms(String... names) {

        int uniformAddress;
        for (String name : names) {

            uniformAddress = glGetUniformLocation(programAddress, name);
            if (uniformAddress == -1) {
                System.err.println("Could not find used uniform with name: " + name + " in the program!");
                System.exit(1);
            }

            uniforms.put(name, uniformAddress);
        }
    }

    public int getUniformAddress(String name) {
        return uniforms.get(name);
    }

    public void setUniform(String name, float value) {
        glUniform1f(uniforms.get(name), value);
    }

    public void setUniform(String name, Matrix4f value) {

        final FloatBuffer fb = BufferUtils.createFloatBuffer(16);
        value.store(fb);
        fb.flip();

        glUniformMatrix4(uniforms.get(name), true, fb);
    }

    public int getAddress() {
        return programAddress;
    }

}

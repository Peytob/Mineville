package ru.peytob.mineville.model.opengl;

import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;

/**
 * Compiles the shader code and saves the results.
 */
public class Shader {
    /**
     * Shader id inside OpenGL.
     */
    private final int id;

    /**
     * Type of the shader (from GL33).
     */
    private final int type;

    /**
     * Base constructor.
     *
     * @param code Source code of this shader.
     * @param type Type of the shader.
     * @throws RuntimeException If there were errors during compilation of the shader.
     */
    public Shader(String code, int type) throws RuntimeException {
        this.type = type;
        this.id = glCreateShader(this.type);

        compileShader(code);
    }

    /**
     * Compiles shader.
     *
     * @param code Source code of shader.
     * @throws RuntimeException If there were errors during compilation of the shader.
     */
    private void compileShader(String code) throws RuntimeException {
        glShaderSource(id, code);
        glCompileShader(id);

        try (MemoryStack stack = MemoryStack.stackPush()) {
            final IntBuffer intBuffer = stack.mallocInt(1);
            glGetShaderiv(id, GL_COMPILE_STATUS, intBuffer);
            if (intBuffer.get(0) == 0) {
                throw new RuntimeException("Shader not compiled: " + glGetShaderInfoLog(id));
            }
        }
    }

    /**
     * Deletes a shader object inside OpenGL.
     */
    public void destroy() {
        glDeleteShader(id);
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }
}

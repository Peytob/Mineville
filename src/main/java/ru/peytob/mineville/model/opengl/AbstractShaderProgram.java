package ru.peytob.mineville.model.opengl;

import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;

/**
 * Base class for all shader programs. This class can create and safe delete shader program from memory.
 */
public abstract class AbstractShaderProgram {
    /**
     * Shader program id inside OpenGL.
     */
    protected final int id;

    /**
     * Flag, that the program was successfully linked.
     */
    private boolean isLinked;

    public AbstractShaderProgram() {
        this.isLinked = false;
        this.id = glCreateProgram();
    }

    /**
     * Deletes shader program inside OpenGL.
     */
    public void destroy() {
        glDeleteProgram(id);
    }

    /**
     * Installs the program object as part of current rendering state.
     */
    public void use() {
        glUseProgram(id);
    }

    /**
     * Flag indicating that the program was successfully linked.
     *
     * @return True, if that program was successfully linked.
     */
    public boolean isLinked() {
        return isLinked;
    }

    /**
     * Attach shader to current shader program.
     *
     * @param shader Shader id.
     */
    public void attachShader(Shader shader) {
        glAttachShader(id, shader.getId());
    }

    /**
     * Links and compiles shader program.
     *
     * @throws RuntimeException В случае неуспешной компиляции программы.
     */
    public void link() throws RuntimeException {
        glLinkProgram(id);

        try (MemoryStack stack = MemoryStack.stackPush()) {
            final IntBuffer intBuffer = stack.mallocInt(1);
            glGetProgramiv(id, GL_LINK_STATUS, intBuffer);
            isLinked = intBuffer.get(0) == GL_TRUE;
            if (!isLinked) {
                String log = glGetProgramInfoLog(id);
                throw new RuntimeException("Link shader program error. Log:\n" + log);
            }
        }

        searchUniforms();
    }

    /**
     * Search uniforms locations.
     *
     * @throws RuntimeException If any variable was not found.
     */
    abstract protected void searchUniforms() throws RuntimeException;
}

package ru.peytob.mineville.model.graphic.shader;

import ru.peytob.mineville.model.opengl.AbstractShaderProgram;

import static org.lwjgl.opengl.GL33.*;

/**
 * Shader to drawing all models in game world.
 */
public class WorldShader extends AbstractShaderProgram {
    /**
     * Model matrix location.
     */
    private int modelMatrixLocation;

    /**
     * Projection matrix location.
     */
    private int projectionMatrixLocation;

    /**
     * View matrix location.
     */
    private int viewMatrixLocation;

    public WorldShader() {
        super();
    }

    @Override
    protected void searchUniforms() throws RuntimeException {
//        final String modelMatrix = "u_model";
//        modelMatrixLocation = glGetUniformLocation(id, modelMatrix);
//        if (modelMatrixLocation == -1) {
//            throw new RuntimeException("Uniform " + modelMatrix + " not found.");
//        }
//
//        final String projectionMatrix = "u_projection";
//        projectionMatrixLocation = glGetUniformLocation(id, projectionMatrix);
//        if (projectionMatrixLocation == -1) {
//            throw new RuntimeException("Uniform " + projectionMatrix + " not found.");
//        }
//
//        final String viewMatrix = "u_view";
//        viewMatrixLocation = glGetUniformLocation(id, viewMatrix);
//        if (viewMatrixLocation == -1) {
//            throw new RuntimeException("Uniform " + viewMatrix + " not found.");
//        }
    }
}

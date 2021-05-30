package ru.peytob.mineville.model.graphic;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.opengl.GL.*;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Stores information about window.
 */
public class Window {
    /**
     * Pointer to window object in memory.
     */
    private final long pointer;

    public Window(String title, int width, int height) {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        pointer = glfwCreateWindow(width, height, title, NULL, NULL);
        if (pointer == NULL) {
            throw new RuntimeException("Window is not created.");
        }

        glfwMakeContextCurrent(pointer);
        glfwShowWindow(pointer);

//        glfwSetInputMode(pointer, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        createCapabilities();

        try (MemoryStack stack = MemoryStack.stackPush()) {
            final IntBuffer w = stack.mallocInt(1);
            final IntBuffer h = stack.mallocInt(1);
            glfwGetFramebufferSize(pointer, w, h);
            int contentWidth = w.get();
            int contentHeight = h.get();
            glViewport(0, 0, contentWidth, contentHeight);
        }
    }

    /**
     * Destroys window and free all resources.
     */
    public void destroy() {
        glfwFreeCallbacks(pointer);
        glfwDestroyWindow(pointer);
    }

    /**
     * Checks window is open.
     * @return True, if window should close.
     */
    public boolean isShouldClose() {
        return glfwWindowShouldClose(pointer);
    }

    /**
     * Close window (that is sets ShouldClose to true).
     */
    public void close() {
        glfwSetWindowShouldClose(pointer, true);
    }

    /**
     * Polls all events.
     */
    public void pollEvents() {
        glfwPollEvents();
    }

    /**
     * Returns pointer to window.
     * @return Pointer to window
     */
    public long getPointer() {
        return pointer;
    }
}

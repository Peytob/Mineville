package ru.peytob.mineville.view;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;
import org.lwjgl.glfw.GLFWScrollCallbackI;
import org.lwjgl.system.MemoryStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.peytob.mineville.math.Vec2;
import ru.peytob.mineville.math.Vec2i;
import ru.peytob.mineville.view.input.KeyboardMouseInput;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
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

    private final KeyboardMouseInput keyboardMouseInput;

    public Window(String title, int width, int height) {
        Logger logger = LoggerFactory.getLogger(Window.class);

        logger.info("Window creating begin.");

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

        logger.info("Window has been created successful.");

        glfwMakeContextCurrent(pointer);

        glfwSwapInterval(1);

        glfwSetInputMode(pointer, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        createCapabilities();

        Vec2i framebufferSize = getFramebufferSizes();
        glViewport(0, 0, framebufferSize.getX(), framebufferSize.getY());

        this.keyboardMouseInput = new KeyboardMouseInput(this);
    }

    /**
     * Destroys window and free all resources.
     */
    public void destroy() {
        Logger logger = LoggerFactory.getLogger(Logger.class);
        logger.info("Window destroying.");
        glfwFreeCallbacks(pointer);
        glfwDestroyWindow(pointer);
        logger.info("Window has been destroyed successful.");
    }

    /**
     * Checks if the window should close.
     *
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
     * Swaps screen buffers and display some stuff.
     */
    public void display() {
        glfwSwapBuffers(pointer);
    }

    /**
     * Show window.
     */
    public void show() {
        glfwShowWindow(pointer);
    }

    /**
     * Returns pointer to window.
     *
     * @return Pointer to window
     */
    public long getPointer() {
        return pointer;
    }

    /**
     * Returns cursor position relative to the content area.
     *
     * @return Cursor position relative to the content area.
     */
    public Vec2 getCursorPosition() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            final DoubleBuffer width = stack.mallocDouble(1);
            final DoubleBuffer height = stack.mallocDouble(1);
            glfwGetCursorPos(pointer, width, height);
            return new Vec2((float) width.get(0), (float) height.get(0));
        }
    }

    /**
     * Returns sizes of this window.
     * @return Sizes of window.
     */
    public Vec2i getWindowSizes() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            final IntBuffer width = stack.mallocInt(1);
            final IntBuffer height = stack.mallocInt(1);
            glfwGetWindowSize(pointer, width, height);
            return new Vec2i(width.get(0), height.get(0));
        }
    }

    /**
     * Return sizes of framebuffer for this window.
     * @return Sizes of framebuffer.
     */
    public Vec2i getFramebufferSizes() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            final IntBuffer width = stack.mallocInt(1);
            final IntBuffer height = stack.mallocInt(1);
            glfwGetFramebufferSize(pointer, width, height);
            return new Vec2i(width.get(0), height.get(0));
        }
    }

    public KeyboardMouseInput getKeyboardMouseInput() {
        return this.keyboardMouseInput;
    }

    public void setMouseButtonCallback(GLFWMouseButtonCallbackI callback) {
        glfwSetMouseButtonCallback(pointer, callback);
    }

    public void setCursorPositionCallback(GLFWCursorPosCallbackI callback) {
        glfwSetCursorPosCallback(pointer, callback);
    }

    public void setKeyCallback(GLFWKeyCallbackI callback) {
        glfwSetKeyCallback(pointer, callback);
    }

    public void setScrollCallback(GLFWScrollCallbackI callback) {
        glfwSetScrollCallback(pointer, callback);
    }
}

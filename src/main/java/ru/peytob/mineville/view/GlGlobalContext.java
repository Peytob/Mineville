package ru.peytob.mineville.view;

import static org.lwjgl.opengl.GL33.*;

public class GlGlobalContext {
    public GlGlobalContext() {

    }

    public String getRenderer() {
        return glGetString(GL_RENDERER);
    }

    public String getVendor() {
        return glGetString(GL_VENDOR);
    }

    public String getMajorVersion() {
        return glGetString(GL_MAJOR_VERSION);
    }

    public String getMinorVersion() {
        return glGetString(GL_MINOR_VERSION);
    }

    public String getVersion() {
        return glGetString(GL_VERSION);
    }

    public String getExtension(int index) {
        return glGetStringi(GL_EXTENSIONS, index);
    }

    public String[] getExtensions() {
        final int extensionsNumber = getExtensionsNumber();
        String[] extensions = new String[extensionsNumber];
        for (int i = 0; i < extensionsNumber; ++i) {
            extensions[i] = getExtension(i);
        }
        return extensions;
    }

    public int getExtensionsNumber() {
        return glGetInteger(GL_NUM_EXTENSIONS);
    }
}

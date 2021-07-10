package ru.peytob.mineville.model.opengl;

import ru.peytob.mineville.model.graphic.Image;

import static org.lwjgl.opengl.GL33.*;

public class Texture {
    /**
     * Id of the texture inside OpenGL.
     */
    private final int id;

    public Texture(Image image) {
        this.id = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, id);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(),
                0, GL_RGBA, GL_UNSIGNED_BYTE, image.getData());

        glGenerateMipmap(GL_TEXTURE_2D);
    }

    public void destroy() {
        glDeleteTextures(id);
    }

    public int getId() {
        return id;
    }
}

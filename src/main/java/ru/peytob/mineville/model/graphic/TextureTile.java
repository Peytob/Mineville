package ru.peytob.mineville.model.graphic;

public class TextureTile {
    private final float x;
    private final float y;
    private final TexturesPack parent;

    public TextureTile(float x, float y, TexturesPack parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getAbsoluteWidth() {
        return parent.getAbsoluteWidth();
    }

    public float getAbsoluteHeight() {
        return parent.getAbsoluteHeight();
    }
}

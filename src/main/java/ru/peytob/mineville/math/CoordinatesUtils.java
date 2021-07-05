package ru.peytob.mineville.math;

public class CoordinatesUtils {
    static public int toFlat2D(int x, int y, int w) {
        return x + y * w;
    }

    static public int toFlat3D(int x, int y, int z, int w, int h) {
        return x + w * (y + h * z);
    }
}

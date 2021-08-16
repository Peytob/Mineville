package ru.peytob.mineville.math;

/**
 * Some math methods for working with coordinates.
 */
public class CoordinatesUtils {
    /**
     * Converts two-dimensional integer coordinates to one-dimensional integer coordinates.
     * my2dArray[x][y] => my1dArray[toFlat2D(x, y, my2dArray.length)].
     * @param x X position vector component.
     * @param y Y position vector component.
     * @param w Width of the grid.
     * @return One-dimensional integer coordinates
     */
    static public int toFlat2D(int x, int y, int w) {
        return x + y * w;
    }

    /**
     * Converts three-dimensional integer coordinates to one-dimensional integer coordinates.
     * my3dArray[x][y][z] => my1dArray[toFlat2D(x, y, z, my3dArray.length, my3dArray[0].length)].
     * @param x X position vector component.
     * @param y Y position vector component.
     * @param z Z position vector component.
     * @param w Width of the grid.
     * @param h Height of the grid.
     * @return One-dimensional integer coordinates
     */
    static public int toFlat3D(int x, int y, int z, int w, int h) {
        return x + w * (y + h * z);
    }

    /**
     * Translates the coordinates on a square two-dimensional grid and returns the coordinates of the grid square
     * containing the specified point.
     * toBiggerGrid2D(4, 63, 16) => vec2i(0, 3)
     * toBiggerGrid2D(-2, -17, 16) => vec2i(-1, -2)
     * @param x X position vector component.
     * @param y Y position vector component.
     * @param side Size of grid square.
     * @return Coordinates of the grid square containing the specified point.
     */
    static public Vec2i toBiggerGrid2D(int x, int y, int side) {
        Vec2i position = new Vec2i(x / side, y / side);

        if (x < 0) {
            position.setX(position.getX() - 1);
        }

        if (y < 0) {
            position.setY(position.getY() - 1);
        }

        return position;
    }
}

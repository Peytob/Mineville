package ru.peytob.mineville.math;

import java.util.Arrays;
import java.util.Objects;

/**
 * Implements storage for 4x4 float matrix.
 */
public class ImmutableMat4 {
    /**
     * Matrix data.
     */
    protected final float[] data;

    /**
     * Creates a zero matrix.
     */
    public ImmutableMat4() {
        data = new float[16];
    }

    /**
     * Creates a matrix and fill it from data array.
     *
     * @param data Data for filling in the matrix.
     * @throws NumberFormatException If there are not 16 elements in the array.
     */
    public ImmutableMat4(float[] data) throws NumberFormatException {
        if (data.length != 16) {
            throw new NumberFormatException("Sizes of _data array should be 16 (4 x 4).");
        }

        this.data = Arrays.copyOf(data, data.length);
    }

    /**
     * Copy constructor.
     *
     * @param other Other matrix.
     */
    public ImmutableMat4(ImmutableMat4 other) {
        data = Arrays.copyOf(other.data, other.data.length);
    }

    /**
     * Returns element by index.
     *
     * @param index Index of element.
     * @return Element of matrix.
     * @throws IndexOutOfBoundsException If index is negative or more than 15.
     */
    public float get(int index) throws IndexOutOfBoundsException {
        return data[index];
    }

    /**
     * Returns element by row and column.
     *
     * @param row    Row of element.
     * @param column Column of element.
     * @return Element of matrix at specified row and column. Alias for get(_row * 4 + _column).
     * @throws IndexOutOfBoundsException If _row * 4 + _column is negative or more than 15.
     */
    public float get(int row, int column) throws IndexOutOfBoundsException {
        return get(row * 4 + column);
    }

    /**
     * Returns a raw data of matrix.
     *
     * @return Raw data of array.
     */
    public float[] toFloatArray() {
        return Arrays.copyOf(data, data.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableMat4 mat4 = (ImmutableMat4) o;
        return Arrays.equals(data, mat4.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                builder.append(get(i, j));
                builder.append(", ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}

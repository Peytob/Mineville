package ru.peytob.mineville.math;

public class Mat4 extends ImmutableMat4 {
    public Mat4() {
        super();
    }

    public Mat4(float[] data) throws NumberFormatException {
        super(data);
    }

    public Mat4(ImmutableMat4 other) {
        super(other);
    }

    /**
     * Sets value of specified element.
     *
     * @param index   Index of element.
     * @param element New data of element.
     * @return This matrix object (for chain calls).
     * @throws IndexOutOfBoundsException If index is negative or more than 15.
     */
    public Mat4 set(int index, float element) throws IndexOutOfBoundsException {
        data[index] = element;
        return this;
    }

    /**
     * Sets value of specified element. Alias for set(_row * 4 + _column).
     *
     * @param row     Row of element.
     * @param column  Column of element.
     * @param element New data of element.
     * @return This matrix object (for chain calls).
     * @throws IndexOutOfBoundsException If index is negative or more than 15.
     */
    public Mat4 set(int row, int column, float element) throws IndexOutOfBoundsException {
        set(row * 4 + column, element);
        return this;
    }

    /**
     * Computes sum of two matrices. Results will be saved in this object.
     *
     * @param right Right matrix.
     * @return This matrix object (for chain calls).
     */
    public Mat4 plus(ImmutableMat4 right) {
        for (int i = 0; i < data.length; ++i) {
            data[i] += right.get(i);
        }

        return this;
    }

    /**
     * Computes subtraction of two matrices. Results will be saved in this object.
     *
     * @param right Right matrix.
     * @return This matrix object (for chain calls).
     */
    public Mat4 minus(ImmutableMat4 right) {
        for (int i = 0; i < data.length; ++i) {
            data[i] -= right.get(i);
        }

        return this;
    }

    /**
     * Computes multiplication of two matrices. Results will be saved in this object.
     *
     * @param right Right matrix.
     * @return This matrix object (for chain calls).
     */
    public Mat4 multiplication(ImmutableMat4 right) {
        data[0] = get(0, 0) * right.get(0, 0) + get(1, 0) * right.get(0, 1) +
                get(2, 0) * right.get(0, 2) + get(3, 0) * right.get(0, 3);
        data[1] = get(0, 1) * right.get(0, 0) + get(1, 1) * right.get(0, 1) +
                get(2, 1) * right.get(0, 2) + get(3, 1) * right.get(0, 3);
        data[2] = get(0, 2) * right.get(0, 0) + get(1, 2) * right.get(0, 1) +
                get(2, 2) * right.get(0, 2) + get(3, 2) * right.get(0, 3);
        data[3] = get(0, 3) * right.get(0, 0) + get(1, 3) * right.get(0, 1) +
                get(2, 3) * right.get(0, 2) + get(3, 3) * right.get(0, 3);
        data[4] = get(0, 0) * right.get(1, 0) + get(1, 0) * right.get(1, 1) +
                get(2, 0) * right.get(1, 2) + get(3, 0) * right.get(1, 3);
        data[5] = get(0, 1) * right.get(1, 0) + get(1, 1) * right.get(1, 1) +
                get(2, 1) * right.get(1, 2) + get(3, 1) * right.get(1, 3);
        data[6] = get(0, 2) * right.get(1, 0) + get(1, 2) * right.get(1, 1) +
                get(2, 2) * right.get(1, 2) + get(3, 2) * right.get(1, 3);
        data[7] = get(0, 3) * right.get(1, 0) + get(1, 3) * right.get(1, 1) +
                get(2, 3) * right.get(1, 2) + get(3, 3) * right.get(1, 3);
        data[8] = get(0, 0) * right.get(2, 0) + get(1, 0) * right.get(2, 1) +
                get(2, 0) * right.get(2, 2) + get(3, 0) * right.get(2, 3);
        data[9] = get(0, 1) * right.get(2, 0) + get(1, 1) * right.get(2, 1) +
                get(2, 1) * right.get(2, 2) + get(3, 1) * right.get(2, 3);
        data[10] = get(0, 2) * right.get(2, 0) + get(1, 2) * right.get(2, 1) +
                get(2, 2) * right.get(2, 2) + get(3, 2) * right.get(2, 3);
        data[11] = get(0, 3) * right.get(2, 0) + get(1, 3) * right.get(2, 1) +
                get(2, 3) * right.get(2, 2) + get(3, 3) * right.get(2, 3);
        data[12] = get(0, 0) * right.get(3, 0) + get(1, 0) * right.get(3, 1) +
                get(2, 0) * right.get(3, 2) + get(3, 0) * right.get(3, 3);
        data[13] = get(0, 1) * right.get(3, 0) + get(1, 1) * right.get(3, 1) +
                get(2, 1) * right.get(3, 2) + get(3, 1) * right.get(3, 3);
        data[14] = get(0, 2) * right.get(3, 0) + get(1, 2) * right.get(3, 1) +
                get(2, 2) * right.get(3, 2) + get(3, 2) * right.get(3, 3);
        data[15] = get(0, 3) * right.get(3, 0) + get(1, 3) * right.get(3, 1) +
                get(2, 3) * right.get(3, 2) + get(3, 3) * right.get(3, 3);

        return this;
    }

    /**
     * Computes multiplication of two matrices. Results will be saved in this object.
     *
     * @return This matrix object (for chain calls).
     */
    public Mat4 transpose() {
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                float temp = get(i, j);
                set(i, j, get(j, i));
                set(j, i, temp);
            }
        }

        return this;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

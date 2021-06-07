package ru.peytob.mineville.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Mat4Test {

    @Test
    public void testGet() {
        Mat4 someMatrix = new Mat4(new float[]{
                65, 34, 23, 24,
                12, 89, 5, 12,
                7, 87, 76, 11,
                1, 2, 3, 4
        });

        assertEquals(65.0f, someMatrix.get(0), 0.001);
        assertEquals(65.0f, someMatrix.get(0, 0), 0.001);

        assertEquals(89.0f, someMatrix.get(5), 0.001);
        assertEquals(89.0f, someMatrix.get(1, 1), 0.001);

        assertEquals(4.0f, someMatrix.get(15), 0.001);
        assertEquals(4.0f, someMatrix.get(3, 3), 0.001);

        assertEquals(87.0f, someMatrix.get(9), 0.001);
        assertEquals(87.0f, someMatrix.get(2, 1), 0.001);
    }

    @Test
    public void testSet() {
        Mat4 someMatrix = new Mat4(new float[]{
                65, 34, 23, 24,
                12, 89, 5, 12,
                7, 87, 76, 11,
                1, 2, 3, 4
        });

        assertEquals(65.0f, someMatrix.get(0), 0.001);
        assertEquals(65.0f, someMatrix.get(0, 0), 0.001);

        assertEquals(89.0f, someMatrix.get(5), 0.001);
        assertEquals(89.0f, someMatrix.get(1, 1), 0.001);

        assertEquals(4.0f, someMatrix.get(15), 0.001);
        assertEquals(4.0f, someMatrix.get(3, 3), 0.001);

        assertEquals(87.0f, someMatrix.get(9), 0.001);
        assertEquals(87.0f, someMatrix.get(2, 1), 0.001);
    }

    @Test
    public void testPlus() {
        Mat4 left = new Mat4(new float[]{
                9, 14, 102, 2,
                66, 7, 1, -5,
                0, -8, 76, 8,
                9, 90, 3, -99
        });

        Mat4 right = new Mat4(new float[]{
                53, 34, 102, 42,
                -1, 643, -999, -5,
                64, -77, 76, 8,
                9, 90, 3, -99
        });

        Mat4 resultExp = new Mat4(new float[]{
                62, 48, 204, 44,
                65, 650, -998, -10,
                64, -85, 152, 16,
                18, 180, 6, -198
        });

        assertEquals(resultExp, left.plus(right));
    }

    @Test
    public void testMinus() {
        Mat4 left = new Mat4(new float[]{
                0, 888, 102, 2,
                66, 57, 1, -5,
                7, -8, 745, 0,
                -34, 90, 3, 0
        });

        Mat4 right = new Mat4(new float[]{
                53, -347, 124, 8,
                -1, 23, 969, -67,
                0, -77, 76, 8,
                0, -90, 532, -99
        });

        Mat4 resultExp = new Mat4(new float[]{
                -53, 1235, -22, -6,
                67, 34, -968, 62,
                7, 69, 669, -8,
                -34, 180, -529, 99
        });

        assertEquals(resultExp, left.minus(right));
    }

    @Test
    public void testMultiplication() {
        Mat4 a = new Mat4(new float[]{
                74, 8, 12, 2,
                66, -57, 1, -5,
                -7, -8, 745, 86,
                -4, 50, 3, 69
        });

        Mat4 b = new Mat4(new float[]{
                0, -347, 124, 8,
                -1, 3, 969, -67,
                0, -9, 0, 8,
                0, -90, 532, -9
        });

        Mat4 resultExpAB = new Mat4(new float[]{
                -23802, 19187, 92057, 12951,
                -6391, -11281, 721695, 78694,
                -626, 913, 15, 597,
                -9628, 424, 396223, 45581
        });

        Mat4 resultExpBA = new Mat4(new float[]{
                -8, -25942, 17992, 134,
                57, -22632, -49709, 4400,
                8, -12040, 37132, 5666,
                -50, -4699, 84662, -3979
        });

        assertEquals(resultExpBA, b.multiplication(a));
        assertEquals(resultExpAB, a.multiplication(b));
    }

    @Test
    public void testTranspose() {
        Mat4 original = new Mat4(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        });

        Mat4 transposed = new Mat4(new float[]{
                1, 5, 9, 13,
                2, 6, 10, 14,
                3, 7, 11, 15,
                4, 8, 12, 16
        });

        assertEquals(transposed, original.transpose());
        assertEquals(original, transposed.transpose());
    }
}
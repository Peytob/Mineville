package ru.peytob.mineville.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableMat4Test {
    private ImmutableMat4 immutableMat4;

    @BeforeEach
    void setUp() {
        immutableMat4 = new ImmutableMat4(new float[] {
                4,  3,  2,  1,
                50, 40, 30, 20,
                6,  7,  8,  9,
                12, 13, 14, 15
        });
    }

    @Test
    void constructor() {
        assertThrows(NumberFormatException.class, () -> {
            new ImmutableMat4(new float[] {
                    1, 2, 3, 4
            });
        });

        assertThrows(NumberFormatException.class, () -> {
            new ImmutableMat4(new float[] {
                    1,  2,  3,  4,
                    5,  6,  7,  8,
                    9,  10, 11, 12,
                    13, 14, 15, 16,
                    17, 18, 19, 20
            });
        });

        assertDoesNotThrow(() -> {
            new ImmutableMat4(new float[] {
                    1,  2,  3,  4,
                    5,  6,  7,  8,
                    9,  10, 11, 12,
                    13, 14, 15, 16
            });
        });
    }

    @Test
    void equals() {
        ImmutableMat4 first = new ImmutableMat4(new float[] {
                1.5f,  2,  3,  4,
                5,  6,  7,  8,
                9,  10, 11.1f, 12,
                13, 14, 15, 16
        });

        ImmutableMat4 second = new ImmutableMat4(new float[] {
                1.5f,  2,  3,  4,
                5,  6,  7,  8,
                9,  10, 11.1f, 12,
                13, 14, 15, 16
        });

        ImmutableMat4 third = new ImmutableMat4(new float[] {
                1,  2,  3,  4,
                5,  6,  7.5f,  8,
                9,  10, 11, 12,
                13.2f, 14, 15, 16
        });

        assertEquals(first, second);
        assertNotEquals(first, third);
        assertEquals(third, third);
    }

    @Test
    void copy() {
        ImmutableMat4 copy = new ImmutableMat4(immutableMat4);
        assertEquals(copy, immutableMat4);
    }

    @Test
    void get() {
        assertEquals(4.0f, immutableMat4.get(0));
        assertEquals(4.0f, immutableMat4.get(0, 0));

        assertEquals(14.0f, immutableMat4.get(14));
        assertEquals(14.0f, immutableMat4.get(3, 2));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            immutableMat4.get(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            immutableMat4.get(16);
        });
    }
}
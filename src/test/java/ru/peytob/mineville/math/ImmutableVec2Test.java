package ru.peytob.mineville.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableVec2Test {
    private ImmutableVec2 immutableVec2;

    @BeforeEach
    void setUp() {
        immutableVec2 = new ImmutableVec2(5.4f, 1);
    }

    @Test
    void constructor() {
        ImmutableVec2 copy = new ImmutableVec2(immutableVec2);
        assertEquals(copy, immutableVec2);
    }

    @Test
    void length() {
        assertEquals(5.4918f, immutableVec2.length(), 0.001f);
        assertEquals(0.0f, new ImmutableVec2().length(), 0.001f);
        assertEquals(5.0f, new ImmutableVec2(3.0f, 4.0f).length(), 0.001f);
    }

    @Test
    void testEquals() {
        ImmutableVec2 first = new ImmutableVec2(1.2f, 99.5f);
        ImmutableVec2 second = new ImmutableVec2(44.4f, 0.0f);
        ImmutableVec2 third = new ImmutableVec2(1.2f, 99.5f);

        assertEquals(first, third);
        assertNotEquals(first, second);
    }

    @Test
    void get() {
        assertEquals(5.4f, immutableVec2.getX());
        assertEquals(1.0f, immutableVec2.getY());
    }
}

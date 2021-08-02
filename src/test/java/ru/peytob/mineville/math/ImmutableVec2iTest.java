package ru.peytob.mineville.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableVec2iTest {
    private ImmutableVec2i immutableVec2i;

    @BeforeEach
    void setUp() {
        immutableVec2i = new ImmutableVec2i(5, 1);
    }

    @Test
    void constructor() {
        ImmutableVec2i copy = new ImmutableVec2i(immutableVec2i);
        assertEquals(copy, immutableVec2i);
    }

    @Test
    void length() {
        assertEquals(5.0990f, immutableVec2i.length(), 0.001f);
        assertEquals(0.0f, new ImmutableVec2i().length(), 0.001f);
        assertEquals(5.0f, new ImmutableVec2i(3, 4).length(), 0.001f);
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
        assertEquals(5, immutableVec2i.getX());
        assertEquals(1, immutableVec2i.getY());
    }
}
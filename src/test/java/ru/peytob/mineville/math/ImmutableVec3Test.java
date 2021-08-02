package ru.peytob.mineville.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableVec3Test {
    private ImmutableVec3 immutableVec3;

    @BeforeEach
    void setUp() {
        immutableVec3 = new ImmutableVec3(5.4f, 1, 0.25f);
    }

    @Test
    void constructor() {
        ImmutableVec3 copy = new ImmutableVec3(immutableVec3);
        assertEquals(copy, immutableVec3);
    }

    @Test
    void length() {
        assertEquals(5.4974f, immutableVec3.length(), 0.001f);
        assertEquals(0.0f, new ImmutableVec3().length(), 0.001f);
        assertEquals(12.1954f, new ImmutableVec3(3.0f, 10.3f, 5.8f).length(), 0.001f);
    }

    @Test
    void testEquals() {
        ImmutableVec3 first = new ImmutableVec3(1.2f, 99.5f, 5.0f);
        ImmutableVec3 second = new ImmutableVec3(44.4f, 0.0f, 0.0f);
        ImmutableVec3 third = new ImmutableVec3(1.2f, 99.5f, 5.0f);

        assertEquals(first, third);
        assertNotEquals(first, second);
    }

    @Test
    void get() {
        assertEquals(5.4f, immutableVec3.getX());
        assertEquals(1.0f, immutableVec3.getY());
        assertEquals(0.25f, immutableVec3.getZ());
    }
}
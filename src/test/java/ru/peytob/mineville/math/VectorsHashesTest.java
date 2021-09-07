package ru.peytob.mineville.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VectorsHashesTest {

    @Test
    public void vec2iHashes() {
        ImmutableVec2i firstIm = new ImmutableVec2i(5, 2);
        Vec2i firstMut = new Vec2i(5, 2);

        ImmutableVec2i secondIm = new ImmutableVec2i(1, -22);
        Vec2i secondMut = new Vec2i(1, -22);

        assertEquals(firstIm.hashCode(), firstMut.hashCode());
        assertNotEquals(firstIm.hashCode(), secondIm.hashCode());
        assertNotEquals(firstIm.hashCode(), secondMut.hashCode());

        assertNotEquals(firstMut.hashCode(), secondIm.hashCode());
        assertNotEquals(firstMut.hashCode(), secondMut.hashCode());

        assertEquals(secondIm.hashCode(), secondMut.hashCode());
    }
}

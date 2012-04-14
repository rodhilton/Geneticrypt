package com.nomachetejuggling.geneticrypt.genes.exact

import org.junit.Test
import static junit.framework.Assert.assertEquals

class ExactSequenceTest {

    @Test
    void shouldDammit() {
        ExactSequence seq = new ExactSequence("one","lie");
        assertEquals(seq.score(), 1.0, 0.0001);
    }
}

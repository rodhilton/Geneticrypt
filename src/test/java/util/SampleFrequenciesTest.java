package util;

import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class SampleFrequenciesTest {

    @Test
    public void shouldHaveAllMonograms() {
        Map<String, Double> monograms = SampleFrequencies.getMonograms();
        assertEquals(26, monograms.size());
    }

    @Test
    public void shouldHaveAFreakingLotOfTrigrams() {

    }
}
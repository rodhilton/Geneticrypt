package com.geneticrypt.util;

import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class SampleFrequenciesTest {

    @Test
    public void shouldHaveAllMonograms() {
        Map<String, Double> monograms = SampleFrequencies.getMonograms();
        assertEquals(26, monograms.size());
    }

    @Test
    public void shouldHaveALotOfBigrams() {
       Map<String, Double> bigrams = SampleFrequencies.getBigrams();
       assertThat(bigrams.size(), is(greaterThan(500)));
    }

    @Test
    public void shouldHaveAFreakingTonOfTrigrams() {
       Map<String, Double> trigrams = SampleFrequencies.getTrigrams();
       assertThat(trigrams.size(), is(greaterThan(4000)));
    }
}
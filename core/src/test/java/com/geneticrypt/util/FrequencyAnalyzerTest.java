package com.geneticrypt.util;

import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class FrequencyAnalyzerTest {

    @Test
    public void shouldCountMonograms() {
        String testMessage = "this is a test";
        
        FrequencyAnalyzer analyzer = new FrequencyAnalyzer(testMessage);
        Map<String, Double> monograms = analyzer.getMonograms();
        assertEquals(3.0/11, monograms.get("t"), .000001);
        assertEquals(3.0/11, monograms.get("s"), .000001);
        assertEquals(2.0/11, monograms.get("i"), .000001);
        assertEquals(1.0/11, monograms.get("a"), .000001);
        assertEquals(0.0, monograms.get("x"), .000001);
    }

    @Test
    public void shouldCountBigrams() {
        String testMessage = "this is a test";

        FrequencyAnalyzer analyzer = new FrequencyAnalyzer(testMessage);
        Map<String, Double> bigrams = analyzer.getBigrams();
        assertEquals(2.0/7, bigrams.get("is"), .000001);
        assertEquals(1.0/7, bigrams.get("th"), .000001);
        assertEquals(1.0/7, bigrams.get("es"), .000001);
        assertEquals(0.0/7, bigrams.get("a "), .000001);
        assertEquals(0.0/7, bigrams.get("qp"), .000001);
    }

    @Test
    public void shouldCountTrigrams() {
        String testMessage = "this is a thick test";

        FrequencyAnalyzer analyzer = new FrequencyAnalyzer(testMessage);
        Map<String, Double> trigrams = analyzer.getTrigrams();
        assertEquals(2.0/7, trigrams.get("thi"), .000001);
        assertEquals(1.0/7, trigrams.get("his"), .000001);
        assertEquals(1.0/7, trigrams.get("tes"), .000001);
        assertEquals(0.0/7, trigrams.get(" te"), .000001);
        assertEquals(0.0/7, trigrams.get("hrp"), .000001);
    }
}

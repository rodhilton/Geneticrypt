package com.nomachetejuggling.geneticrypt.util;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.collect.Lists.newArrayList;

public class SampleFrequencies {

    public static Map<String, Double> getMonograms() {
        return SampleFrequencyHolder.getInstance().getMonograms();
    }

    public static Map<String, Double> getBigrams() {
        return SampleFrequencyHolder.getInstance().getBigrams();
    }

    public static Map<String, Double> getTrigrams() {
        return SampleFrequencyHolder.getInstance().getTrigrams();
    }

    private static class SampleFrequencyHolder {
        private static final SampleFrequencyHolder INSTANCE = new SampleFrequencyHolder();
        private FrequencyAnalyzer frequencyAnalyzer;

        public static synchronized SampleFrequencyHolder getInstance() {
            return INSTANCE;
        }

        private SampleFrequencyHolder() {
            frequencyAnalyzer = new FrequencyAnalyzer(SampleText.getText());
        }

        public Map<String, Double> getMonograms() {
            return frequencyAnalyzer.getMonograms();
        }

        public Map<String, Double> getBigrams() {
            return frequencyAnalyzer.getBigrams();
        }

        public Map<String, Double> getTrigrams() {
            return frequencyAnalyzer.getTrigrams();
        }
    }
}

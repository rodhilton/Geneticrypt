package util;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public class FrequencyAnalyzer {
    private Map<String, Double> monograms;
    private Map<String, Double> bigrams;
    private Map<String, Double> trigrams;

    private String[] texts;

    private static final Pattern MONOGRAM_PATTERN = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
    private static final Pattern BIGRAM_PATTERN = Pattern.compile("[a-z]{2}", Pattern.CASE_INSENSITIVE);
    private static final Pattern TRIGRAM_PATTERN = Pattern.compile("[a-z]{3}", Pattern.CASE_INSENSITIVE);

    public FrequencyAnalyzer(String... texts) {
        this.texts = texts;
    }

    public Map<String, Double> getMonograms() {
        ensureCalculationsDone();
        return monograms;
    }

    public Map<String, Double> getBigrams() {
        ensureCalculationsDone();
        return bigrams;
    }

    public Map<String, Double> getTrigrams() {
        ensureCalculationsDone();
        return trigrams;
    }

    private synchronized void ensureCalculationsDone() {
        if (monograms == null || bigrams == null || trigrams == null) {
            calculateFrequencies();
        }
    }

    private void calculateFrequencies() {
        final Map<String, Integer> monogramCounts = new DefaultingMap<String, Integer>(0);
        final Map<String, Integer> bigramCounts = new DefaultingMap<String, Integer>(0);
        final Map<String, Integer> trigramCounts = new DefaultingMap<String, Integer>(0);

        for (String text : texts) {
            for (int i = 0; i < text.length(); i++) {
                {
                    String monoSub = substring(text, i, i + 1);
                    if (MONOGRAM_PATTERN.matcher(monoSub).matches()) {
                        monogramCounts.put(monoSub, monogramCounts.get(monoSub) + 1);
                    }
                }

                {
                    String biSub = substring(text, i, i + 2);
                    if (BIGRAM_PATTERN.matcher(biSub).matches()) {
                        bigramCounts.put(biSub, bigramCounts.get(biSub) + 1);
                    }
                }

                {
                    String triSub = substring(text, i, i + 3);
                    if (TRIGRAM_PATTERN.matcher(triSub).matches()) {
                        trigramCounts.put(triSub, trigramCounts.get(triSub) + 1);
                    }
                }
            }
        }

        monograms = tabulate(monogramCounts);
        bigrams = tabulate(bigramCounts);
        trigrams = tabulate(trigramCounts);
    }

    private Map<String, Double> tabulate(Map<String, Integer> ngramCounts) {

        final Double count = getTotalCount(ngramCounts.values());

        return new DefaultingMap<String, Double>(0.0, Maps.transformValues(ngramCounts, new Function<Integer, Double>() {
            @Override
            public Double apply(Integer input) {
                return input / count;
            }
        }));
    }

    private String substring(String string, int start, int end) {
        return string.substring(start, Math.min(end, string.length()));
    }

    private Double getTotalCount(Collection<Integer> counts) {
        double sum = 0;
        for (Integer count : counts) {
            sum += count;
        }
        return sum;
    }
}

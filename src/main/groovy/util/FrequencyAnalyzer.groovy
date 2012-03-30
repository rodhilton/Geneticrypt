package util

public class FrequencyAnalyzer {
    private Map<String, Double> monograms;
    private Map<String, Double> bigrams;
    private Map<String, Double> trigrams;

    private String[] texts;

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

        texts.each { text ->
            for (int i = 0; i < text.length(); i++) {
                String monoSub = safeSubstring(text, i, 1).toLowerCase();
                if(monoSub ==~ /[a-z]{1}/) {
                    monogramCounts[monoSub] += 1
                }

                String biSub = safeSubstring(text, i, 2).toLowerCase();
                if(biSub ==~ /[a-z]{2}/) {
                    bigramCounts[biSub] += 1
                }

                String triSub = safeSubstring(text, i, 3).toLowerCase();
                if(triSub ==~ /[a-z]{3}/) {
                    trigramCounts[triSub] += 1
                }
            }
        }

        monograms = tabulate(monogramCounts);
        bigrams = tabulate(bigramCounts);
        trigrams = tabulate(trigramCounts);
    }

    private String safeSubstring(String text, int i, int i1) {
        return text.substring(i, Math.min(i + i1, text.length()))
    }

    private Map<String, Double> tabulate(Map<String, Integer> ngramCounts) {

        final Double total = ngramCounts.values().inject(0) { sum, item -> sum + item}

        Map<String, Double> transformedMap = ngramCounts.inject([:]) { newMap, entry -> newMap << [(entry.key): entry.value / total]}

        return new DefaultingMap<String, Double>(0D, transformedMap);
    }

}
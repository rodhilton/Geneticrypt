package com.nomachetejuggling.geneticrypt.util

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
            //Remove punctuation and lower-case the string
            text = text.toLowerCase().replaceAll(/[^A-Za-z]/,"")

            for (int i = 0; i < text.length(); i++) {
                String monoSub = safeSubstring(text, i, 1)
                if (monoSub ==~ /[a-z]{1}/) {
                    monogramCounts[monoSub] += 1
                }

                String biSub = safeSubstring(text, i, 2)
                if (biSub ==~ /[a-z]{2}/) {
                    bigramCounts[biSub] += 1
                }

                String triSub = safeSubstring(text, i, 3)
                if (triSub ==~ /[a-z]{3}/) {
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

    /**
     * Takes a map of ngram counts and transforms it into a map of counts based on percentage.
     *
     * Example: input is ["a": 3, "b": 2].  That means there are 5 total occurences of the ngrams, and a is 3 of those,
     * which is 3/5 or 0.6, and b is 2 of them which is 2/5 or 0.4.  So the output is ["a": 0.6, "b": 0.4]
     *
     * @param ngramCounts
     * @return tabulated map
     */
    private Map<String, Double> tabulate(Map<String, Integer> ngramCounts) {

        final Double total = ngramCounts.values().inject(0) { sum, item -> sum + item}

        Map<String, Double> transformedMap = ngramCounts.inject([:]) { newMap, entry -> newMap << [(entry.key): entry.value / total]}

        return new DefaultingMap<String, Double>(0D, transformedMap);
    }

}
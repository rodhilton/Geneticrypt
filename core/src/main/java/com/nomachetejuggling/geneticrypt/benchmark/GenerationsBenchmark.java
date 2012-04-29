package com.nomachetejuggling.geneticrypt.benchmark;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.collect.Ordering;
import com.nomachetejuggling.geneticrypt.ciphers.MonoSubstitutionCipher;
import com.nomachetejuggling.geneticrypt.genes.crypt.CryptSequence;
import com.nomachetejuggling.geneticrypt.simulators.genetic.EvolutionarySimulator;
import com.nomachetejuggling.geneticrypt.simulators.genetic.ThreadedEvolutionarySimulator;
import com.nomachetejuggling.geneticrypt.util.MutableString;
import com.nomachetejuggling.geneticrypt.util.UpdateCallback;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.nomachetejuggling.geneticrypt.util.Util.shuffle;

public class GenerationsBenchmark {
    private static Random random = new SecureRandom();
    private static final Integer[] GENERATION_SIZES = new Integer[]{10, 20, 30, 40, 50, 75, 100, 125, 150, 200,250,300,400};
    private static final int TRIALS = 5;
    private static final int CIPHER_LENGTH = 1000;
    private static final int MAX_GENERATIONS = 400;

    public static void main(String[] args) {
        BenchmarkText benchmarkText = new BenchmarkText(random);

        final Map<Integer, DescriptiveStatistics> stats = new HashMap<Integer, DescriptiveStatistics>();

        for (int trialNum = 0; trialNum < TRIALS; trialNum++) {
            System.out.print("\n#Trial " + trialNum);
            String text = benchmarkText.getText(CIPHER_LENGTH);
            final String key = shuffle("ABCDEFGHIJKLMNOPQRSTUVWXYZ", random);
            final String cipherText = new MonoSubstitutionCipher(key).encrypt(text);

            final EvolutionarySimulator<CryptSequence> evolutionarySimulator = new ThreadedEvolutionarySimulator<CryptSequence>(100);

            final AtomicInteger generationCount = new AtomicInteger();
            final MutableString lastKey = new MutableString();

            evolutionarySimulator.registerUpdates(new UpdateCallback<CryptSequence>() {
                @Override
                public void call(Collection<CryptSequence> selected) {
                    CryptSequence object = Collections.max(selected, Ordering.natural().onResultOf(new Function<CryptSequence, Double>() {

                        @Override
                        public Double apply(CryptSequence input) {
                            return input.score();
                        }
                    }));


                    lastKey.set(object.getKey());
                    int count = generationCount.incrementAndGet();

                    if (!stats.containsKey(count)) {
                        stats.put(count, new DescriptiveStatistics());
                    }

                    stats.get(count).addValue(lettersRight(lastKey, key));

                    if(count % 10 == 0) {
                        System.out.print(".");
                    }

                    if (count > MAX_GENERATIONS) {
                        evolutionarySimulator.requestStop();
                    }
                }
            });

            evolutionarySimulator.simulate(new Supplier<CryptSequence>() {
                @Override
                public CryptSequence get() {
                    return new CryptSequence(cipherText, random);
                }
            });
        }

        System.out.println("\n#Gen,Median,Avg,Max,Std.Dev");
        for(int size: GENERATION_SIZES) {
            DescriptiveStatistics genStats =stats.get(size);
            System.out.format("%s,%s,%s,%s,%s\n", size, genStats.getPercentile(50), genStats.getMean(), genStats.getMax(),genStats.getStandardDeviation());
        }

    }
    //the range should really be the standard deviation
    //It should also include the max, if possible, to be graphed separately

    //System.out.format("%s,%s,%s,%s\n", maxGenerations,stats.getPercentile(50),stats.getMean(), stats.getStandardDeviation());
//Line break to separate each of the chunks of population sizes
//System.out.println("");

    static int lettersRight(CharSequence s1, CharSequence s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                count++;
            }
        }
        return count;
    }

}
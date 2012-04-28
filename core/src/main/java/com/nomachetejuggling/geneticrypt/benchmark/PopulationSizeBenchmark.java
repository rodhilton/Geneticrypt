package com.nomachetejuggling.geneticrypt.benchmark;

import com.google.common.base.Supplier;
import com.nomachetejuggling.geneticrypt.ciphers.MonoSubstitutionCipher;
import com.nomachetejuggling.geneticrypt.genes.crypt.CryptSequence;
import com.nomachetejuggling.geneticrypt.simulators.genetic.GeneticSimulator;
import com.nomachetejuggling.geneticrypt.simulators.genetic.ThreadedGeneticSimulator;
import com.nomachetejuggling.geneticrypt.util.MutableString;
import com.nomachetejuggling.geneticrypt.util.UpdateCallback;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.nomachetejuggling.geneticrypt.util.Util.shuffle;

public class PopulationSizeBenchmark {
    private static Random random = new SecureRandom();
    private static final int[] POPULATION_SIZES = new int[]{10, 20, 50, 75, 100, 200, 300,400,500};
    private static final int MAX_GENERATIONS = 100;
    private static final int TRIALS = 5;
    private static final int CIPHER_LENGTH=500;

    public static void main(String[] args) {
        BenchmarkText benchmarkText = new BenchmarkText(random);

        System.out.println("#Pop,Avg,Max,Std.Dev");

        for (int populationSize : POPULATION_SIZES) {
            //List<Integer> trialResults = new ArrayList<Integer>();
            DescriptiveStatistics stats = new DescriptiveStatistics();

            for (int trialNum = 0; trialNum < TRIALS; trialNum++) {

                String text = benchmarkText.getText(CIPHER_LENGTH);
                String key = shuffle("ABCDEFGHIJKLMNOPQRSTUVWXYZ", random);
                final String cipherText = new MonoSubstitutionCipher(key).encrypt(text);

                final GeneticSimulator<CryptSequence> geneticSimulator = new ThreadedGeneticSimulator<CryptSequence>(populationSize);

                final AtomicInteger generationCount = new AtomicInteger();
                final MutableString lastKey = new MutableString();

                geneticSimulator.registerUpdates(new UpdateCallback<CryptSequence>() {
                    @Override
                    public void call(CryptSequence object) {
                        lastKey.set(object.getKey());
                        int count = generationCount.incrementAndGet();

                        if (count > MAX_GENERATIONS) {
                            geneticSimulator.requestStop();
                        }
                    }
                });

                geneticSimulator.simulate(new Supplier<CryptSequence>() {
                    @Override
                    public CryptSequence get() {
                        return new CryptSequence(cipherText, random);
                    }
                });

                stats.addValue(lettersRight(lastKey, key));
            }
            //the range should really be the standard deviation
            //It should also include the max, if possible, to be graphed separately

            System.out.format("%s,%s,%s,%s\n",populationSize,stats.getMean(), stats.getMax(), stats.getStandardDeviation());
        }
    }

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
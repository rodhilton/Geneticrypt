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

public class CipherTextLengthBenchmark {
    private static Random random = new SecureRandom();
    private static final int[] CHUNK_SIZES = new int[]{100, 200, 300, 400, 500, 750, 1000, 1500, 2000};
    private static final int MAX_GENERATIONS = 100;
    private static final int TRIALS = 10;

    public static void main(String[] args) {
        BenchmarkText benchmarkText = new BenchmarkText(random);

        for (int chunkSize : CHUNK_SIZES) {
            //List<Integer> trialResults = new ArrayList<Integer>();
            DescriptiveStatistics stats = new DescriptiveStatistics();

            for (int trialNum = 0; trialNum < TRIALS; trialNum++) {

                String text = benchmarkText.getText(chunkSize);
                String key = shuffle("ABCDEFGHIJKLMNOPQRSTUVWXYZ", random);
                final String cipherText = new MonoSubstitutionCipher(key).encrypt(text);

                final GeneticSimulator<CryptSequence> geneticSimulator = new ThreadedGeneticSimulator<CryptSequence>(75);

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

            System.out.println(chunkSize + "," + stats.getMean() + "," + stats.getStandardDeviation());
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
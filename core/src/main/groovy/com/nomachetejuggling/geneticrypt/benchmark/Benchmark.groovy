package com.nomachetejuggling.geneticrypt.benchmark

import com.nomachetejuggling.geneticrypt.util.Dictionary
import com.nomachetejuggling.geneticrypt.ciphers.MonoSubstitutionCipher

import static com.nomachetejuggling.geneticrypt.util.Util.shuffle
import com.nomachetejuggling.geneticrypt.genes.crypt.CryptSequence
import com.nomachetejuggling.geneticrypt.simulators.genetic.GeneticSimulator
import com.nomachetejuggling.geneticrypt.simulators.genetic.ThreadedGeneticSimulator
import java.util.concurrent.atomic.AtomicInteger
import com.nomachetejuggling.geneticrypt.util.UpdateCallback
import com.nomachetejuggling.geneticrypt.util.EndCondition
import com.google.common.base.Supplier

import static com.nomachetejuggling.geneticrypt.util.Util.similarity

class Benchmark {
    private static random = new Random()



    public static void main(String[] args) {
        def benchmarkText = new BenchmarkText(random)


        def wordChunks = [25, 50, 100, 250, 500]

        wordChunks.each {chunkSize ->
            def text = benchmarkText.getText(chunkSize)
            def key = shuffle("ABCDEFGHIJKLMNOPQRSTUVWXYZ", random)
            def cipherText = new MonoSubstitutionCipher(key).encrypt(text)

            GeneticSimulator<CryptSequence> geneticSimulator = new ThreadedGeneticSimulator<CryptSequence>();

            final AtomicInteger generationCount = new AtomicInteger();

            geneticSimulator.registerUpdates(new Update(generationCount, text, cipherText, geneticSimulator));

            geneticSimulator.simulate(new CryptSupplier(cipherText, random));

            println("\n" + chunkSize + ": " + generationCount.get())


        }
    }


}

class CryptSupplier implements Supplier<CryptSequence> {

    String cipherText
    Random random

    CryptSupplier(String cipherText, Random random) {
        this.cipherText = cipherText
        this.random = random
    }

    @Override
    CryptSequence get() {
        return new CryptSequence(cipherText, random);
    }
}

class Update implements UpdateCallback<CryptSequence> {

    AtomicInteger generationCount
    String plainText
    GeneticSimulator geneticSimulator
    String cipherText

    Update(AtomicInteger generationCount, String text, String cipherText, GeneticSimulator geneticSimulator) {
        this.generationCount = generationCount
        this.plainText = text
        this.cipherText = cipherText
        this.geneticSimulator = geneticSimulator
    }

    @Override
    void call(CryptSequence object) {
        int count = generationCount.incrementAndGet();
        if (count % 100 == 0) print(".")
        //println(object)

        String potentialPlaintext = new MonoSubstitutionCipher(object.getKey()).decrypt(cipherText);
        if (count > 10000 || similarity(potentialPlaintext, plainText) > 0.98D) {
            geneticSimulator.requestStop();
        }
    }
}


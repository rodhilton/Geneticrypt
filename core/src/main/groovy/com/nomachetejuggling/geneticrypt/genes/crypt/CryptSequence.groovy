package com.nomachetejuggling.geneticrypt.genes.crypt

import com.nomachetejuggling.geneticrypt.ciphers.MonoSubstitutionCipher
import com.nomachetejuggling.geneticrypt.genes.Candidate
import com.nomachetejuggling.geneticrypt.util.FrequencyAnalyzer
import com.nomachetejuggling.geneticrypt.util.SampleFrequencies
import org.apache.commons.lang.StringUtils
import static com.nomachetejuggling.geneticrypt.util.Util.shuffle

class CryptSequence implements Candidate {

    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String key
    private String cipherText
    private Double cachedScore = null;
    private Random random
//    private Double kickProbability = 0.5D

    CryptSequence(String cipherText, Random random) {
        this(shuffle(ALPHABET, random), cipherText, random)
    }

    private CryptSequence(String newKey, String cipherText, Random random) {
        this.key = newKey
        this.cipherText = cipherText
        this.random = random
    }

    String toString() {
        def decrypt = new MonoSubstitutionCipher(key).decrypt(cipherText)
        "${StringUtils.abbreviate(decrypt, 90)} (${key}) = ${score()}"
    }

    @Override
    CryptSequence mutate() {
//            if(random.nextDouble() < kickProbability) {
//                randomSequence()
//            } else {
                mutatedSequence()
            //}
    }

    private CryptSequence mutatedSequence() {
        int index1 = random.nextInt(key.length())
        int index2 = random.nextInt(key.length())

        StringBuilder sb = new StringBuilder()
        sb.append(key)
        char temp = key.charAt(index1)
        sb.setCharAt(index1, key.charAt(index2))
        sb.setCharAt(index2, temp)

        new CryptSequence(sb.toString(), cipherText, random)
    }

    private CryptSequence randomSequence() {
        new CryptSequence(cipherText, random)
    }


    @Override
    synchronized double score() {
        if (cachedScore == null) {

            def cipher = new MonoSubstitutionCipher(key)
            def possiblePlainText = cipher.decrypt(cipherText.replaceAll("[^A-Z]",""))

            def analyzer = new FrequencyAnalyzer(possiblePlainText)


            def monogramDifference = diff(SampleFrequencies.getMonograms(), analyzer.getMonograms())
            def bigramDifference = diff(SampleFrequencies.getBigrams(), analyzer.getBigrams())
            def trigramDifference = diff(SampleFrequencies.getTrigrams(), analyzer.getTrigrams())

           //def t=(monogramDifference+bigramDifference)/4
           // cachedScore = Math.pow(t, 8)

            def monoWeight = 0.2D
            def biWeight = 0.3D
            def triWeight = 0.5D

            cachedScore = monoWeight * (1-monogramDifference) + biWeight * (1-bigramDifference) + triWeight * (1-trigramDifference)

//            cachedScore = Math.pow((1D-difference),8)
        }
        cachedScore
    }

    private def diff(Map<String, Double> knownGrams, Map<String, Double> checkGrams) {
        def difference = 0
        checkGrams.each {String key, Double value ->
            def abs = Math.abs(knownGrams.get(key) - value)
            difference += abs
        }
        difference
    }

    public String getKey ( ) {
        return key
    }
}

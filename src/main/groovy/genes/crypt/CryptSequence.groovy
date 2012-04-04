package genes.crypt

import ciphers.MonoSubstitutionCipher
import genes.GeneSequence
import util.FrequencyAnalyzer
import util.SampleFrequencies
import org.apache.commons.lang.StringUtils
import static util.Util.shuffle

class CryptSequence implements GeneSequence {

    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String key
    private String cipherText
    private Double cachedScore = null;
    private Random random

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
        "${StringUtils.abbreviate(decrypt,90)} (${key})"
    }

    @Override
    CryptSequence mutate() {
        //Swaps two elements
        int index1 = random.nextInt(key.length())
        int index2 = random.nextInt(key.length())

        StringBuilder sb = new StringBuilder();
        sb.append(key);

        char temp = key.charAt(index1)
        sb.setCharAt(index1, key.charAt(index2));
        sb.setCharAt(index2, temp);


        CryptSequence newSequence = new CryptSequence(sb.toString(), cipherText, random);
        return newSequence;
    }

    @Override
    synchronized double score() {
        if (cachedScore == null) {

            def cipher = new MonoSubstitutionCipher(key)
            def possiblePlainText = cipher.decrypt(cipherText)

            Map<String, Double> textTrigrams = new FrequencyAnalyzer(possiblePlainText).getTrigrams()
            def englishTrigrams = SampleFrequencies.getTrigrams()

            def difference = 0
            textTrigrams.each {String key, Double value ->
                difference += Math.abs(value - englishTrigrams.get(key))
            }

            cachedScore = -difference;
        }
        cachedScore
    }
}

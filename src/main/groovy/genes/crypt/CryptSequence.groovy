package genes.crypt

import ciphers.MonoSubstitutionCipher
import genes.GeneSequence
import util.FrequencyAnalyzer
import util.SampleFrequencies
import org.apache.commons.lang.StringUtils

class CryptSequence implements GeneSequence {

    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String key
    private String cipherText
    private Double cachedScore = null;

    CryptSequence(String cipherText) {
        this(ALPHABET.toList().sort {new Random().nextInt() }.join(""), cipherText)
    }

    private CryptSequence(String newKey, String cipherText) {
        this.key = newKey
        this.cipherText = cipherText
    }

    String toString() {
        def decrypt = new MonoSubstitutionCipher(key).decrypt(cipherText)
        "${StringUtils.abbreviate(decrypt,70)} (${key}) - ${score()}"
    }

    @Override
    CryptSequence mutate() {
        //Swaps two elements
        Random random = new Random();
        int index1 = random.nextInt(key.length())
        int index2 = random.nextInt(key.length())

        StringBuilder sb = new StringBuilder();
        sb.append(key);

        char temp = key.charAt(index1)
        sb.setCharAt(index1, key.charAt(index2));
        sb.setCharAt(index2, temp);


        CryptSequence newSequence = new CryptSequence(sb.toString(), cipherText);
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

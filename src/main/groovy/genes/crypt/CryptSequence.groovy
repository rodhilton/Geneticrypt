package genes.crypt

import genes.GeneSequence
import ciphers.MonoSubstitutionCipher
import util.FrequencyAnalyzer
import util.SampleFrequencies

class CryptSequence implements GeneSequence {

    private final static String ALPHABET ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String key
    private String cipherText

    CryptSequence(String cipherText) {
        this(ALPHABET.toList().sort {new Random().nextInt() }.join(""), cipherText)
    }

    private CryptSequence(String newKey, String cipherText) {
        this.key = newKey
        this.cipherText = cipherText
    }

    String toString() {
        new MonoSubstitutionCipher(key).decrypt(cipherText)
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
    double score() {
        def cipher = new MonoSubstitutionCipher(key)
        def possiblePlainText = cipher.decrypt(cipherText)

        Map<String, Double> textBigrams = new FrequencyAnalyzer(possiblePlainText).getBigrams()
        def englishBigrams = SampleFrequencies.getBigrams()

        def difference = 0
        textBigrams.each {String key, Double value ->
            difference += Math.abs(value - englishBigrams.get(key))
        }

        -difference
    }
}

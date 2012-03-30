import ciphers.Cipher;
import ciphers.MonoSubstitutionCipher;
import com.google.common.base.Supplier;
import genes.crypt.CryptSequence;
import genes.exact.ExactSequence;
import genes.simple.SimpleSequence;
import simulator.GeneticSimulator;

public class Main {

    public static void main(String[] args) {

        String key = "BALSCKDEFGHIJMNOPQRTUVWXYZ";
        String plainText = "This is a test of the genetic solver.  It is not a particularly long piece of text, but it is somewhat long, which is good.  Yeah that's right, it's a reasonably long plaintext, though it could certainly stand to be quite a bit longer.";

        Cipher cipher = new MonoSubstitutionCipher(key);
        final String cipherText = cipher.encrypt(plainText);

        GeneticSimulator<CryptSequence> geneticSimulator = new GeneticSimulator<CryptSequence>();

        geneticSimulator.simulate(new Supplier<CryptSequence>() {

            @Override
            public CryptSequence get() {
                return new CryptSequence(cipherText);
            }
        });

    }
}

import ciphers.Cipher;
import ciphers.MonoSubstitutionCipher;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import genes.crypt.CryptSequence;
import genes.exact.ExactSequence;
import genes.simple.SimpleSequence;
import org.apache.commons.lang.StringUtils;
import simulator.GeneticSimulator;
import simulator.GeneticSimulatorImpl;
import util.UpdateCallback;

public class Main {

    public static void main(String[] args) {

        String key = "JULIASWEOMBCDFGHKNPQRTVXYZ";
        String plainText = " Suppose the Supreme Court does rule that the health care mandate is unconstitutional? What happens then?\n" +
                "(I'm not saying that they will, but let's play \"what if?\")\n" +
                "The famous individual mandate is just one piece of the new health care law enacted in 2010. Take away the mandate, and here are two principal elements left behind:\n" +
                "-- A huge expansion of the Medicaid program. The majority of those who'd gain health coverage under the new health care law, an estimated 18 million people, would gain it from being enrolled in Medicaid, the health care program for the poor. Even before the new health care law, Medicaid was a huge program, covering one in six Americans. It's on its way to becoming bigger still, whatever happens to the individual mandate.\n" +
                "\n" +
                "David Frum\n" +
                "-- Tough new rules on insurance companies. The new health care law forbids insurers to refuse coverage on the basis of \"pre-existing conditions.\" All applicants must be accepted, and they must be covered at the same price as the other members of the insured group.\n" +
                "Now let's war-game what happens post-mandate.\n" +
                "1. The private insurance market will crash in a spectacular train wreck.\n" +
                "Faced with big new costs and deprived of their expected new revenues from the mandate, insurance companies will have to raise prices. Faced with rising prices, employers will cut back coverage.\n" +
                "The 2010 law imposes new obligations on employers to provide health insurance but also presents employers with an option to escape those obligations by paying a (comparatively) small fine. As insurance costs surge in a post-mandate world, more employers will take advantage of that option. Their employees will join the new market for individual care, the famous health care \"exchanges.\"\n" +
                "Minus the mandate, the policies on offer in the exchanges will be unexpectedly expensive. Minus the mandate, many individuals will choose not to buy. The law offers subsidies to buyers who cannot afford the full cost of the new policies. Minus the mandate, those subsidies will cost much more than expected.";

        Cipher cipher = new MonoSubstitutionCipher(key);
        final String cipherText = cipher.encrypt(plainText);

        GeneticSimulator<CryptSequence> geneticSimulator = new GeneticSimulatorImpl<CryptSequence>();

        geneticSimulator.registerUpdates(new UpdateCallback<CryptSequence>() {
            @Override
            public void call(CryptSequence object) {
                System.out.println(object);
            }
        });

        geneticSimulator.simulate(new Supplier<CryptSequence>() {

            @Override
            public CryptSequence get() {
                return new CryptSequence(cipherText);
            }
        });

    }
}

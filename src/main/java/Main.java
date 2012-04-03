import ciphers.Cipher;
import ciphers.MonoSubstitutionCipher;
import com.google.common.base.Supplier;
import genes.crypt.CryptSequence;
import simulator.GeneticSimulator;
import simulator.ThreadedGeneticSimulator;
import util.UpdateCallback;

public class Main {

    public static void main(String[] args) {

        String key = "JULIASWEOMBCDFGHKNPQRTVXYZ";
        String plainText = "Rick Santorum on Monday showed little hope for winning several contests in April but predicted a big comeback next month, when voters in more conservative states head to the polls.\n" +
                "\n" +
                "“May is rich with delegates and strong states for us,” Santorum said on CNN’s “Piers Morgan Tonight.”\n" +
                "\n" +
                "– Follow the Ticker on Twitter: @PoliticalTicker\n" +
                "\n" +
                "His comments came one day before a trio of contests in the Democrat-heavy areas of Wisconsin, Maryland and the District of Columbia. Also in April, voters will cast their ballots in the traditionally blue states of Connecticut, Delaware, New York and Rhode Island.\n" +
                "\n" +
                "Santorum, who has fared well in the South and Midwest, said he knew “April would be a very tough month,” which is why his campaign has shifted its focus towards May.\n" +
                "\n" +
                "Pointing to the typically red states of Texas, Arkansas, Kentucky, Indiana and West Virginia - all of which hold primaries next month - the former Pennsylvania senator said he liked his chances in those contests and predicted a drastic change in the race come summertime.\n" +
                "\n" +
                "“By the end of May, we expect this race to be very close to even,” Santorum said, referring to a match-up with rival Mitt Romney.\n" +
                "\n" +
                "So far, Santorum lags significantly behind Romney in delegates, according to CNN’s latest estimates. The former Massachusetts governor leads the GOP field with 571 delegates, while Santorum has 264, Newt Gingrich has 137 and Ron Paul has 71.\n" +
                "\n" +
                "April, however, won’t be a total loss for his campaign, Santorum said. The candidate reiterated that he expects to win his home state of Pennsylvania later in the month and thinks he will “run well” in Wisconsin’s primary this week.\n" +
                "\n" +
                "While he has not explicitly called on Gingrich and Paul to drop out of the race, he said their “vote totals are distorting the outcome” and acknowledged their departure would help his White House aspirations.\n" +
                "\n" +
                "“We have an opportunity to rally behind one conservative who can beat Mitt Romney right now, and that’s me,” Santorum said. “Hopefully Speaker Gingrich and others will come to that conclusion.”";

        Cipher cipher = new MonoSubstitutionCipher(key);
        final String cipherText = cipher.encrypt(plainText);

        GeneticSimulator<CryptSequence> geneticSimulator = new ThreadedGeneticSimulator<CryptSequence>();

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

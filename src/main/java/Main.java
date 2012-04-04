import ciphers.Cipher;
import ciphers.MonoSubstitutionCipher;
import com.google.common.base.Supplier;
import genes.crypt.CryptSequence;
import simulator.GeneticSimulator;
import simulator.ThreadedGeneticSimulator;
import util.UpdateCallback;

import java.security.SecureRandom;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        String key = "JULIASWEOMBCDFGHKNPQRTVXYZ";
        String plainText = "Forty-two delegates are up for grabs when Wisconsin holds its Republican presidential primary Tuesday. But for Rick Santorum, much more appears to be at stake.\n" +
                "The Badger State may be the final chance for the former U.S. senator from Pennsylvania to convince Republicans that the race for the GOP nomination isn't over.\n" +
                "Wisconsin joins Maryland and the District of Columbia in holding primaries Tuesday.\n" +
                "While polls suggest that former Massachusetts Gov. Mitt Romney is the overwhelming favorite in Maryland and D.C., Romney holds only a single-digit lead over Santorum in Wisconsin, which could be one of Santorum's last opportunities to slow down Romney's march toward clinching the nomination.\n" +
                " Santorum looking forward to May Rick Santorum talks bowling on the trail Who is Ann Romney? Santorum rep: GOP race 'long way to go'\n" +
                "\"I think that because Wisconsin has been at the political epicenter of this country and represents some of the same arguments that are relevant in every corner of this country, whoever wins this Wisconsin primary is going to have some very serious bragging rights,\" said Republican National Committee Chairman Reince Priebus, a former chairman of the Wisconsin GOP.\n" +
                "Another strategist sees higher stakes than bragging rights.\n" +
                "\"In a race where none of the 'must-wins' have actually turned out to be 'must-wins,' Wisconsin looks to be as close as Republicans have been to functionally wrapping up the nominating contest,\" said GOP strategist Gentry Collins, a former political director for the Republican National Committee and the Republican Governors Association.\n" +
                "Romney has 571 delegates, according to CNN's estimate. That's more than twice the 264 delegates Santorum holds, but only about halfway toward securing the 1,144 delegates needed to clinch the nomination.\n" +
                "\"I've got a ways to go before I've got 1,144 delegates, so I'm not counting the delegates before they hatch. But I'm going to keep working very hard, and hope I get a good, strong sendoff from Wisconsin,\" Romney said Saturday in Fitchburg, Wisconsin.\n" +
                "\"I've got a good boost from the folks in Illinois. And if I can get that boost also from Wisconsin, I think we'll be on a path that'll get me the nomination well before the convention.\"\n" +
                "Since Romney's double-digit victory in Illinois two weeks ago -- followed by a wave of some of the party's major names and elder statesmen endorsing him and urging a quick conclusion to the divisive nomination battle -- the conversation seems to have changed: the front-runner increasingly being called the inevitable nominee.\n" +
                "At an event in Middleton, Wisconsin, on Sunday, Romney said that the nominee \"will probably be me.\"\n" +
                "Romney also has increasingly focused his rhetoric at President Barack Obama to portray the campaign in terms of November's general election rather than the ongoing nominating process.\n" +
                "\"The president is consumed with trying to find someone to blame for an extraordinarily failed presidency,\" Romney said Tuesday on \"Fox and Friends,\" particularly targeting Obama's economic policies. \"This has been the slowest recovery we've ever had in American history.\"\n" +
                "Santorum seems to be downplaying expectations, saying in an interview Monday on \"Piers Morgan Tonight\" that he knew that \"April would be a very tough month.\"\n" +
                "But regardless of the increasingly daunting odds facing him, Santorum doesn't sound like a candidate about to end his White House bid.\n" +
                " Romney sets sights on women voters Santorum hits the lanes The 'redesigned' Newt Gingrich Ron Paul: Too soon to write people off\n" +
                "While Romney is far ahead of Santorum, former House Speaker Newt Gingrich and Rep. Ron Paul of Texas in the hunt for delegates, exit polls indicate that Romney still has a problem locking in the conservative base of the party.\n" +
                "And that continues to fuel Santorum's campaign, in which he continually depicts himself as the lone true conservative going up against the Republican establishment and liberal media bias.\n" +
                "\"The reason I'm here is because what the establishment is trying to shove down the throats of the folks of this country on the Republican ticket isn't being swallowed. And the reason it's not being swallowed is because they want someone who reflects their values, someone that they can trust, someone who's an authentic conservative,\" Santorum told voters Sunday in Milwaukee.\n" +
                "While Romney appears to be more in general-election mode, targeting his firepower at Obama rather than his GOP rivals, he was not leaving anything to chance in Wisconsin.\n" +
                "He campaigned in the state over the three days leading up to Tuesday's primary, and his campaign, combined with an independent pro-Romney super PAC, will outspend Santorum and his super PAC by a nearly 4-1 ratio to run TV ads in Wisconsin.\n" +
                "That led Santorum to ask Sunday in Mishicot, Wisconsin: \"Why is he (Romney) spending $4 million in Wisconsin, if this race is over? If it's over and there's no chance, then why he is bothering even campaigning anymore, if it's over?\"\n" +
                "Santorum has some help on the ground in the Badger State.\n" +
                "\"Santorum's very capable deputy campaign manager, Jill Latham, ran the Republican Party's turnout programs in Wisconsin through the 2006 cycle and knows the state well. She will be a big tactical asset in Wisconsin, and should be able to plus-up Santorum's chances on the ground,\" said Collins, who ran Romney's 2008 operation in Iowa but is not taking sides in this cycle.\n" +
                "A Santorum win in Wisconsin on Tuesday could revive the headlines that Romney is having trouble locking up the nomination. But a Santorum loss will most likely increase the chorus of voices for him to drop out.\n" +
                "\"If Romney clean-sweeps Tuesday night, it's very hard to see a path for Santorum,\" CNN Chief Political Correspondent Candy Crowley said.\n" +
                "After Tuesday, there are three weeks before five more states vote on April 24.\n" +
                "Romney appears to be the favorite in four of those states: New York, Connecticut, Rhode Island and Delaware.\n" +
                "The fifth, Pennsylvania, is Santorum's home state, but polls show his once-large lead there has disappeared.\n" +
                "\"Do you want to lose your home state? I don't think so. I think if Santorum wants any kind of future, how do you stay in and lose Pennsylvania?\" asked Crowley, anchor of CNN's \"State of the Union.\"\n" +
                "Collins agrees, adding, \"If it looks like Romney has the nomination locked up before then, Santorum will need to be careful to avoid a serious embarrassment in his own home state.\"\n" +
                "Santorum and his campaign say there's no talk of dropping out until Romney clinches the nomination. Crowley says any public mention right now of possibly calling it quits would be detrimental to Santorum.\n" +
                "\"The minute you show any leg on getting out, your supporters are going to vote for Romney. So you're kind of stuck,\" Crowley said. \"Your words have to be bravado. I think his words on Wednesday will tell us more about his strategy than his words on Monday.\"\n" +
                "Santorum points toward May, which looks friendlier for him. He could be the favorite in primaries in North Carolina and West Virginia on May 8, Nebraska on May 15, Arkansas and Kentucky on May 22, and Texas a week later.\n" +
                "\"May is rich with delegates and strong states for us. By the end of May, we expect this race to be very close to even,\" Santorum told Morgan.\n" +
                "But it could be too little, too late by May. Sen. Hillary Clinton won most of the final contests against Sen. Barack Obama in the marathon 2008 Democratic presidential nomination battle. But in the end, it didn't matter.\n" +
                "Said Crowley: \"Santorum will win some states, but at the moment it's not about winning states. It's about winning the nomination, and that's where it gets difficult for Santorum.\"";

        Cipher cipher = new MonoSubstitutionCipher(key);
        final String cipherText = cipher.encrypt(plainText);
        final Random random = new SecureRandom();

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
                return new CryptSequence(cipherText, random);
            }
        });

    }
}

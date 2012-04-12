package com.geneticrypt;

import com.geneticrypt.ciphers.Cipher;
import com.geneticrypt.ciphers.MonoSubstitutionCipher;
import com.geneticrypt.simulator.ThreadedKickingGeneticSimulator;
import com.google.common.base.Supplier;
import com.geneticrypt.genes.crypt.CryptSequence;
import com.geneticrypt.simulator.GeneticSimulator;
import com.geneticrypt.simulator.ThreadedGeneticSimulator;
import com.geneticrypt.util.UpdateCallback;

import java.security.SecureRandom;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        String key = "JULIASWEOMBCDFGHKNPQRTVXYZ";
        String plainText = "Wisconsin voters cast their ballots Tuesday in what may be a critical contest for the GOP presidential nomination, but front-runner Mitt Romney sounded as if he already was in a general election campaign against President Barack Obama.\n" +
                "Romney and Obama traded barbs on a day when GOP presidential primaries were being held in Wisconsin -- where polls showed Romney with a single-digit lead over Rick Santorum -- and Maryland and the District of Columbia, where polls had Romney as the overwhelming favorite.\n" +
                "Speaking at a restaurant in Waukesha, Wisconsin, Romney suggested Obama wants to duck responsibility \"for what's happened in this country,\" saying the president should get full credit or blame for \"what's happened in this economy, and what's happened to gasoline prices under his watch.\"\n" +
                "\"It is time to have somebody who will take responsibility, and if I am president, I will not only get things right again, I will take full responsibility for my errors and make sure that people understand we have a president in the White House again where the buck will stop at his desk,\" Romney said.\n" +
                "Later Tuesday, Obama mentioned the former Massachusetts governor by name in a speech for the first time this year, while slamming a House-passed budget proposal that Romney has embraced.\n" +
                " Santorum looking forward to May Rick Santorum talks bowling on the trail Who is Ann Romney? Santorum rep: GOP race 'long way to go'\n" +
                "Obama, speaking at a media luncheon in Washington, said the plan, which would lower tax rates and cut spending while reforming the Medicare and Medicaid government-run health care programs, was \"thinly veiled Social Darwinism\" and \"antithetical to our entire history as a land of opportunity and upward mobility for everyone who's willing to work for it.\"";

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

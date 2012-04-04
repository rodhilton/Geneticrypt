package com.geneticrypt;

import com.geneticrypt.ciphers.Cipher;
import com.geneticrypt.ciphers.MonoSubstitutionCipher;
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
        String plainText = "President Barack Obama launched a major assault Tuesday on the House-passed Republican budget proposal embraced by front-running GOP presidential candidate Mitt Romney, calling it \"social Darwinism\" that would stifle the American dream.\n" +
                "In a speech to a media luncheon, Obama described the measure -- prepared by House Budget Committee Chairman Paul Ryan, R-Wisconsin, and passed by the House -- as a \"Trojan Horse\" that is disguised as a deficit reduction plan but actually imposes a \"radical vision.\"\n" +
                "\"It is thinly-veiled Social Darwinism,\" Obama said. \"It is antithetical to our entire history as a land of opportunity and upward mobility for everyone who's willing to work for it -- a place where prosperity doesn't trickle down from the top, but grows outward from the heart of the middle class.\"\n" +
                "He added that \"by gutting the very things we need to grow an economy that's built to last -- education and training; research and development; infrastructure -- it's a prescription for decline.\"\n" +
                "The remarks signaled Obama's full engagement in his re-election campaign for the November vote as Romney has seized an apparently solid grip on the Republican nomination.\n" +
                "For the first time this year, Obama mentioned the former Massachusetts governor by name in a speech, noting Romney's support for the Ryan budget plan.\n" +
                "\"One of my potential opponents, Gov. Romney, has said that he hoped a similar version of this plan from last year would be introduced on day one of his presidency,\" Obama said. \"He said that he's very supportive of this new budget and he even called it marvelous, which is a word you don't often hear when it comes to describing a budget.\"\n" +
                "Ryan and other Republican leaders immediately criticized the Obama speech as a politically motivated appeal to populism, rather than a serious approach to budget deficits.\n" +
                "\"History will not be kind to a president who, when it came time to confront our generation's defining challenge, chose to duck and run,\" Ryan said in a statement. \"The president refuses to take responsibility for the economy and refuses to offer a credible plan to address the most predictable economic crisis in our history. Instead, he has chosen tired and cynical political attacks as he focuses on his own re-election.\"\n" +
                "According to Ryan, Obama used his speech to \"distort the truth and divide Americans in order to distract from his failed record.\"\n" +
                "Obama, however, blamed a polarized political climate for an inability to make progress on such key issues as deficit reduction and entitlement reform, arguing that Republicans have shifted to the right and dropped support for moderate proposals acceptable to Democrats.\n" +
                "\"The problem right now is not the technical means to solve it. The problem is our politics, and that's part of what this election and what this debate will need to be about,\" Obama said in response to a question at the end. \"Are we, as a country, willing to get back to commonsense, balanced, fair solutions that encourage our long-term economic growth and stabilize our budget?\"";

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

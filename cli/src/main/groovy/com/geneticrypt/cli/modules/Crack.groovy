package com.geneticrypt.cli.modules

import com.geneticrypt.cli.Module
import com.geneticrypt.cli.ModuleName
import org.apache.commons.cli.Options
import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.GnuParser
import org.apache.commons.cli.CommandLine
import com.geneticrypt.ciphers.MonoSubstitutionCipher



import static com.geneticrypt.cli.util.Util.readFromStdin
import org.apache.commons.cli.HelpFormatter
import java.security.SecureRandom
import com.geneticrypt.genes.crypt.CryptSequence
import com.geneticrypt.simulator.GeneticSimulator
import com.geneticrypt.simulator.ThreadedGeneticSimulator
import com.geneticrypt.util.UpdateCallback
import com.google.common.base.Supplier

@ModuleName(Crack.NAME)
class Crack implements Module {
    public static final String NAME = "crack"

    private static Options options = new Options() {
        {
            addOption("f", "file", true, "file to crack")
            addOption("s", "stdin", false, "read crack text from standard in")
            addOption("c", "cipertext", true, "ciphertext to crack")
        }
    }

    @Override
    void invoke(String[] args) {
        CommandLineParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, args);


        if (cmd.hasOption("s")) {
            def ciphertext = readFromStdin()
            crack(ciphertext)
        } else if (cmd.hasOption("c")) {
            def ciphertext = cmd.getOptionValue("c")
            crack(ciphertext)
        } else {
            printHelp()
        }
    }

    private void printHelp() {
        HelpFormatter help = new HelpFormatter()
        help.printHelp(NAME, options, true)
    }

    void crack(final String ciphertext) {
        final Random random = new SecureRandom();

        GeneticSimulator<CryptSequence> geneticSimulator = new ThreadedGeneticSimulator<CryptSequence>();

        geneticSimulator.registerUpdates(new UpdateCallback<CryptSequence>() {
            @Override
            public void call(CryptSequence object) {
                println(object)
            }
        });

        geneticSimulator.simulate(new Supplier<CryptSequence>() {

            @Override
            public CryptSequence get() {
                return new CryptSequence(ciphertext, random);
            }
        });

    }
}

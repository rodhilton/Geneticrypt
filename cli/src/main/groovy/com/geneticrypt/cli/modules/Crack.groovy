package com.geneticrypt.cli.modules

import com.geneticrypt.cli.Module
import com.geneticrypt.cli.ModuleName
import org.apache.commons.cli.Options
import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.GnuParser
import org.apache.commons.cli.CommandLine

import static com.geneticrypt.cli.util.Util.readFromStdin

import java.security.SecureRandom
import com.geneticrypt.genes.crypt.CryptSequence
import com.geneticrypt.simulator.GeneticSimulator
import com.geneticrypt.simulator.ThreadedGeneticSimulator
import com.geneticrypt.util.UpdateCallback
import com.google.common.base.Supplier

@ModuleName("crack")
class Crack extends Module {

    @Override
    public Options getOptions() {
        return new Options() {
            {
                addOption("f", "file", true, "file to crack")
                addOption("s", "stdin", false, "read crack text from standard in")
                addOption("c", "cipertext", true, "ciphertext to crack")
            }
        }
    }

    @Override
    void process(CommandLine cmd) {
        def text;
        if (cmd.hasOption("s")) {
            text = readFromStdin()
            crack(ciphertext)
        } else if (cmd.hasOption("c")) {
            text = cmd.getOptionValue("c")
            crack(ciphertext)
        } else if (cmd.hasOption("f")) {
            text = new File(cmd.getOptionValue("f")).text
        } else {
            printHelp()
            return
        }

        crack(text)
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

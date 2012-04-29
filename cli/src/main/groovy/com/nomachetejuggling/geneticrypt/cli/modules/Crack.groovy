package com.nomachetejuggling.geneticrypt.cli.modules

import com.nomachetejuggling.geneticrypt.cli.Module
import com.nomachetejuggling.geneticrypt.cli.ModuleName
import org.apache.commons.cli.Options

import org.apache.commons.cli.CommandLine

import static com.nomachetejuggling.geneticrypt.cli.util.Util.readFromStdin

import java.security.SecureRandom
import com.nomachetejuggling.geneticrypt.genes.crypt.CryptSequence

import com.nomachetejuggling.geneticrypt.util.UpdateCallback
import com.google.common.base.Supplier

import com.nomachetejuggling.geneticrypt.simulators.genetic.EvolutionarySimulator
import com.nomachetejuggling.geneticrypt.simulators.genetic.ThreadedEvolutionarySimulator

@ModuleName("crack")
class Crack extends Module {

    @Override
    public Options getOptions() {
        return new Options() {
            {
                addOption("f", "file", true, "file to crack")
                addOption("s", "stdin", false, "read crack text from standard in")
                addOption("t", "text", true, "ciphertext to crack")
            }
        }
    }

    @Override
    void process(CommandLine cmd) {
        def text;
        if (cmd.hasOption("s")) {
            text = readFromStdin()
        } else if (cmd.hasOption("t")) {
            text = cmd.getOptionValue("t")
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

        EvolutionarySimulator<CryptSequence> geneticSimulator = new ThreadedEvolutionarySimulator<CryptSequence>(75);

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

package com.nomachetejuggling.geneticrypt.cli.modules

import org.apache.commons.cli.Options

import org.apache.commons.cli.CommandLine

import static java.util.Collections.shuffle
import com.nomachetejuggling.geneticrypt.cli.ModuleName
import com.nomachetejuggling.geneticrypt.cli.Module

@ModuleName("keygen")
class KeyGen extends Module {
    private static final ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    @Override
    public Options getOptions() {
        new Options() {{
            addOption("s", "seed", true, "word or phrase to use as a seed");
        }}
    }

    @Override
    void process(CommandLine cmd) {
        if (cmd.hasOption("s")) {
            def phrase = cmd.getOptionValue("s")
            def keyPart = phrase.toUpperCase().toCharArray().toList().unique()
            def alpha = ALPHABET.toCharArray().toList()
            alpha.removeAll(keyPart)
            def newKey = keyPart + alpha.reverse()
            println(newKey.join(""))
        } else {
            def alpha = ALPHABET.toCharArray().toList()
            shuffle(alpha)
            println alpha.join("")
        }

    }
}

package com.geneticrypt.cli.modules

import org.apache.commons.cli.Options

import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.GnuParser
import org.apache.commons.cli.CommandLine

import static java.util.Collections.shuffle
import com.geneticrypt.cli.ModuleName
import com.geneticrypt.cli.Module

@ModuleName(KeyGen.NAME)
class KeyGen implements Module {

    public static final String NAME = "keygen"

    private static final ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    @Override
    void invoke(String[] args) {
        Options options = new Options();

        options.addOption("s", "seed", true, "word or phrase to use as a seed");

        CommandLineParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, args);

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

package com.geneticrypt.cli.modules

import com.geneticrypt.cli.ModuleName
import com.geneticrypt.cli.Module
import org.apache.commons.cli.Options
import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.GnuParser
import org.apache.commons.cli.CommandLine

import com.geneticrypt.ciphers.MonoSubstitutionCipher

import org.apache.commons.cli.HelpFormatter
import com.geneticrypt.cli.util.Util

@ModuleName(Mono.NAME)
class Mono implements Module{

    public static final String NAME = "mono"

    private static Options options = new Options() {{
        addOption("k", "key", true, "key to use for encryption")
        addOption("f", "file", true, "file to encrypt")
        addOption("s", "stdin", false, "read from standard in")
        addOption("t", "text", true, "text to encrypt/decrypt")
        addOption("d", "decrypt", false, "decrypt text instead of encrypt it")
    }}

    @Override
    void invoke(String[] args) {

        CommandLineParser parser = new GnuParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("k")) {
            def key = cmd.getOptionValue("k")
            def cipher = new MonoSubstitutionCipher(key)

            def text
            if(cmd.hasOption("s")){
                text= Util.readFromStdin()
            } else if(cmd.hasOption("t")) {
                text = cmd.getOptionValue("t")
            } else {
                //file
                text="NOT IMPLEMENTED"
            }

            if(cmd.hasOption("d")) {
                println(cipher.decrypt(text))
            } else {
                println cipher.encrypt(text)
            }

        } else {
            printHelp()
        }
    }

    private void printHelp() {
        HelpFormatter help = new HelpFormatter()
        help.printHelp(NAME, options, true)
    }
}

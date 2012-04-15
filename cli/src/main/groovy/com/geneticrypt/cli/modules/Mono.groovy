package com.geneticrypt.cli.modules

import com.geneticrypt.cli.ModuleName
import com.geneticrypt.cli.Module
import org.apache.commons.cli.Options

import org.apache.commons.cli.CommandLine

import com.geneticrypt.ciphers.MonoSubstitutionCipher

import com.geneticrypt.cli.util.Util

@ModuleName("mono")
class Mono extends Module{

    @Override
    Options getOptions() {
        new Options() {{
            addOption("k", "key", true, "key to use for encryption")
            addOption("f", "file", true, "file to encrypt")
            addOption("s", "stdin", false, "read from standard in")
            addOption("t", "text", true, "text to encrypt/decrypt")
            addOption("d", "decrypt", false, "decrypt text instead of encrypt it")
        }}
    }

    @Override
    void process(CommandLine cmd) {

        if (cmd.hasOption("k")) {
            def key = cmd.getOptionValue("k")
            def cipher = new MonoSubstitutionCipher(key)

            def text
            if(cmd.hasOption("s")){
                text= Util.readFromStdin()
            } else if(cmd.hasOption("t")) {
                text = cmd.getOptionValue("t")
            } else {
                text = new File(cmd.getOptionValue("f")).text
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
}

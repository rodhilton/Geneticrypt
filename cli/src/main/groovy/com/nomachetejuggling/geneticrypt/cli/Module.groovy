package com.nomachetejuggling.geneticrypt.cli

import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Options
import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.GnuParser
import org.apache.commons.cli.ParseException
import org.apache.commons.cli.HelpFormatter

abstract class Module {
    abstract void process(CommandLine cmd);
    abstract Options getOptions();

    public void invoke(String... args) {
        try {
            CommandLineParser parser = new GnuParser()
            CommandLine cmd = parser.parse(gatherOptions(), args)

            if (cmd.hasOption("h")) {
                printHelp()
            } else {
                process(cmd);
            }

        } catch (ParseException e) {
            throw new RuntimeException("Could not parse command line options", e);
        }
    }

    protected Options gatherOptions() {
        Options options = getOptions()
        options.addOption("h", "help", false, "get help")
        options
    }

    protected void printHelp() {
        HelpFormatter help = new HelpFormatter();
        ModuleName name = this.class.getAnnotation(ModuleName)
        help.printHelp("cryptools ${name.value()}", gatherOptions(), true);
    }
}
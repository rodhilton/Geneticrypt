package com.nomachetejuggling.geneticrypt.cli

import com.nomachetejuggling.geneticrypt.cli.modules.KeyGen
import com.nomachetejuggling.geneticrypt.cli.modules.Mono
import com.nomachetejuggling.geneticrypt.cli.modules.Crack

class Main {

    private static def modules = [KeyGen, Mono, Crack]

    public static void main(String[] args) throws Exception {
        if(args.length > 1 && args[0].toLowerCase() == "help") {
            args = args[1..-1] + ["--help"]
        }

        def moduleClass = modules.find {c ->
            ModuleName annotation = c.getAnnotation(ModuleName)
            String name = annotation.value()
            args.length > 0 && name.toLowerCase() == args[0].toLowerCase()
        }

        if(moduleClass) {
            def module = (Module)moduleClass.newInstance()
            if(args.length > 1) {
                module.invoke((String[])args[1..-1].toArray())
            } else {
                module.invoke()
            }
        } else {
            println "Supported Modules:"
            println modules.collect { m ->
                ModuleName annotation = m.getAnnotation(ModuleName)
                annotation.value()
            }.collect{ "  "+it }.join("\n")
        }
    }
}

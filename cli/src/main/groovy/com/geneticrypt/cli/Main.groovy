package com.geneticrypt.cli

import com.geneticrypt.cli.modules.KeyGen
import com.geneticrypt.cli.modules.Mono
import com.geneticrypt.cli.modules.Crack

class Main {

    private static def modules = [KeyGen, Mono, Crack]

    public static void main(String[] args) throws Exception {
        def moduleClass = modules.find {c ->
            ModuleName annotation = c.getAnnotation(ModuleName)
            String name = annotation.value()
            args.length > 1 && name == args[0]
        }

        if(moduleClass) {
            def module = (Module)moduleClass.newInstance()
            module.invoke((String[])args[1..-1].toArray())
        } else {
            println "Supported Modules:"
            println modules.collect { m ->
                ModuleName annotation = m.getAnnotation(ModuleName)
                annotation.value()
            }.join("\n")
        }
    }
}

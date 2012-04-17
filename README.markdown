Purpose
-------

Geneticrypt is a project that performs automatic cryptanalysis against text encrypted with a monoalphabetic substitution cipher.  It relies on Genetic Algorithms to perform this task.

The code is written as a framework for performing any sort of metaheuristic search against any kind of problem, by utilizing interfaces.  This makes it very easy to extend to solve other problems or to solve the same problem via different metaheuristic algorithms.

Design
------

Simulators can implement the Simulator interface or extend the GeneticSimulator class in order to implement other metaheuristics, and alternative problems only need implement the GeneSequence interface to be dropped in.

The code is divided into 3 subprojects: core, cli, and frontend.  Core is the primary library for performing cryptanalysis, while cli and frontend are both user interfaces to the parts of the library, one commandline-based and one GUI-based.

CLI
===

Building
------------

To build the cli project, 'cryptools', which contains a handful of useful utilities to generate keys, encrypt and decrypt siphers, and perform cryptanalysis, you must first build the core module.

In core, run `gradle install`, which will place the jar files for the library in your local maven respository.  Then in the cli project, run `gradle installApp`, which will create a script called `cryptools` in the `build/install/cryptools/bin/` directory.

Usage
-----

Cryptools uses different modules, each with its own set of arguments.  To get a list of modules, run `cryptools help`.  `cryptools help <modulename>` will provide usage details for any given module.

If you want to quickly test the geneticrypt project, put a plain text file in `plain.txt`, then execute `cryptools keygen` to get a new key.  Then run `cryptools mono -k <the key> -f plain.txt > ciphertext.txt` to save the encrypted text to a file.

Last, run `cryptools crack -f ciphertext.txt` and watch as Geneticrypt attempts to find the key.


Frontend
========

Building
--------

As with the cli project, one must first run `gradle install` in the `core` project to get the library into a local maven repository.

Note: `griffon package jar` doesn't seem to work due to an issue with classpaths that I haven't yet resolved, I don't recommend running the app by packaging it.

Usage
-----

In the `frontend` project, run `griffon run-app` and the front-end will launch.  Simply paste encrypted text (read the CLI usage section for a method of getting such text) into the text box and click "Crack!" to see Geneticrypt in action.


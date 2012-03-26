package main

import com.google.common.base.Supplier
import genes.simple.SimpleSequence
import simulator.GeneticSimulator

class Main {

    static void main(String[] args) {

        GeneticSimulator<SimpleSequence> geneticSimulator = new GeneticSimulator<SimpleSequence>();

        geneticSimulator.simulate(new Supplier<SimpleSequence>() {

            @Override
            public SimpleSequence get() {
                return new SimpleSequence();
            }
        });

    }
}

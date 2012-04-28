package com.nomachetejuggling.geneticrypt.simulators.genetic;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import com.nomachetejuggling.geneticrypt.genes.Candidate;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.google.common.collect.Collections2.transform;

public class ThreadedKickingGeneticSimulator<T extends Candidate> extends ThreadedGeneticSimulator<T> {
    private Random random;

    public ThreadedKickingGeneticSimulator(int populationSize) {
        this(populationSize, new Random());
    }

    public ThreadedKickingGeneticSimulator(int populationSize, Random random) {
        super(populationSize);
        this.random = random;
    }

    @Override
    protected List<T> newPopulationFrom(T bestFit) {
        List<T> population = new ArrayList<T>();
        //population.add(bestFit); //Include the previous generation's best fit as well
        for (int i = 0; i < getPopulationSize(); i++) {
            population.add((T) bestFit.mutate());
        }
        return population;
    }
}
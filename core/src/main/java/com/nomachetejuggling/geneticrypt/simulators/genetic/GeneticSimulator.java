package com.nomachetejuggling.geneticrypt.simulators.genetic;

import com.google.common.base.Supplier;
import com.nomachetejuggling.geneticrypt.genes.GeneSequence;
import com.nomachetejuggling.geneticrypt.simulators.Simulator;
import com.nomachetejuggling.geneticrypt.util.EndCondition;
import com.nomachetejuggling.geneticrypt.util.UpdateCallback;

import java.util.ArrayList;
import java.util.List;

public abstract class GeneticSimulator<T extends GeneSequence> implements Simulator<T> {

    private List<UpdateCallback<T>> registered = new ArrayList<UpdateCallback<T>>();
    private boolean stop = false;

    @Override
    public void simulate(Supplier<T> supplier) {
        List<T> population = createInitialPopulation(supplier);

        while (!stop) {
            T bestFit = findBest(population);
            updateRegistered(bestFit);
            population = newPopulationFrom(bestFit);
        }
    }

    private void updateRegistered(T bestFit) {
        for(UpdateCallback<T> callback: registered) {
            callback.call(bestFit);
        }
    }

    @Override
    public void registerUpdates(UpdateCallback<T> callback) {
        registered.add(callback);
    }

    protected abstract List<T> newPopulationFrom(T bestFit);

    protected abstract T findBest(List<T> population);

    protected abstract List<T> createInitialPopulation(Supplier<T> supplier);

    public synchronized void requestStop() {
        stop = true;
    }
}

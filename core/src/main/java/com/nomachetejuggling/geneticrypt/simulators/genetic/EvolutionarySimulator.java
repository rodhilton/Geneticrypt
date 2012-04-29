package com.nomachetejuggling.geneticrypt.simulators.genetic;

import com.google.common.base.Supplier;
import com.nomachetejuggling.geneticrypt.genes.Candidate;
import com.nomachetejuggling.geneticrypt.simulators.Simulator;
import com.nomachetejuggling.geneticrypt.util.UpdateCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class EvolutionarySimulator<T extends Candidate> implements Simulator<T> {

    private List<UpdateCallback<T>> registered = new ArrayList<UpdateCallback<T>>();
    private boolean stop = false;

    @Override
    public void simulate(Supplier<T> supplier) {
        Collection<T> population = createInitialPopulation(supplier);

        while (!stop) {
            Collection<T> selected = select(population);
            updateRegistered(selected);
            population = newPopulationFrom(selected);
        }
    }

    //TODO: this should call callback for all selected, not just one. perhaps include the generation number? perhaps callback with all selected.
    private void updateRegistered(Collection<T> selected) {
        if (selected.size() > 0) {
            for (UpdateCallback<T> callback : registered) {
                callback.call(selected);
            }
        }
    }

    @Override
    public void registerUpdates(UpdateCallback<T> callback) {
        registered.add(callback);
    }

    protected abstract Collection<T> newPopulationFrom(Collection<T> selected);

    protected abstract Collection<T> select(Collection<T> population);

    protected abstract Collection<T> createInitialPopulation(Supplier<T> supplier);

    public synchronized void requestStop() {
        stop = true;
    }
}

package simulator;

import com.google.common.base.Supplier;
import genes.GeneSequence;
import util.ScoreKeeper;
import util.UpdateCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GeneticSimulator<T extends GeneSequence> implements Simulator<T> {

    private List<UpdateCallback<T>> registered = new ArrayList<UpdateCallback<T>>();

    @Override
    public void simulate(Supplier<T> supplier) {
        List<T> population = createInitialPopulation(supplier);

        while (true) {
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

}

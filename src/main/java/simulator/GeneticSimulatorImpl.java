package simulator;

import com.google.common.base.Supplier;
import genes.GeneSequence;

import java.util.ArrayList;
import java.util.List;

public class GeneticSimulatorImpl<T extends GeneSequence> extends GeneticSimulator<T> {
    private static final int MIN_GENERATIONS = 500;
    private static final double HALT_THRESHOLD = 0;
    private static final int POPULATION_SIZE = 20;

    @Override
    protected T findBest(List<T> population) {

        T bestFit = population.get(0);
        double bestFitScore = bestFit.score();

        for (int i = 1; i < population.size(); i++) {
            T sequence = population.get(i);
            double score = sequence.score();

            if (score > bestFitScore) {
                bestFitScore = score;
                bestFit = sequence;
            }
        }

        return bestFit;

    }

    @Override
    protected List<T> newPopulationFrom(T bestFit) {
        List<T> population = new ArrayList<T>();
        //population.add(bestFit); //Include the previous generation's best fit as well
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add((T) bestFit.mutate());
        }
        return population;
    }

    @Override
    protected List<T> createInitialPopulation(Supplier<T> supplier) {
        List<T> population = new ArrayList<T>(POPULATION_SIZE);
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(supplier.get());
        }
        return population;
    }
}

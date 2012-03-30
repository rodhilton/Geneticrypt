package simulator;

import com.google.common.base.Supplier;
import genes.GeneSequence;
import util.ScoreKeeper;

import java.util.ArrayList;
import java.util.List;

public class GeneticSimulator<T extends GeneSequence> {
    private static final int MIN_GENERATIONS = 500;
    private static final double HALT_THRESHOLD = 0;
    private static final int POPULATION_SIZE = 20;

    public void simulate(Supplier<T> supplier) {
        List<T> population = new ArrayList<T>(POPULATION_SIZE);
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(supplier.get());
        }

        ScoreKeeper previousScores = new ScoreKeeper(MIN_GENERATIONS);

        while (true) {
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

            System.out.println(bestFit + " - "+bestFitScore);

            //if the abs val of last 5 average minus this score is < a specified value, print and exit
            if (previousScores.isFull() && previousScores.sameAs(bestFitScore, MIN_GENERATIONS)) {
                System.out.println("done");
                System.exit(0);
            }
//            if(previousScores.isFull() && bestFitScore < previousScores.average()) {
//                                   System.out.println("done");
//                System.exit(0);
//            }

            previousScores.add(bestFitScore);

            population.clear();
            //population.add(bestFit); //Include the previous generation's best fit as well
            for (int i = 0; i < POPULATION_SIZE; i++) {
                population.add((T) bestFit.mutate());
            }
        }
    }
}

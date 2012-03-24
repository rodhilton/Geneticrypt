import genes.GeneSequence;
import genes.GeneticSearch;
import org.apache.commons.collections.buffer.CircularFifoBuffer;
import simple.SimpleCalculator;
import simple.SimpleSearch;
import util.ScoreKeeper;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int MIN_GENERATIONS = 50;
    private static final double HALT_THRESHOLD = 0;
    private static final int POPULATION_SIZE = 20;


    public static void main(String[] args) {

        GeneticSearch search = new SimpleSearch();

        List<GeneSequence> population = new ArrayList<GeneSequence>(POPULATION_SIZE);
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(search.init());
        }

        ScoreKeeper previousScores = new ScoreKeeper(MIN_GENERATIONS);

        while (true) {
            GeneSequence bestFit = population.get(0);
            double bestFitScore = search.score(bestFit);

            for (int i = 1; i < population.size(); i++) {
                GeneSequence sequence = population.get(i);
                double score = search.score(sequence);

                if (score > bestFitScore) {
                    bestFitScore = score;
                    bestFit = sequence;
                }
            }

            System.out.println(bestFit);

            //if the abs val of last 5 average minus this score is < a specified value, print and exit
            if(previousScores.isFull() && bestFitScore - previousScores.average() < HALT_THRESHOLD) {
                System.out.println("done");
                System.exit(0);
            }

            previousScores.add(bestFitScore);

            population.clear();
            for (int i = 0; i < POPULATION_SIZE; i++) {
                population.add(bestFit.mutate());
            }
        }

    }
}

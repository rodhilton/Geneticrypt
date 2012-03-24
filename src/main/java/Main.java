import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int POPULATION_SIZE = 20;

    public static void main(String[] args) {
        List<SimpleSequence> population = new ArrayList<SimpleSequence>(POPULATION_SIZE);
        for (int i = 0; i < POPULATION_SIZE; i++) {
            SimpleSequence gene = new SimpleSequence();
            gene.randomize();
            population.add(gene);
        }

        while (true) {
            SimpleCalculator calc = new SimpleCalculator();

            SimpleSequence bestFit = population.get(0);
            double bestFitScore = calc.getFitness(bestFit);

            for (int i = 1; i < population.size(); i++) {
                SimpleSequence sequence = population.get(i);
                double score = calc.getFitness(sequence);

                if (score > bestFitScore) {
                    bestFitScore = score;
                    bestFit = sequence;
                }
            }

            System.out.println(bestFit.getSequence());

            if(bestFit.getSequence().matches("^A+$")) {
                System.out.println("done");
                System.exit(0);
            }

            population.clear();
            for (int i = 0; i < POPULATION_SIZE; i++) {
                population.add(bestFit.mutate());
            }
        }


    }
}

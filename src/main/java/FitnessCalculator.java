public interface FitnessCalculator<T extends GeneSequence> {

    public double getFitness(T sequence);
}

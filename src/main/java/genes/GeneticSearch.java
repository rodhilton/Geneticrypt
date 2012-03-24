package genes;

public interface GeneticSearch<T extends GeneSequence> {

    public T init();

    double score(T sequence);
}

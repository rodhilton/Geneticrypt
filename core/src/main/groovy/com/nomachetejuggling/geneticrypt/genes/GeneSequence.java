package com.nomachetejuggling.geneticrypt.genes;

public interface GeneSequence {
    public GeneSequence mutate();
    public double score();
}
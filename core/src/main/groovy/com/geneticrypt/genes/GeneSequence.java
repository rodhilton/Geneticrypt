package com.geneticrypt.genes;

public interface GeneSequence {
    public GeneSequence mutate();
    public double score();
}
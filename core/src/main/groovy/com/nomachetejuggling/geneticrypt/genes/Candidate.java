package com.nomachetejuggling.geneticrypt.genes;

public interface Candidate {
    public Candidate mutate();
    public double score();
}
package com.nomachetejuggling.geneticrypt.util;

import java.util.LinkedList;

public class ScoreKeeper {
    private LinkedList<Double> queue;
    private int size;
    private static final double SAME_THRESHOLD = .01;

    public ScoreKeeper(int size) {
        this.queue = new LinkedList<Double>();
        this.size = size;
    }

    public void add(double score) {
        queue.add(score);
        while(queue.size() > size) {
            queue.removeFirst();
        }
    }

    public double average() {
        double sum = 0;
        for(Double d: queue) {
            sum = sum + d;
        }
        return sum / queue.size();
    }

    public double getLast() {
        return queue.getLast();
    }

    public boolean isFull() {
        return size == queue.size();
    }

    public boolean sameAs(double bestFitScore, int howMany) {
        for(int i=1;i<=howMany;i++) {
            int index = queue.size() - i;
            double score = queue.get(index);
            if(Math.abs(bestFitScore - score) > SAME_THRESHOLD) {
                return false;
            }
        }

        return true;
    }
}

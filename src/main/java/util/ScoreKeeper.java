package util;

import java.util.LinkedList;
import java.util.Queue;

public class ScoreKeeper {
    private LinkedList<Double> queue;
    private int size;

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

    public boolean isFull() {
        return size == queue.size();
    }
}

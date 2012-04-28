package com.nomachetejuggling.geneticrypt.genes.simple

import com.nomachetejuggling.geneticrypt.genes.Candidate

class SimpleSequence implements Candidate {
    private static int LENGTH = 26;
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    def state

    SimpleSequence() {
        this(randomize());
    }

    static String randomize() {
        StringBuilder temp = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            temp.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return temp.toString()
    }

    SimpleSequence(String state) {
        this.state = state
    }

    @Override
    public SimpleSequence mutate() {
        Random random = new Random();
        int index = random.nextInt(state.length());
        String before = state.substring(0, index);
        char mutated = alphabet.charAt(random.nextInt(alphabet.length()));
        String after = state.substring(index + 1);

        SimpleSequence newSequence = new SimpleSequence();
        newSequence.state = before + mutated + after;
        return newSequence;
    }

    public String getSequence() {
        return state
    }

    public String toString() {
        return state
    }

    @Override
    double score() {
        int count = 0;
        for (char c: state.toCharArray()) {
            if (c == 'A') {
                count++;
            }
        }
        return count;
    }
}
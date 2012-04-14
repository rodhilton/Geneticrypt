package com.nomachetejuggling.geneticrypt.genes.sort

import com.nomachetejuggling.geneticrypt.genes.GeneSequence
import static com.nomachetejuggling.geneticrypt.util.Util.shuffle

class LetterSequence implements GeneSequence {
    String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String state

    LetterSequence() {
        //random
        state = shuffle(alphabet)
    }

    private LetterSequence(String state) {
        this.state = state
    }

    @Override
    public LetterSequence mutate() {
        Random random = new Random();
        int index1 = random.nextInt(state.length())
        int index2 = random.nextInt(state.length())

        StringBuilder sb = new StringBuilder();
        sb.append(state);

        char temp = state.charAt(index1)
        sb.setCharAt(index1, state.charAt(index2));
        sb.setCharAt(index2, temp);


        LetterSequence newSequence = new LetterSequence(sb.toString());
        return newSequence;
    }

    public String getSequence() {
        return state
    }

    public String toString() {
        return state
    }

    @Override
    void randomize() {
        state=shuffle(alphabet)
    }

    @Override
    double score() {
        String seq = state

        char previousChar = seq[0]
        double score = 0;
        for(int i=1;i<seq.length();i++) {
            char currentChar = seq[i]
            if(currentChar > previousChar) {
                score += 5
            } else {
                score -= 1
            }
            previousChar = currentChar

        }
        return score
    }
}
package com.nomachetejuggling.geneticrypt.genes.exact

import com.nomachetejuggling.geneticrypt.genes.GeneSequence

/**
 * A sequence which is evaluated according to how closely it matches an exact string.
 */
class ExactSequence implements GeneSequence {

    String goalString
    String sequence

    ExactSequence(String goalString) {
        this(generate(goalString), goalString)
    }

    static String generate(String s) {
        StringBuilder sb=new StringBuilder();
        Random rand=new Random();
        for(int i=0;i<s.length();i++) {
            sb.append(s.charAt(rand.nextInt(s.length())))
        }
        return sb.toString();
    }

    private ExactSequence(String sequence, String goalString) {
        this.sequence = sequence
        this.goalString = goalString
    }

    @Override
    GeneSequence mutate() {
        Random random = new Random();
        int index1 = random.nextInt(sequence.length())
        int index2 = random.nextInt(sequence.length())

        StringBuilder sb = new StringBuilder();
        sb.append(sequence);

        char temp = sequence.charAt(index1)
        sb.setCharAt(index1, sequence.charAt(index2));
        sb.setCharAt(index2, temp);

        return new ExactSequence(sb.toString(), goalString);
    }

    def String toString() {
        return sequence
    }

    @Override
    double score() {
        double count=0;
        char[] seqArray = sequence.toCharArray()
        char[] goalArray = goalString.toCharArray()
        for(int i=0;i<seqArray.length;i++) {
            char x = seqArray[i]
            char y = goalArray[i]
            if(x==y) {
                count++;
            }
        }
        return count;
    }
}

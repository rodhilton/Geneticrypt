package genes.exact

import genes.GeneSequence
import static util.Util.shuffle
import org.apache.commons.lang.StringUtils

/**
 * A sequence which is evaluated according to how closely it matches an exact string.
 */
class ExactSequence implements GeneSequence {

    String goalString
    String sequence

    ExactSequence(String goalString) {
        this(shuffle(goalString), goalString)
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

        return new ExactSequence(sb.toString());
    }

    def String toString() {
        return sequence
    }

    @Override
    double score() {
        return -StringUtils.getLevenshteinDistance(sequence, goalString)
    }
}

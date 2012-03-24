public class SimpleCalculator implements FitnessCalculator<SimpleSequence> {
    @Override
    public double getFitness(SimpleSequence sequence) {
        String s = sequence.getSequence();
        int count=0;
        for(char c: s.toCharArray()) {
            if(c=='A') {
                count++;
            }
        }
        return count;
    }
}

import java.util.Random;

public class SimpleSequence implements GeneSequence {
    private static int LENGTH = 26;
    private String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String state;

    @Override
    public void randomize() {
        StringBuilder temp = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<LENGTH;i++) {
            temp.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        state = temp.toString();
    }

    @Override
    public SimpleSequence mutate() {
        Random random = new Random();
        int index = random.nextInt(state.length());
        String before = state.substring(0,index);
        char mutated = alphabet.charAt(random.nextInt(alphabet.length()));
        String after = state.substring(index+1);

        SimpleSequence newSequence = new SimpleSequence();
        newSequence.state = before+mutated+after;
        return newSequence;
    }

    public String getSequence() {
        return state;
    }
}

import genes.GeneticSearch
import genes.GeneSequence
import simple.SimpleSequence

class SortSearch implements GeneticSearch<LetterSequence> {
    String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    LetterSequence init() {
        return new LetterSequence(shuffle(alphabet));
    }

    @Override
    double score(LetterSequence sequence) {
        String seq = sequence.state

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

    public static String shuffle(String s)
    {

        String shuffledString = "";

        while (s.length() != 0)
        {
            int index = (int) Math.floor(Math.random() * s.length());
            char c = s.charAt(index);
            s = s.substring(0,index)+s.substring(index+1);
            shuffledString += c;
        }

        return shuffledString;

    }
}



class LetterSequence implements GeneSequence {
    String state

    LetterSequence(String state) {
        this.state = state
    }

    @Override
    public LetterSequence mutate() {
        Random random = new Random();
        int index1 = random.nextInt(state.length())
        int index2 = random.nextInt(state.length())

        int i1 = Math.min(index1, index2);
        int i2 = Math.min(index1,index2)

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
}
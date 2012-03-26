import com.google.common.base.Supplier;
import genes.exact.ExactSequence;
import genes.simple.SimpleSequence;
import simulator.GeneticSimulator;

public class Main {

    public static void main(String[] args) {

        GeneticSimulator<ExactSequence> geneticSimulator = new GeneticSimulator<ExactSequence>();

        geneticSimulator.simulate(new Supplier<ExactSequence>() {

            @Override
            public ExactSequence get() {
                return new ExactSequence("this is a motherfucking test with a lot of test, hopefully it will make the shit longer and that will have a good effect possibly");
            }
        });

    }
}

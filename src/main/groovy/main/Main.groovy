package main

import com.google.common.base.Supplier

import simulator.GeneticSimulator
import genes.exact.ExactSequence
import simulator.ThreadedGeneticSimulator
import util.UpdateCallback

class Main {

    static void main(String[] args) {

        GeneticSimulator<ExactSequence> geneticSimulator = new ThreadedGeneticSimulator<ExactSequence>();
        geneticSimulator.registerUpdates(new UpdateCallback<ExactSequence>() {
            @Override
            void call(ExactSequence object) {
                System.out.println(object+" "+object.score());
            }

        })

        geneticSimulator.simulate(new Supplier<ExactSequence>() {

            @Override
            public ExactSequence get() {
                return new ExactSequence("Why, man, he doth bestride the narrow world, Like a Colossus, and we petty men, Walk under his huge legs and peep about, To find ourselves dishonourable graves., Men at some time are masters of their fates:, The fault, dear Brutus, is not in our stars,, But in ourselves, that we are underlings., Brutus and Caesar: what should be in that 'Caesar'?, Why should that name be sounded more than yours?, Write them together, yours is as fair a name;, Sound them, it doth become the mouth as well;, Weigh them, it is as heavy; conjure with 'em,, Brutus will start a spirit as soon as Caesar., Now, in the names of all the gods at once,, Upon what meat doth this our Caesar feed,, That he is grown so great? Age, thou art shamed!, Rome, thou hast lost the breed of noble bloods!, When went there by an age, since the great flood,, But it was famed with more than with one man?, When could they say till now, that talk'd of Rome,, That her wide walls encompass'd but one man?, Now is it Rome indeed and room enough,, When there is in it but one only man., O, you and I have heard our fathers say,, There was a Brutus once that would have brook'd, The eternal devil to keep his state in Rome, As easily as a king.");
            }
        });

    }
}

package simulator;

import com.google.common.base.Supplier;
import util.UpdateCallback;

public interface Simulator<T> {
    void simulate(Supplier<T> supplier);
    void registerUpdates(UpdateCallback<T> observer);
}

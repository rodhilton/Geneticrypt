package com.geneticrypt.simulator;

import com.google.common.base.Supplier;
import com.geneticrypt.util.UpdateCallback;

public interface Simulator<T> {
    void simulate(Supplier<T> supplier);
    void registerUpdates(UpdateCallback<T> observer);
}

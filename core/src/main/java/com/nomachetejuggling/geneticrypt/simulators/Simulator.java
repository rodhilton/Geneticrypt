package com.nomachetejuggling.geneticrypt.simulators;

import com.google.common.base.Supplier;
import com.nomachetejuggling.geneticrypt.util.UpdateCallback;

public interface Simulator<T> {
    void simulate(Supplier<T> supplier);
    void registerUpdates(UpdateCallback<T> observer);
}

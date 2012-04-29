package com.nomachetejuggling.geneticrypt.util;

import java.util.Collection;

public interface UpdateCallback<T> {
    void call(Collection<T> selected);
}

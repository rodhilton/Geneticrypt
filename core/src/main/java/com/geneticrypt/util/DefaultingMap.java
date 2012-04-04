package com.geneticrypt.util;

import java.util.HashMap;
import java.util.Map;

public class DefaultingMap<K,V> extends HashMap<K, V> {

    private V defaultValue;

    public DefaultingMap(V defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    public DefaultingMap(V defaultValue, Map<K, V> initialMap) {
        this(defaultValue);
        super.putAll(initialMap);
    }

    @Override
    public V get(Object key) {
        if(super.containsKey(key)) {
            return super.get(key);
        } else {
            return defaultValue;
        }
    }
}

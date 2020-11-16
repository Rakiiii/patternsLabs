package dev.smurf.patternsdrawlab.java.vectors;


import java.util.ArrayList;
import java.util.List;

import dev.smurf.patternsdrawlab.java.interfaces.IVector;

abstract public class BaseVector<T> implements IVector<T> {
    protected List<T> vector;

    @Override
    public Integer size() {
        return vector.size();
    }

    @Override
    public T getElement(Integer i) {
        return vector.get(i);
    }

    @Override
    public void setElement(Integer i, T element) {
        vector.set(i, element);
    }
}

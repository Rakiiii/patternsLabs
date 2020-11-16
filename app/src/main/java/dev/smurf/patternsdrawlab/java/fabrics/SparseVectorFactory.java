package dev.smurf.patternsdrawlab.java.fabrics;

import dev.smurf.patternsdrawlab.java.interfaces.IVector;
import dev.smurf.patternsdrawlab.java.vectors.SparseVector;


public class SparseVectorFactory<T> implements VectorFactory<T> {
    private T zeroValue;

    public SparseVectorFactory(T zeroValue) {
        this.zeroValue = zeroValue;
    }

    @Override
    public IVector<T> createVector(Integer size) {
        return new SparseVector<T>(size, zeroValue);
    }
}

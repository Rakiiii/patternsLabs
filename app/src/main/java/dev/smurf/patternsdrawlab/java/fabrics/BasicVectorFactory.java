package dev.smurf.patternsdrawlab.java.fabrics;

import dev.smurf.patternsdrawlab.java.interfaces.IVector;
import dev.smurf.patternsdrawlab.java.vectors.BasicVector;

public class BasicVectorFactory<T> implements VectorFactory<T> {
    @Override
    public IVector<T> createVector(Integer size) {
        return new BasicVector<T>(size);
    }
}

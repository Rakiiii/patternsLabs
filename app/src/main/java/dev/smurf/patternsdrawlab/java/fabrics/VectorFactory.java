package dev.smurf.patternsdrawlab.java.fabrics;


import dev.smurf.patternsdrawlab.java.interfaces.IVector;

public interface VectorFactory<T> {
    IVector<T> createVector(Integer size);
}

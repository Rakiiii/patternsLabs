package dev.smurf.patternsdrawlab.java.fabrics;

public interface ElementFactory<T> {
    T createNonZeroElementSmallerThenMax(T maxElement);
}

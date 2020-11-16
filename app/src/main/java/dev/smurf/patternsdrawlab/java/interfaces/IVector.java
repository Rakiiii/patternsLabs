package dev.smurf.patternsdrawlab.java.interfaces;

public interface IVector<T> {
    Integer size();

    void setElement(Integer i, T element);

    T getElement(Integer i);
}

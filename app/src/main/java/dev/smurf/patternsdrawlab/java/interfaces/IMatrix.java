package dev.smurf.patternsdrawlab.java.interfaces;

public interface IMatrix<T> {
    Integer cols();

    Integer rows();

    void setElement(Integer row, Integer col, T element);

    T getElement(Integer row, Integer col);
}

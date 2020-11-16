package dev.smurf.patternsdrawlab.java.interfaces;

public interface MatrixDecorator<T> extends IMatrix<T> {
    public IMatrix<T> getSuper();
}

package dev.smurf.patternsdrawlab.java.matrix;

import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;
import dev.smurf.patternsdrawlab.java.interfaces.MatrixDecorator;

public class SparceMatrixVisibilityDecorator<T> implements MatrixDecorator<T> {
    private final IMatrix<T> matrix;
    public final T zeroValue;

    public SparceMatrixVisibilityDecorator(IMatrix<T> matrix, T zeroValue) {
        this.matrix = matrix;
        this.zeroValue = zeroValue;
    }

    @Override
    public T getElement(Integer row, Integer col) {
        return !matrix.getElement(row, col).equals(zeroValue) ? matrix.getElement(row, col) : null;
    }

    @Override
    public void setElement(Integer row, Integer col, T element) {
        matrix.setElement(row, col, element);
    }

    @Override
    public Integer rows() {
        return matrix.rows();
    }

    @Override
    public Integer cols() {
        return matrix.cols();
    }

    @Override
    public IMatrix<T> getSuper() {
        return matrix;
    }
}

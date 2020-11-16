package dev.smurf.patternsdrawlab.java.matrix;

import dev.smurf.patternsdrawlab.java.fabrics.VectorFactory;
import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;
import dev.smurf.patternsdrawlab.java.interfaces.IVector;

import java.util.ArrayList;

abstract public class BaseMatrix<T> implements IMatrix<T> {
    private ArrayList<IVector<T>> matrix;

    protected void reInitMatrix(VectorFactory<T> factory, Integer row, Integer col) {
        matrix = new ArrayList<IVector<T>>();
        for (int i = 0; i < col; i++) {
            reInitVector(i, factory, row);
        }
    }

    protected void reInitVector(Integer col, VectorFactory<T> factory, Integer row) {
        matrix.add(col, factory.createVector(row));
    }

    @Override
    public Integer cols() {
        return matrix.size();
    }

    @Override
    public Integer rows() {
        return matrix.get(0).size();
    }

    @Override
    public T getElement(Integer row, Integer col) {
        return matrix.get(col).getElement(row);
    }

    @Override
    public void setElement(Integer row, Integer col, T element) {
        matrix.get(col).setElement(row, element);
    }
}

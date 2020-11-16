package dev.smurf.patternsdrawlab.java.matrix;

import java.util.HashMap;
import java.util.Map;

import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;
import dev.smurf.patternsdrawlab.java.interfaces.MatrixDecorator;

public class MatrixRenumDecorator<T> implements MatrixDecorator<T> {
    private final IMatrix<T> matrix;
    private final Map<Integer, Integer> newRows = new HashMap<Integer, Integer>();
    private final Map<Integer, Integer> newCols = new HashMap<Integer, Integer>();

    public MatrixRenumDecorator(IMatrix<T> matrix) {
        this.matrix = matrix;
    }

    public void RenumRows(Integer oldNum, Integer newNum) {
        newRows.put(newNum, oldNum);
        newRows.put(oldNum, newNum);
    }

    public void RenumCols(Integer oldNum, Integer newNum) {
        newCols.put(newNum, oldNum);
        newCols.put(oldNum, newNum);
    }

    @Override
    public IMatrix<T> getSuper() {
        return matrix;
    }

    @Override
    public Integer cols() {
        return matrix.cols();
    }

    @Override
    public Integer rows() {
        return matrix.rows();
    }

    @Override
    public T getElement(Integer row, Integer col) {
        Integer r = newRows.get(row) != null ? newRows.get(row) : row;
        Integer c = newCols.get(col) != null ? newCols.get(col) : col;
        return matrix.getElement(r, c);
    }

    @Override
    public void setElement(Integer row, Integer col, T element) {
        Integer r = newRows.get(row) != null ? newRows.get(row) : row;
        Integer c = newCols.get(col) != null ? newCols.get(col) : col;
        matrix.setElement(r, c, element);
    }
}

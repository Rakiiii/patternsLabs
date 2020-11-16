package dev.smurf.patternsdrawlab.java.matrix;


import dev.smurf.patternsdrawlab.java.fabrics.SparseVectorFactory;

public class SparseMatrix<T> extends BaseMatrix<T> {
    public SparseMatrix(Integer row, Integer col, T zeroValue) {
        reInitMatrix(new SparseVectorFactory<T>(zeroValue), row, col);
    }
}

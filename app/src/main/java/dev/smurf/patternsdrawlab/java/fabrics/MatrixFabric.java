package dev.smurf.patternsdrawlab.java.fabrics;

import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;

public interface MatrixFabric<T> {
    public IMatrix<T> createMatrix(Integer row, Integer col);
}

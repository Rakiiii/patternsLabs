package dev.smurf.patternsdrawlab.java;

import dev.smurf.patternsdrawlab.java.drawers.DrawableInteger;
import dev.smurf.patternsdrawlab.java.fabrics.MatrixFabric;
import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;

public class MatrixIntegerTranslator {
    public static IMatrix<DrawableInteger> translateMatrix(IMatrix<Integer> matrix, MatrixFabric<DrawableInteger> fabric) {
        IMatrix<DrawableInteger> newmatrix = fabric.createMatrix(matrix.rows(), matrix.cols());
        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.cols(); j++) {
                newmatrix.setElement(i, j, new DrawableInteger(matrix.getElement(i, j)));
            }
        }
        return newmatrix;
    }
}

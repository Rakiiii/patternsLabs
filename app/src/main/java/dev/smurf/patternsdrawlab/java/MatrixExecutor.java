package dev.smurf.patternsdrawlab.java;

import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;
import dev.smurf.patternsdrawlab.java.interfaces.MatrixDecorator;

public class MatrixExecutor {
    public static IMatrix<Integer> execute(IMatrix<Integer> matrix) {
        if (matrix instanceof MatrixDecorator<?>) {
            return ((MatrixDecorator<Integer>) matrix).getSuper();
        } else {
            return null;
        }
    }
}

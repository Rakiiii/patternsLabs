package dev.smurf.patternsdrawlab.java;

import java.util.*;

import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;

public class MatrixInitilizer {
    public static void initializeMatrix(IMatrix<Integer> matrix, Integer amountOfNonZeros, Integer maxElement) {
        HashMap<Integer, Integer> nonZeroElement = new HashMap<Integer, Integer>(amountOfNonZeros);
        Integer maxMatrixNumber = matrix.cols() * matrix.rows();
        if (maxMatrixNumber < amountOfNonZeros)
            throw new IllegalArgumentException("To many non zero elements in matrix");
        for (int i = 0; i < amountOfNonZeros; i++) {
            Integer randomNumber = new Random().nextInt(maxElement);
            while (randomNumber == 0) {
                randomNumber = new Random().nextInt(maxElement);
            }
            Integer randomPosition = new Random().nextInt(maxMatrixNumber);
            while (nonZeroElement.containsKey(randomPosition)) {
                randomPosition = new Random().nextInt(maxMatrixNumber);
            }
            nonZeroElement.put(i, randomNumber);
        }
        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.cols(); j++) {
                if (nonZeroElement.containsKey(i * matrix.cols() + j)) {
                    matrix.setElement(i, j, nonZeroElement.get(i * matrix.cols() + j));
                } else {
                    matrix.setElement(i, j, 0);
                }
            }
        }
    }
}

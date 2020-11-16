package dev.smurf.patternsdrawlab.java;

import java.util.Comparator;

import dev.smurf.patternsdrawlab.java.interfaces.Averager;
import dev.smurf.patternsdrawlab.java.interfaces.ElementsSummer;
import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;

public class MatrixStatistic<T> {
    private IMatrix<T> matrix;

    public MatrixStatistic(IMatrix<T> matrix) {
        this.matrix = matrix;
    }

    public T maxElement(Comparator<T> comparator) {
        T max = null;
        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.cols(); j++) {
                if (max == null || comparator.compare(matrix.getElement(i, j), max) > 0) {
                    max = matrix.getElement(i, j);
                }
            }
        }
        return max;
    }

    public T elementsSum(ElementsSummer<T> summer) {
        T sum = null;
        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.cols(); j++) {
                if (sum == null) {
                    sum = matrix.getElement(i, j);
                } else {
                    sum = summer.sum(sum, matrix.getElement(i, j));
                }
            }
        }
        return sum;
    }

    public T average(Averager<T> averager, ElementsSummer<T> summer) {
        return averager.average(elementsSum(summer), matrix.cols() * matrix.rows());
    }

    public Integer amountOfNonZeros(T zeroElement) {
        Integer counter = 0;
        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.cols(); j++) {
                if (!matrix.getElement(i, j).equals(zeroElement)) {
                    counter++;
                }
            }
        }
        return counter;
    }
}

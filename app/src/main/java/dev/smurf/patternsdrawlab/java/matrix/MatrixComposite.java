package dev.smurf.patternsdrawlab.java.matrix;

import java.util.ArrayList;
import java.util.List;

import dev.smurf.patternsdrawlab.java.interfaces.IDrawable;
import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;

/**
 * Компоновщик матриц
 *
 * @param <T> Тип элементов матриц
 */
public class MatrixComposite<T extends IDrawable> implements IMatrix<T> {
    /**
     * Коллекция матриц
     */
    private List<List<IMatrix<T>>> matrixList;

    /**
     * Конструктор компоновщика на основе коллекции матриц
     *
     * @param matrixList Коллекция матриц
     */
    private MatrixComposite(List<List<IMatrix<T>>> matrixList) {
        this.matrixList = matrixList;
    }

    /**
     * Импелементация интерфейса матрицы
     */
    @Override
    public Integer rows() {
        Integer overAllRows = 0;
        for (List<IMatrix<T>> matrixRow : matrixList) {
            Integer maxRow = 0;
            for (IMatrix<T> matrix : matrixRow) {
                if (matrix.rows() > maxRow) maxRow = matrix.rows();
            }
            overAllRows += maxRow;
        }
        return overAllRows;
    }

    /**
     * Импелементация интерфейса матрицы
     */
    @Override
    public Integer cols() {
        int maxCols = 0;
        for (List<IMatrix<T>> matrixRow : matrixList) {
            Integer overAllCols = 0;
            for (IMatrix<T> matrix : matrixRow) {
                overAllCols += matrix.cols();
            }
            if (overAllCols > maxCols) maxCols = overAllCols;
        }
        return maxCols;
    }

    /**
     * Метод расчета колличества элементов в составленной строке матриц в высоту
     * @param matrixLine Строка матриц
     * @return Максимальную высоту столбца из всех матриц в строке
     */
    private Integer lineHeight(List<IMatrix<T>> matrixLine) {
        Integer maxRow = 0;
        for (IMatrix<T> matrix : matrixLine) {
            if (matrix.rows() > maxRow) maxRow = matrix.rows();
        }
        return maxRow;
    }

    /**
     * Импелементация интерфейса матрицы
     */
    @Override
    public T getElement(Integer row, Integer col) {
        Integer rowOffset = 0;
        for (List<IMatrix<T>> matrixRow : matrixList) {
            Integer height = lineHeight(matrixRow);
            if (rowOffset + height > row) {
                Integer colsOffset = 0;
                for (IMatrix<T> matrix : matrixRow) {
                    Integer width = matrix.cols();
                    if (colsOffset + width > col) {
                        Integer expectedMatrixHeight = row - rowOffset;
                        if (expectedMatrixHeight >= matrix.rows()) {
                            return null;
                        } else {
                            return matrix.getElement(expectedMatrixHeight, col - colsOffset);
                        }
                    }
                    colsOffset += width;
                }
                return null;
            }
            rowOffset += height;
        }
        return null;
    }

    /**
     * Импелементация интерфейса матрицы
     */
    @Override
    public void setElement(Integer row, Integer col, T element) {
        Integer rowOffset = 0;
        for (List<IMatrix<T>> matrixRow : matrixList) {
            Integer height = lineHeight(matrixRow);
            if (rowOffset + height >= row) {
                Integer colsOffset = 0;
                for (IMatrix<T> matrix : matrixRow) {
                    Integer width = matrix.cols();
                    if (colsOffset + width >= col) {
                        Integer expectedMatrixHeight = row - rowOffset;
                        if (expectedMatrixHeight <= matrix.rows()) {
                            matrix.setElement(expectedMatrixHeight, col - colsOffset, element);
                        }
                    }
                    colsOffset += width;
                }
            }
            rowOffset += height;
        }
    }

    /**
     * Билдер для компоновщика матриц
     * @param <M> Тип элемента компонуемой матрицы
     */
    public static class Builder<M extends IDrawable> {
        /**
         * Буферный список матриц
         */
        private List<List<IMatrix<M>>> matrixList;
        /**
         * Номер последнего ряда для добавления новой матриц в список матриц
         */
        private Integer lastRow = 0;

        public Builder() {
            matrixList = new ArrayList<>();
            matrixList.add(new ArrayList<>());
        }

        /**
         * Метод добовления матрицы в последний ряд
         * @param matrix Маттрица
         * @return this
         */
        public Builder<M> addMatrixInExistedRow(IMatrix<M> matrix) {
            matrixList.get(lastRow).add(matrix);
            return this;
        }

        /**
         * Метод добовления матрицы в следующий ряд
         * @param matrix Маттрица
         * @return this
         */
        public Builder<M> addMatrixInNextRow(IMatrix<M> matrix) {
            matrixList.add(new ArrayList<>());
            lastRow++;
            matrixList.get(lastRow).add(matrix);
            return this;
        }

        /**
         * Метод создания скомпоновонной матрицы
         * @return Компоновщик матриц
         */
        public MatrixComposite<M> build() {
            return new MatrixComposite<>(matrixList);
        }

    }
}

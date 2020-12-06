package dev.smurf.patternsdrawlab.java.matrix

import dev.smurf.patternsdrawlab.java.MatrixExecutor
import dev.smurf.patternsdrawlab.java.MatrixInitilizer
import dev.smurf.patternsdrawlab.java.MatrixIntegerTranslator
import dev.smurf.patternsdrawlab.java.drawers.DrawableInteger
import dev.smurf.patternsdrawlab.java.interfaces.DrawableMatrix
import dev.smurf.patternsdrawlab.java.interfaces.IDrawable
import dev.smurf.patternsdrawlab.java.interfaces.IMatrix
import java.util.*

/**
 * Вспомогательный класс для мимнимизации переписыания кода
 */
class MatrixConstructor {
    companion object {
        fun renumMatrix(smatrix: IMatrix<Int>): IMatrix<Int> {
            val decMatrix = MatrixRenumDecorator(smatrix)
            decMatrix.RenumRows(Random().nextInt(smatrix.rows()), Random().nextInt(smatrix.rows()))
            decMatrix.RenumCols(Random().nextInt(smatrix.cols()), Random().nextInt(smatrix.cols()))
            return decMatrix
        }

        fun restoreMatrix(matrix: IMatrix<Int>): IMatrix<Int> {
            return MatrixExecutor.execute(matrix)?.let { it } ?: matrix
        }

        fun constructCompositeMatrix(): IMatrix<DrawableInteger> =
            MatrixComposite.Builder<DrawableInteger>().apply {
                var matrix = BasicMatrix<Int>(2, 2)
                MatrixInitilizer.initializeMatrix(matrix, 4, 9)
                var translatedMatrix =
                    MatrixIntegerTranslator.translateMatrix(matrix) { row, col ->
                        BasicMatrix(
                            row,
                            col
                        )
                    }
                addMatrixInExistedRow(translatedMatrix)
                matrix = BasicMatrix<Int>(4, 3)
                MatrixInitilizer.initializeMatrix(matrix, 12, 9)
                translatedMatrix =
                    MatrixIntegerTranslator.translateMatrix(matrix) { row, col ->
                        BasicMatrix(
                            row,
                            col
                        )
                    }
                addMatrixInExistedRow(translatedMatrix)
                matrix = BasicMatrix<Int>(1, 3)
                MatrixInitilizer.initializeMatrix(matrix, 3, 9)
                translatedMatrix =
                    MatrixIntegerTranslator.translateMatrix(matrix) { row, col ->
                        BasicMatrix(
                            row,
                            col
                        )
                    }
                addMatrixInExistedRow(wrapMatrixIntoBorder(translatedMatrix))
                matrix = BasicMatrix<Int>(2, 4)
                MatrixInitilizer.initializeMatrix(matrix, 8, 9)
                translatedMatrix =
                    MatrixIntegerTranslator.translateMatrix(matrix) { row, col ->
                        BasicMatrix(
                            row,
                            col
                        )
                    }
                addMatrixInNextRow(wrapMatrixIntoBorder(translatedMatrix))
                matrix = BasicMatrix<Int>(2, 3)
                MatrixInitilizer.initializeMatrix(matrix, 6, 9)
                translatedMatrix =
                    MatrixIntegerTranslator.translateMatrix(matrix) { row, col ->
                        BasicMatrix(
                            row,
                            col
                        )
                    }
                addMatrixInExistedRow(wrapMatrixIntoBorder(translatedMatrix))
                matrix = BasicMatrix<Int>(1, 1)
                MatrixInitilizer.initializeMatrix(matrix, 1, 9)
                translatedMatrix =
                    MatrixIntegerTranslator.translateMatrix(matrix) { row, col ->
                        BasicMatrix(
                            row,
                            col
                        )
                    }
                addMatrixInNextRow(wrapMatrixIntoBorder(translatedMatrix))
            }.build()

        private fun <T : IDrawable> wrapMatrixIntoBorder(matrix: IMatrix<T>): DrawableMatrix<T> {
            return BorderMatrixDecorator<T>(DrawableMatrixDecorator<T>(matrix))
        }
    }
}
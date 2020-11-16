package dev.smurf.patternsdrawlab

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.smurf.patternsdrawlab.java.MatrixExecutor
import dev.smurf.patternsdrawlab.java.MatrixInitilizer
import dev.smurf.patternsdrawlab.java.MatrixIntegerTranslator
import dev.smurf.patternsdrawlab.java.drawers.*
import dev.smurf.patternsdrawlab.java.fabrics.MatrixFabric
import dev.smurf.patternsdrawlab.java.interfaces.DrawableMatrix
import dev.smurf.patternsdrawlab.java.interfaces.IDrawable
import dev.smurf.patternsdrawlab.java.interfaces.IMatrix
import dev.smurf.patternsdrawlab.java.matrix.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var bmatrix: IMatrix<Int> = BasicMatrix<Int>(3, 3)

    init {
        MatrixInitilizer.initializeMatrix(bmatrix, 7, 9)
    }

    private var smatrix: IMatrix<Int> = SparseMatrix<Int>(3, 3, 0)

    init {
        MatrixInitilizer.initializeMatrix(smatrix, 7, 9)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        console_out.text = "kjnk.jnj\nnjkkjscnd\nnljn"

        border.setOnClickListener {
            isBorder = !isBorder
        }
        basic_matrix.setOnClickListener {
            drawBasicMatrix()
        }

        space_matrix.setOnClickListener {
            drawSpareMatrix()
        }

        space_matrix_renum.setOnClickListener {
            val decMatrix = MatrixRenumDecorator(smatrix)
            decMatrix.RenumRows(Random().nextInt(smatrix.rows()), Random().nextInt(smatrix.rows()))
            decMatrix.RenumCols(Random().nextInt(smatrix.cols()), Random().nextInt(smatrix.cols()))
            smatrix = decMatrix
            drawSpareMatrix()
        }

        basic_matrix_renum.setOnClickListener {
            val decMatrix = MatrixRenumDecorator(bmatrix)
            decMatrix.RenumRows(Random().nextInt(bmatrix.rows()), Random().nextInt(bmatrix.rows()))
            decMatrix.RenumCols(Random().nextInt(bmatrix.cols()), Random().nextInt(bmatrix.cols()))
            bmatrix = decMatrix

            drawBasicMatrix()
        }

        basic_matrix_restore.setOnClickListener {
            bmatrix = MatrixExecutor.execute(bmatrix)?.let { it } ?: bmatrix
            drawBasicMatrix()
        }

        space_matrix_restore.setOnClickListener {
            smatrix = MatrixExecutor.execute(smatrix)?.let { it } ?: smatrix
            drawSpareMatrix()
        }

        draw_composite_matrix.setOnClickListener {
            drawCompositeMatrix()
        }
    }

    private fun drawSpareMatrix() {
        console_out.text = ""
        val decorableMatrix = smatrix
        var matrix: DrawableMatrix<DrawableInteger> =
            DrawableMatrixDecorator(
                SparceMatrixVisibilityDecorator(
                    MatrixIntegerTranslator.translateMatrix(
                        decorableMatrix
                    ) { row, col -> SparseMatrix<DrawableInteger>(row, col, DrawableInteger(0)); },
                    DrawableInteger(0)
                )
            )
        if (isBorder) {
            matrix = BorderMatrixDecorator(matrix)
        }
        matrix.draw(TextViewPainter(console_out), Point(0, 0))
        matrix.draw(
            GuiPainter(image, Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)),
            Point(10, 10)
        )
    }

    private fun drawBasicMatrix() {
        console_out.text = ""
        val decorableMatrix = bmatrix

        var matrix: DrawableMatrix<DrawableInteger> =
            DrawableMatrixDecorator(
                MatrixIntegerTranslator.translateMatrix(
                    decorableMatrix
                ) { row, col -> BasicMatrix<DrawableInteger>(row, col); }
            )
        if (isBorder) {
            matrix = BorderMatrixDecorator(matrix)
        }
        matrix.draw(TextViewPainter(console_out), Point(0, 0))
        matrix.draw(
            GuiPainter(image, Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)),
            Point(10, 10)
        )
    }

    private fun constructCompositeMatrix() = MatrixComposite.Builder<DrawableInteger>().apply {
        var matrix = BasicMatrix<Int>(2, 2)
        MatrixInitilizer.initializeMatrix(matrix, 4, 9)
        var translatedMatrix =
            MatrixIntegerTranslator.translateMatrix(matrix) { row, col -> BasicMatrix(row, col) }
        addMatrixInExistedRow(translatedMatrix)
        matrix = BasicMatrix<Int>(4, 3)
        MatrixInitilizer.initializeMatrix(matrix, 12, 9)
        translatedMatrix =
            MatrixIntegerTranslator.translateMatrix(matrix) { row, col -> BasicMatrix(row, col) }
        addMatrixInExistedRow(translatedMatrix)
        matrix = BasicMatrix<Int>(1, 3)
        MatrixInitilizer.initializeMatrix(matrix, 3, 9)
        translatedMatrix =
            MatrixIntegerTranslator.translateMatrix(matrix) { row, col -> BasicMatrix(row, col) }
        addMatrixInExistedRow(wrapMatrixIntoBorder(translatedMatrix))
        matrix = BasicMatrix<Int>(2, 4)
        MatrixInitilizer.initializeMatrix(matrix, 8, 9)
        translatedMatrix =
            MatrixIntegerTranslator.translateMatrix(matrix) { row, col -> BasicMatrix(row, col) }
        addMatrixInNextRow(wrapMatrixIntoBorder(translatedMatrix))
        matrix = BasicMatrix<Int>(2, 3)
        MatrixInitilizer.initializeMatrix(matrix, 6, 9)
        translatedMatrix =
            MatrixIntegerTranslator.translateMatrix(matrix) { row, col -> BasicMatrix(row, col) }
        addMatrixInExistedRow(wrapMatrixIntoBorder(translatedMatrix))
        matrix = BasicMatrix<Int>(1, 1)
        MatrixInitilizer.initializeMatrix(matrix, 1, 9)
        translatedMatrix =
            MatrixIntegerTranslator.translateMatrix(matrix) { row, col -> BasicMatrix(row, col) }
        addMatrixInNextRow(wrapMatrixIntoBorder(translatedMatrix))
    }.build()

    private fun drawCompositeMatrix() {
        console_out.text = ""
        val decorableMatrix = constructCompositeMatrix()

        var matrix: DrawableMatrix<DrawableInteger> =
            DrawableMatrixDecorator(decorableMatrix)
        if (isBorder) {
            matrix = BorderMatrixDecorator(matrix)
        }
        matrix.draw(TextViewPainter(console_out), Point(0, 0))
        matrix.draw(
            GuiPainter(image, Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)),
            Point(10, 10)
        )
    }


    private fun <T : IDrawable> wrapMatrixIntoBorder(matrix: IMatrix<T>): DrawableMatrix<T> {
        return BorderMatrixDecorator<T>(DrawableMatrixDecorator<T>(matrix))
    }


    private var isBorder = false
}
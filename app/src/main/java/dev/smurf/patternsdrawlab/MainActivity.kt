package dev.smurf.patternsdrawlab

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import dev.smurf.patternsdrawlab.java.Model
import dev.smurf.patternsdrawlab.java.MatrixInitilizer
import dev.smurf.patternsdrawlab.java.MatrixIntegerTranslator
import dev.smurf.patternsdrawlab.java.commands.Revert
import dev.smurf.patternsdrawlab.java.commands.matrixCommands.*
import dev.smurf.patternsdrawlab.java.drawers.*
import dev.smurf.patternsdrawlab.java.interfaces.DrawableMatrix
import dev.smurf.patternsdrawlab.java.interfaces.IMatrix
import dev.smurf.patternsdrawlab.java.matrix.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : FragmentActivity() {

    private var bmatrix: IMatrix<Int> = BasicMatrix<Int>(3, 3)
    private val model: Model = Model()

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
        model.state.compositeMatrix.observe(this) {
            drawCompositeMatrix(it)
        }

        model.state.basicMatrix.observe(this) {
            drawBasicMatrix(it)
        }

        model.state.sparceMatrix.observe(this) {
            drawSpareMatrix(it)
        }

        model.state.isBorders.observe(this) {
            border.isChecked = it
        }

        model.state.type.observe(this) {
            when (it) {
                MatrixTypes.BasicMatrix -> drawBasicMatrix(model.state.basicMatrix.value)
                MatrixTypes.SparceMatrix -> drawSpareMatrix(model.state.sparceMatrix.value)
                MatrixTypes.CompositeMatrix -> drawCompositeMatrix(model.state.compositeMatrix.value)
                MatrixTypes.NoRedraw -> return@observe
                null -> return@observe
            }
        }

        border.setOnClickListener {
            model.commandStream.value = BorderCommand()
        }
        basic_matrix.setOnClickListener {
            model.state.basicMatrix.value = bmatrix
        }

        space_matrix.setOnClickListener {
            model.state.basicMatrix.value = smatrix
        }

        space_matrix_renum.setOnClickListener {
            model.commandStream.value = RenumSparceMatrix()
        }

        basic_matrix_renum.setOnClickListener {
            model.commandStream.value = RenumBasicMatrix()
        }

        basic_matrix_restore.setOnClickListener {
            model.commandStream.value = RestoreBasicMatrix()
        }

        space_matrix_restore.setOnClickListener {
            model.commandStream.value = RestoreSparceMatrix()
        }

        draw_composite_matrix.setOnClickListener {
            model.commandStream.value = DrawCompositeMatrix()
        }

        revert.setOnClickListener {
            model.commandStream.value = Revert()
        }
    }

    private fun drawSpareMatrix(smatrix: IMatrix<Int>?) {
        if (smatrix == null) {
            image.setImageBitmap(Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888))
            console_out.text = ""
            return
        }
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

    private fun drawBasicMatrix(bmatrix: IMatrix<Int>?) {
        if (bmatrix == null) {
            image.setImageBitmap(Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888))
            console_out.text = ""
            return
        }
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


    private fun drawCompositeMatrix(cmatrix: IMatrix<DrawableInteger>?) {
        if (cmatrix == null) {
            image.setImageBitmap(Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888))
            console_out.text = ""
            return
        }
        console_out.text = ""
        val decorableMatrix = cmatrix//constructCompositeMatrix()

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


    private var isBorder = false
}
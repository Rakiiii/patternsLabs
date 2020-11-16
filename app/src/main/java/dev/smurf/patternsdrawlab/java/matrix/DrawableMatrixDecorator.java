package dev.smurf.patternsdrawlab.java.matrix;

import dev.smurf.patternsdrawlab.java.drawers.Point;
import dev.smurf.patternsdrawlab.java.interfaces.DrawableMatrix;
import dev.smurf.patternsdrawlab.java.interfaces.IDrawable;
import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;
import dev.smurf.patternsdrawlab.java.interfaces.IPainter;
import dev.smurf.patternsdrawlab.java.interfaces.MatrixDecorator;

public class DrawableMatrixDecorator<T extends IDrawable> implements DrawableMatrix<T>, MatrixDecorator<T> {
    private final IMatrix<T> matrix;

    public DrawableMatrixDecorator(IMatrix<T> matrix) {
        this.matrix = matrix;
    }

    @Override
    public IMatrix<T> getSuper() {
        return matrix;
    }

    @Override
    public Integer cols() {
        return matrix.cols();
    }

    @Override
    public T getElement(Integer row, Integer col) {
        return matrix.getElement(row, col);
    }

    @Override
    public Integer rows() {
        return matrix.rows();
    }

    @Override
    public void setElement(Integer row, Integer col, T element) {
        matrix.setElement(row, col, element);
    }

    @Override
    public Point measure(IPainter painter) {
        int maxLength = getMaxSize(painter, matrix.rows(), matrix.cols(), point -> point.getX());
        int maxHeight = getMaxSize(painter, matrix.cols(), matrix.rows(),point -> point.getY());
        return new Point(maxLength, maxHeight);
    }

    private Integer getMaxSize(IPainter painter, Integer firstDirection, Integer secondDirection,cordExecutor e) {
        int max = 0;
        for (int i = 0; i < firstDirection; i++) {
            int subMax = 0;
            for (int j = 0; j < secondDirection; j++) {
                T element  = matrix.getElement(i, j);
                if(element != null) {
                    subMax += e.execute(element.measure(painter));
                }
            }
            if (subMax > max) max = subMax;
        }
        return max;
    }

    private interface cordExecutor{
        Integer execute(Point point);
    }


    @Override
    public Point draw(IPainter painter, Point point) {
        Integer xPos = point.getX();
        Integer yPos = point.getY();
        for (int i = 0; i < matrix.rows(); i++) {
            Point offset = null;
            for (int j = 0; j < matrix.rows(); j++) {
                T element = matrix.getElement(i, j);
                if(element != null) {
                    element.draw(painter, new Point(xPos, yPos));
                    offset = matrix.getElement(i, j).measure(painter);
                    xPos += offset.getX();
                }
            }
            if (offset != null) yPos += offset.getY();
            xPos = point.getX();
        }
        return null;
    }
}

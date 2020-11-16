package dev.smurf.patternsdrawlab.java.matrix;

import dev.smurf.patternsdrawlab.java.drawers.Point;
import dev.smurf.patternsdrawlab.java.interfaces.DrawableMatrix;
import dev.smurf.patternsdrawlab.java.interfaces.IMatrix;
import dev.smurf.patternsdrawlab.java.interfaces.IPainter;
import dev.smurf.patternsdrawlab.java.interfaces.MatrixDecorator;

public class BorderMatrixDecorator<T> implements DrawableMatrix<T>, MatrixDecorator<T> {
    private DrawableMatrix<T> matrix;

    public BorderMatrixDecorator(DrawableMatrix<T> matrix) {
        this.matrix = matrix;
    }

    @Override
    public void setElement(Integer row, Integer col, T element) {
        matrix.setElement(row, col, element);
    }

    @Override
    public Integer rows() {
        return matrix.rows();
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
    public Point measure(IPainter painter) {
        return new Point(matrix.measure(painter).getX() + painter.lineWidth() * 2,
                matrix.measure(painter).getY() + painter.lineWidth() * 2);
    }

    @Override
    public Point draw(IPainter painter, Point point) {
        Point size = matrix.measure(painter);
        painter.addLine(size.getY(), point, false);
        matrix.draw(painter, new Point(point.getX() + painter.lineWidth(), point.getY()));
        painter.addLine(size.getY(), new Point(point.getX() + size.getX() + painter.lineWidth()*2, point.getY()), false);
        return new Point(point.getX() + size.getX(), point.getY() + size.getY());
    }

    @Override
    public IMatrix<T> getSuper() {
        return matrix;
    }
}

package dev.smurf.patternsdrawlab.java.drawers;


import dev.smurf.patternsdrawlab.java.interfaces.IDrawable;
import dev.smurf.patternsdrawlab.java.interfaces.IPainter;
import dev.smurf.patternsdrawlab.java.interfaces.IVector;

public class BasicVectorDrawer<T> implements IDrawable {
    private IVector<T> vector;

    public BasicVectorDrawer(IVector<T> vector) {
        this.vector = vector;
    }

    @Override
    public Point draw(IPainter painter, Point point) {
        Point cursorPosition = point.copy();
        for (int i = 0; i < vector.size(); i++) {
            painter.addString(vector.getElement(i).toString() + '\n', cursorPosition);
            cursorPosition = new Point(
                    cursorPosition.getX(),
                    cursorPosition.getY() + painter.measureString(vector.getElement(i).toString()).getY()
            );
        }
        return new Point(
                cursorPosition.getX() + painter.measureString(vector.getElement(vector.size() - 1).toString()).getX(),
                cursorPosition.getY()
        );
    }

    @Override
    public Point measure(IPainter painter) {
        Point cursorPosition = new Point(0, 0);
        for (int i = 0; i < vector.size(); i++) {
            cursorPosition = new Point(
                    cursorPosition.getX(),
                    cursorPosition.getY() + painter.measureString(vector.getElement(i).toString()).getY()
            );
        }
        return new Point(
                cursorPosition.getX() + painter.measureString(vector.getElement(vector.size() - 1).toString()).getX(),
                cursorPosition.getY()
        );
    }
}

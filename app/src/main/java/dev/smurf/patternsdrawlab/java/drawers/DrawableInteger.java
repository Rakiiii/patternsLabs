package dev.smurf.patternsdrawlab.java.drawers;

import java.util.Objects;

import dev.smurf.patternsdrawlab.java.drawers.Point;
import dev.smurf.patternsdrawlab.java.interfaces.IDrawable;
import dev.smurf.patternsdrawlab.java.interfaces.IPainter;

public class DrawableInteger implements IDrawable {
    private final Integer number;

    public DrawableInteger(Integer num) {
        number = num;
    }

    @Override
    public Point draw(IPainter painter, Point point) {
        painter.addString(number.toString(), point);
        return painter.measureString(number.toString());
    }

    @Override
    public Point measure(IPainter painter) {
        return painter.measureString(number.toString());
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DrawableInteger)) return false;
        DrawableInteger that = (DrawableInteger) o;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}

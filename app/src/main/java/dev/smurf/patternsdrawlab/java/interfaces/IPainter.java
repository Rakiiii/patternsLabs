package dev.smurf.patternsdrawlab.java.interfaces;


import dev.smurf.patternsdrawlab.java.drawers.Point;

public interface IPainter {
    Point addLine(Integer length, Point point, Boolean isHorizontal);

    Point addString(String string, Point point);

    Point measureString(String string);

    Integer lineWidth();
}

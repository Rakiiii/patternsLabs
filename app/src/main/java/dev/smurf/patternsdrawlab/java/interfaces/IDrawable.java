package dev.smurf.patternsdrawlab.java.interfaces;

import dev.smurf.patternsdrawlab.java.drawers.Point;

public interface IDrawable {
    Point measure(IPainter painter);

    Point draw(IPainter painter, Point point);
}

package dev.smurf.patternsdrawlab.java.drawers;

public class Point {
    private Integer x;
    private Integer y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Point copy() {
        return new Point(x, y);
    }
}

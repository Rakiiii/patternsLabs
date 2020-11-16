package dev.smurf.patternsdrawlab.java.drawers;


import dev.smurf.patternsdrawlab.java.interfaces.IPainter;

public class ConsolePainter implements IPainter {

    @Override
    public Integer lineWidth() {
        return 1;
    }

    @Override
    public Point addString(String string, Point point) {
        String[] lines = string.split("\\r?\\n");
        Integer maxLineLength = 0;
        for (int i = 0; i < lines.length; i++) {
            moveCursorToPoint(new Point(point.getX(), point.getY() + i));
            if (maxLineLength < lines[i].length()) maxLineLength = lines[i].length();
            System.out.print(lines[i]);
        }
        return new Point(point.getX() + maxLineLength, point.getY() + lines.length);
    }

    @Override
    public Point addLine(Integer length, Point point, Boolean isHorizontal) {
        moveCursorToPoint(point);
        if (isHorizontal) {
            drawHorizontalLine(length);
            return new Point(point.getX() + length, point.getY());
        } else {
            drawVerticalLine(length);
            return new Point(point.getX(), point.getY() + length);
        }
    }

    @Override
    public Point measureString(String string) {
        return new Point(string.length(), string.split("\\r?\\n").length);
    }

    private void drawHorizontalLine(Integer length) {
        System.out.print(constructString(length, horizontalLine));
    }

    private void drawVerticalLine(Integer length) {
        System.out.print(constructString(length, verticalLine));
    }

    private String constructString(Integer length, char symbol) {
        StringBuilder str = new StringBuilder();
        for (int i = 0 ; i < length; i ++){
            str.append(symbol);
        }
        return str.toString();
    }

    private void moveCursorToPoint(Point point) {
        if (point != null) {
            System.out.print(String.format("%c[%d;%df", escCode, point.getX(), point.getY()));
        }
    }

    private static final char escCode = 0x1B;
    private static final char horizontalLine = 0x2501;
    private static final char verticalLine = 0x2502;
}

package dev.smurf.patternsdrawlab.java.drawers;

import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.smurf.patternsdrawlab.java.interfaces.IPainter;

public class TextViewPainter implements IPainter {

    private TextView tv;

    public TextViewPainter(TextView tv) {
        this.tv = tv;
    }

    @Override
    public Integer lineWidth() {
        return 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Point addString(String string, Point point) {
        List<String> str = new ArrayList<>(Arrays.asList(tv.getText().toString().split("\\r?\\n")));
        while (str.size() <= point.getY()) {
            str.add("");
        }
        str.set(point.getY(), str.get(point.getY()) + string);
        final StringBuilder builder = new StringBuilder();
        str.forEach(e -> {
            if (!e.contains("\n")) builder.append(e + '\n');
            else builder.append(e);
        });

        tv.setText(builder.toString());
        return new Point(point.getX() + string.length(), point.getY() + string.length());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Point addLine(Integer length, Point point, Boolean isHorizontal) {
        //moveCursorToPoint(point);
        if (isHorizontal) {
            drawHorizontalLine(length);
            return new Point(point.getX() + length, point.getY());
        } else {
            for (int i = 0; i < length; i++) {
                this.addString(String.valueOf(verticalLine), new Point(point.getX(), point.getY() + i));
            }
            //drawVerticalLine(length);
            return new Point(point.getX(), point.getY() + length);
        }
    }

    @Override
    public Point measureString(String string) {
        return new Point(string.length(), string.split("\\r?\\n").length);
    }

    private void drawHorizontalLine(Integer length) {
        tv.setText(constructString(length, horizontalLine, false));
    }

    private void drawVerticalLine(Integer length) {

        tv.setText(constructString(length, verticalLine, true));
    }

    private String constructString(Integer length, char symbol, Boolean mustBeNextLine) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(symbol);
            if (mustBeNextLine) {
                str.append('\n');
            }
        }
        return str.toString();
    }

    private void moveCursorToPoint(Point point) {
        if (point != null) {
            tv.setText(String.format("%c[%d;%df", escCode, point.getX(), point.getY()));
        }
    }

    private static final char escCode = 0x1B;
    private static final char horizontalLine = 0x2501;
    private static final char verticalLine = 0x2502;
}

package dev.smurf.patternsdrawlab.java.drawers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.ImageView;

import dev.smurf.patternsdrawlab.java.interfaces.IPainter;

public class GuiPainter implements IPainter {
    private Paint paint = new Paint(Color.BLACK);
    private ImageView imageView;
    private Bitmap bitmap;
    private Canvas cv;

    @Override
    public Integer lineWidth() {
        return Math.round(paint.getStrokeWidth());
    }

    public GuiPainter(ImageView iv, Bitmap bitm) {
        this.imageView = iv;
        this.bitmap = bitm;
        cv = new Canvas(bitm);
        paint.setTextSize(100.0f);
        paint.setStrokeWidth(8);
    }

    @Override
    public Point measureString(String string) {
        Rect rect = new Rect();
        paint.getTextBounds(string, 0, string.length(), rect);
        return new Point(rect.width(), rect.height());
    }

    @Override
    public Point addString(String string, Point point) {
        cv.drawText(string, point.getX(), point.getY() + measureString(string).getY(), paint);
        drawOnImageView();
        Rect rect = new Rect();
        paint.getTextBounds(string, 0, string.length(), rect);
        return new Point(point.getX() + rect.width(), point.getY() + rect.height());
    }

    @Override
    public Point addLine(Integer length, Point point, Boolean isHorizontal) {
        if (isHorizontal) {
            drawHorizontalLine(length, point);
            return new Point(point.getX() + length, point.getY());
        } else {
            drawVerticalLine(length, point);
            return new Point(point.getX(), point.getY() + length);
        }
    }

    private void drawHorizontalLine(Integer length, Point point) {
        cv.drawLine(point.getX(), point.getY(), point.getX() + length, point.getY(), paint);
        drawOnImageView();
    }

    private void drawVerticalLine(Integer length, Point point) {
        cv.drawLine(point.getX(), point.getY(), point.getX(), point.getY() + length, paint);
        drawOnImageView();
    }

    private void drawOnImageView() {
        imageView.setImageBitmap(bitmap);
    }
}

package cs.vsu.ru.group6.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends AbstractShape {

    private double width, height;
    public Triangle(double x0, double y0, double x2, double y2){
        this(x0, y0, x2, y2, null, null);
    }

    public Triangle(double x0, double y0, double x1, double y1, Color fillColor, Color borderColor) {
        super(x0, y0, x1, y1, fillColor, borderColor);
    }

    @Override
    public void draw(GraphicsContext graphicsContext2D) {
        double x0 = getX0();
        double y0 = getY0();
        double x1 = getX1();
        double y1 = getY1();

        double x = 1. / 2 * (x1 + x0);
        double y = y0;

        Color fillColor = getFillColor();
        if(fillColor != null) {
            graphicsContext2D.setFill(fillColor);
            graphicsContext2D.fillPolygon(
                    new double[] {x0, x, x1},
                    new double[] {y1, y, y1},
                    3
            );
        }
        Color borderColor = getBorderColor();
        if(fillColor != null) {
            graphicsContext2D.setStroke(borderColor);
            graphicsContext2D.strokePolygon(
                    new double[] {x0, x, x1},
                    new double[] {y1, y, y1},
                    3
            );
        }

        /*graphicsContext2D.beginPath();
        graphicsContext2D.moveTo(x0, y1);
        graphicsContext2D.lineTo(x1, y1);
        graphicsContext2D.moveTo(x1, y1);
        graphicsContext2D.lineTo(x2, y2);
        graphicsContext2D.moveTo(x2, y2);
        graphicsContext2D.lineTo(x0, y1);
        graphicsContext2D.closePath();
         */
    }

    @Override
    public void resize(double width, double height) {

    }

    @Override
    public boolean isInsideBounds(double x, double y) {
        return false;
    }
    @Override
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
    }
}

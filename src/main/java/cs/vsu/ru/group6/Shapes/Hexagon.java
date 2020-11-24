package cs.vsu.ru.group6.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Hexagon extends AbstractShape{

    private double width, height;

    public Hexagon(double x0, double y0, double x1, double y1) {
        this(x0, y0, x1, y1, null, null);
    }

    public Hexagon(double x0, double y0, double x1, double y1, Color fillColor, Color borderColor) {
        super(x0, y0, x1, y1, fillColor, borderColor);
    }

    @Override
    public void draw(GraphicsContext graphicsContext2D) {
        double x0 = getX0();
        double y0 = getY0();
        double x1 = getX1();
        double y1 = getY1();

        double x2 = 1. / 2 * (x0 + x1);
        double y2 = 1. / 2 * (y0 + y1);
        double t1 = y2 - 1. / 2 * (y1 - y2);
        double t2 = 1. / 2 * (y1 - y2) + y2;
        Color fillColor = getFillColor();
        if(fillColor != null) {
            graphicsContext2D.setFill(fillColor);
            graphicsContext2D.fillPolygon(
                    new double[] {x2, x1, x1, x2, x0, x0},
                    new double[] {y0, t1, t2, y1, t2, t1},
                    6
            );
        }
        Color borderColor = getBorderColor();
        if(fillColor != null) {
            graphicsContext2D.setStroke(borderColor);
            graphicsContext2D.strokePolygon(
                    new double[] {x2, x1, x1, x2, x0, x0},
                    new double[] {y0, t1, t2, y1, t2, t1},
                    6
            );
        }
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

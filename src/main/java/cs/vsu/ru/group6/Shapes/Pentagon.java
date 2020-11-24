package cs.vsu.ru.group6.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pentagon extends AbstractShape {
    private double width;

    private double height;

    public Pentagon(double x0, double y0, double x1, double y1) {
        this(x0, y0, x1, y1, null, null);
    }

    public Pentagon(double x0, double y0, double x1, double y1, Color fillColor, Color borderColor) {
        super(x0, y0, x1, y1, fillColor, borderColor);
    }

    @Override
    public void draw(GraphicsContext graphicsContext2D) {
        double x0 = getX0();
        double y0 = getY0();
        double x1 = getX1();
        double y1 = getY1();

        double temp;
        if(x1 < x0 && y1 < y0){
            temp = x0; x0 = x1; x1 = temp;
            temp = y0; y0 = y1; y1 = temp;
        }else if(y1 < y0){
            temp = y0; y0 = y1; y1 = temp;
        }else if(x1 < x0){
            temp = x0; x0 = x1; x1 = temp;
        }

        double xmid = 1. / 2 * (x0 + x1);
        double ymid = 1. / 2 * (y0 + y1);
        double y2 = ymid - .25 * (ymid - y0);
        double t1 = .6 * (x1 - xmid) + xmid;
        double t2 = xmid - .6 * (x1 - xmid);
        Color fillColor = getFillColor();
        if(fillColor != null) {
            graphicsContext2D.setFill(fillColor);
            graphicsContext2D.fillPolygon(
                    new double[] {xmid, x1, t1, t2, x0},
                    new double[] {y0, y2, y1, y1, y2},
                    5
            );
        }
        Color borderColor = getBorderColor();
        if(fillColor != null) {
            graphicsContext2D.setStroke(borderColor);
            graphicsContext2D.strokePolygon(
                    new double[] {xmid, x1, t1, t2, x0},
                    new double[] {y0, y2, y1, y1, y2},
                    5
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

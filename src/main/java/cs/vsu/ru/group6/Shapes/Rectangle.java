package cs.vsu.ru.group6.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.StrictMath.abs;

public class Rectangle extends AbstractShape {

    private double width, height;
    private double x1, y1;


    public Rectangle( double x, double y, double width, double height) {
        this(x, y, width, height, null, null);
    }

    /*public Rectangle(double x, double y, double width, double height, Color fillColor, Color borderColor) {
        super(x, y, fillColor, borderColor);
        this.width = width;
        this.height = height;
    }

     */
    public Rectangle(double x0, double y0, double x1, double y1, Color fillColor, Color borderColor) {
        super(x0, y0, x1, y1, fillColor, borderColor);
        this.width = abs(x1 - x0);
        this.height = abs(y1 - y0);
    }

    @Override
    public void draw(GraphicsContext ctx) {
        double x0 = getX0();
        double x1 = getX1();
        double y0 = getY0();
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

        Color borderColor = getBorderColor();
        if(borderColor != null) {
            ctx.setStroke(borderColor);
            ctx.strokeRect(x0, y0, width, height);
        }
        Color fillColor = getFillColor();
        if(fillColor != null) {
            ctx.setFill(fillColor);
            ctx.fillRect(x0, y0, width, height);
        }
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void resize(double width, double height) {
        setHeight(height);
        setWidth(width);
    }

    @Override
    public boolean isInsideBounds(double x, double y) {
        return false;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }
}

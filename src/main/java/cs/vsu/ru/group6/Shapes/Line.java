package cs.vsu.ru.group6.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends AbstractShape {

    public Line( double x0, double y0, double x1, double y1){
        this(x0, y0, x1, y1, null);
    }

    public Line(double x0, double y0, double x1, double y1, Color fillColor) {
        super(x0, y0, x1, y1, fillColor, null);
    }

    @Override
    public void draw(GraphicsContext ctx) {
        Color borderColor = getBorderColor();
        if(borderColor != null)
            ctx.setStroke(borderColor);

        ctx.beginPath();
        ctx.moveTo(getX0(), getY0());
        ctx.lineTo(getX1(), getY1());
        ctx.stroke();
        ctx.closePath();
    }

    @Override
    public void resize(double xn, double yn) {
        setX0(xn);
        setY0(yn);
    }

    @Override
    public boolean isInsideBounds(double x, double y) {
        return y == (getY1() - getY0()) / (getX1() - getX0()) * ( x - getX0()) + getY0();
    }

    @Override
    public void setWidth(double w) {

    }

    @Override
    public void setHeight(double h) {

    }
}

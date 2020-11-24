package cs.vsu.ru.group6.Shapes;


import cs.vsu.ru.group6.common.IEditor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

import static java.lang.StrictMath.sqrt;

public class Circle extends AbstractShape{
    private double radius;
    private double width;
    private double height;
    private double diameter;

    public Circle(double x0, double y0, double x1, double y1) {
        this(x0, y0, x1, y1, null, null);
    }

    public Circle(double x0, double y0, double x1, double y1, Color fillColor, Color borderColor) {
        super(x0, y0, x1, y1, fillColor, borderColor);
        double r = sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
        this.setRadius(r);
    }

    public void draw(GraphicsContext ctx) {
        Color borderColor = getBorderColor();
        double x0 = getX0() - this.radius;
        double y0 = getY0() - this.radius;
        if(borderColor != null) {
            ctx.setStroke(borderColor);
            ctx.strokeOval(x0, y0, getWidth(), getHeight());
        }
        Color fillColor = getFillColor();
        if(fillColor != null) {
            ctx.setFill(fillColor);
            ctx.fillOval(x0, y0, getWidth(), getHeight());
        }
    }

    @Override
    public void resize(double width, double height) {
        resizeCircle(width);
    }

    private void resizeCircle(double newRadius) {
        setRadius(newRadius);
    }

    @Override
    public double getHeight() {
        return this.diameter;
    }

    @Override
    public double getWidth() {
        return this.diameter;
    }

    @Override
    public boolean isInsideBounds(double x, double y) {
        return (x - getX0()) * (x - getX0()) + (y - getY0()) * (y - getY0()) == radius * radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
        this.diameter = this.radius + this.radius;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
    }

    private class EditorCircle implements IEditor {

        private final int PARAM_COUNTER = 5;
        private String header;
        private Map<String, Object> otherParams = new HashMap<>(PARAM_COUNTER);
        @Override
        public void collectInfo() {
            this.header = "Окружность";
            this.otherParams.put("Координата по x", Circle.this.getX0());
            this.otherParams.put("Координата по y", Circle.this.getY0());
            this.otherParams.put("Радиус", Circle.this.getRadius());
            this.otherParams.put("Цвет границы", Circle.this.getBorderColor().hashCode());
            this.otherParams.put("Цвет заливки", Circle.this.getFillColor().hashCode());
            //this.otherParams.put("Толщина линии", Circle.this.getThickness());
        }

        @Override
        public void apply(Object[] newArgs) {
            if(newArgs.length > PARAM_COUNTER)
                throw new IllegalArgumentException();

            move((Double) newArgs[0], (Double) newArgs[1]);
            resizeCircle((Double) newArgs[2]);
            Circle.this.setBorderColor((Color) newArgs[3]);
            Circle.this.setFillColor((Color) newArgs[4]);
            //Circle.this.setThickness((Integer) newArgs[5]);
            //TODO дописать применение параметров из окна (?)
        }
    }
}

package cs.vsu.ru.group6.Shapes;

import cs.vsu.ru.group6.common.IEditor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

import static java.lang.StrictMath.abs;

public class Ellipse extends AbstractShape {
    private double radiusX, radiusY;
    private double width, height;
    public Ellipse(double x, double y, double radiusX, double radiusY){
        this(x, y, radiusX, radiusY, null, null);
    }

    /*public Ellipse(double x, double y, double radiusX, double radiusY, Color fillColor, Color borderColor) {
        super(x, y, fillColor, borderColor);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }
     */

    public Ellipse(double x0, double y0, double x1, double y1, Color fillColor, Color borderColor) {
        super(x0, y0, x1, y1, fillColor, borderColor);
        /*this.radiusX = abs((y1 - y0) * (y1 - y0) / (x1 - x0) + (x1 - x0));
        this.radiusY = abs(y1 / sqrt(1 - (x1 / radiusX) * (x1 / radiusX)));
         */
        /*this.radiusX = abs( ((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0)) / (x1 - x0));
        this.radiusY = abs(radiusX * y1 / sqrt(radiusX * radiusX - x1 * x1));

         */
        this.radiusX = abs(x1 - x0);
        this.radiusY = abs(y1 - y0);
    }

    @Override
    public void draw(GraphicsContext ctx) {
        double x0 = getX0() - this.radiusX;
        double y0 = getY0() - this.radiusY;
        Color borderColor = getBorderColor();
        if (borderColor != null) {
            ctx.setStroke(borderColor);
            ctx.strokeOval(x0, y0, getWidth(), getHeight());
        }
        Color fillColor = getFillColor();
        if (fillColor != null) {
            ctx.setFill(fillColor);
            ctx.fillOval(x0, y0, getWidth(), getHeight());
        }
    }

    @Override
    public void resize(double width, double height) {
        setRadiusX(width);
        setRadiusY(height);
    }

    @Override
    public boolean isInsideBounds(double x, double y) {
        return x * x / radiusX * radiusX + y * y / radiusY * radiusY <= 1 ;
    }

    @Override
    public double getWidth() {
        return this.radiusX + this.radiusX;
    }

    private double getRadiusX() {
        return this.radiusX;
    }

    public void setRadiusX(double radiusX) {
        this.radiusX = radiusX;
    }

    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }

    private double getRadiusY() {
        return this.radiusY;
    }

    @Override
    public double getHeight() {
        return this.radiusY + this.radiusY;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
    }
    private class EditorEllipse implements IEditor {

        private final int PARAM_COUNTER = 6;
        private String header;
        private Map<String, Object> otherParams = new HashMap<>(PARAM_COUNTER);
        @Override
        public void collectInfo() {
            this.header = "Эллипс";
            this.otherParams.put("Координата по x", Ellipse.this.getX0());
            this.otherParams.put("Координата по y", Ellipse.this.getY0());
            this.otherParams.put("Первая полуось", Ellipse.this.getRadiusX());
            this.otherParams.put("Вторая полуось", Ellipse.this.getRadiusY());
            this.otherParams.put("Цвет границы", Ellipse.this.getBorderColor().hashCode());
            this.otherParams.put("Цвет заливки", Ellipse.this.getFillColor().hashCode());
            //this.otherParams.put("Толщина линии", Circle.this.getThickness());
        }

        @Override
        public void apply(Object[] newArgs) {
            if(newArgs.length > PARAM_COUNTER)
                throw new IllegalArgumentException();

            move((Double) newArgs[0], (Double) newArgs[1]);
            resize((Double) newArgs[2], (Double) newArgs[3]);
            Ellipse.this.setBorderColor((Color) newArgs[4]);
            Ellipse.this.setFillColor((Color) newArgs[5]);
            //Circle.this.setThickness((Integer) newArgs[5]);
            //TODO дописать применение параметров из окна (?)
        }
    }
}

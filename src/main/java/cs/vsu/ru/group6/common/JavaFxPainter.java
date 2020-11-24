package cs.vsu.ru.group6.common;

import cs.vsu.ru.group6.Shapes.AbstractShape;
import cs.vsu.ru.group6.Shapes.ShapeFactory;
import cs.vsu.ru.group6.Shapes.ShapesEnum;
import javafx.scene.paint.Color;

public class JavaFxPainter{
    private Color color;
    private Canvas canvas;
    private ShapesEnum shapeType;
    private ShapeFactory factory;
    private EventParam param;
    private double x0, y0;
    private double x1, y1;
    private Color fillColor, borderColor;
    private DBManager dbManager;
    private Params p = new Params();

    public JavaFxPainter(Canvas canvas, DBManager db) {
        factory = new ShapeFactory();
        this.canvas = canvas;
        this.dbManager = db;
    }

    public void paint() {
        AbstractShape shape = canvas.addShape(factory.selectShape(p));
        if(shape != null)
            dbManager.add(shape);

       /* canvas.addShape(factory.selectShape(new Params(
                getShapeType(),
                getX0(), getY0(),
                getX1(), getY1(),
                getFillColor(), getBorderColor()
        )));

        */
    }

    public void setType(EventType type, double x, double y) {
        param = new EventParam(type, x, y);
    }

    public void selectShape(ShapesEnum shape) {
        p.setShapeType(shape);
    }
    public Color getFillColor() {
        return p.getFillColor();
    }

    public Color getBorderColor() {
        return p.getBorderColor();
    }

    public void setFillColor(Color fillColor) {
        p.setFillColor(fillColor);
    }

    public void setBorderColor(Color borderColor) {
        p.setBorderColor(borderColor);
    }

    public ShapesEnum getShapeType() {
        return p.getShapeType();
    }

    public double getX0() {
        return p.getX0();
    }

    public double getY0() {
        return p.getY0();
    }

    public double getX1() {
        return p.getX1();
    }

    public double getY1() {
        return p.getY1();
    }

    public void setStartPoint(double x, double y) {
        p.setX0(x);
        p.setY0(y);
    }

    public void setEndPoint(double x, double y) {
        p.setX1(x);
        p.setY1(y);
    }

    class EventParam{
        private EventType type;
        private double x, y;
        public EventParam(EventType type, double x, double y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }

    public class Params{
        private ShapesEnum shapeType;
        private double x0, y0;
        private double x1, y1;
        private Color fillColor, borderColor;

        public Params(ShapesEnum shape, double x0, double y0, double x1, double y1, Color fillColor, Color borderColor) {
            this.shapeType = shape;
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
            this.fillColor = fillColor;
            this.borderColor = borderColor;
        }

        public Params() {

        }

        public ShapesEnum getShapeType() {
            return shapeType;
        }

        public void setShapeType(ShapesEnum shapeType) {
            this.shapeType = shapeType;
        }

        public double getX0() {
            return x0;
        }

        public void setX0(double x0) {
            this.x0 = x0;
        }

        public double getY0() {
            return y0;
        }

        public void setY0(double y0) {
            this.y0 = y0;
        }

        public double getX1() {
            return x1;
        }

        public void setX1(double x1) {
            this.x1 = x1;
        }

        public double getY1() {
            return y1;
        }

        public void setY1(double y1) {
            this.y1 = y1;
        }

        public Color getFillColor() {
            return fillColor;
        }

        public void setFillColor(Color fillColor) {
            this.fillColor = fillColor;
        }

        public Color getBorderColor() {
            return borderColor;
        }

        public void setBorderColor(Color borderColor) {
            this.borderColor = borderColor;
        }
    }
}

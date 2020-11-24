package cs.vsu.ru.group6.Shapes;

import cs.vsu.ru.group6.common.Builder;
import cs.vsu.ru.group6.common.JavaFxPainter;


public class ShapeFactory {
    private Builder builder;

    public ShapeFactory() {

    }

    public AbstractShape selectShape(JavaFxPainter.Params param){
        if(param.getShapeType() == null)
            return null;
        double x0 = param.getX0();
        double x1 = param.getX1();
        double y0 = param.getY0();
        double y1 = param.getY1();

        switch (param.getShapeType()){
            case LINE:
                return new Line(x0, y0,
                        x1, y1,
                        param.getFillColor()
                );
            case CIRCLE:
                return new Circle(x0, y0,
                        x1, y1,
                        param.getFillColor(), param.getBorderColor());
            case ELLIPSE:
                return new Ellipse(x0, y0,
                        x1, y1,
                        param.getFillColor(), param.getBorderColor());
            case RECTANGLE:
                return new Rectangle(x0, y0,
                        x1, y1,
                        param.getFillColor(), param.getBorderColor());
            case TRIANGLE:
                return new Triangle(x0, y0,
                        x1, y1,
                        param.getFillColor(), param.getBorderColor());
            case RHOMBUS:
                return new Rhombus(x0, y0,
                        x1, y1,
                        param.getFillColor(), param.getBorderColor());
            case HEXAGON:
                return new Hexagon(x0, y0,
                        x1, y1,
                        param.getFillColor(), param.getBorderColor());
            case PENTAGON:
                return new Pentagon(x0, y0,
                        x1, y1,
                        param.getFillColor(), param.getBorderColor());
            case STAR:
                return new Star(x0, y0,
                        x1, y1,
                        param.getFillColor(), param.getBorderColor());
            default:
                break;
        }
        return null;
    }
}

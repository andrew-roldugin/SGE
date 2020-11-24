package cs.vsu.ru.group6.Shapes;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Star extends AbstractShape {
    private double width, height;

    public Star(double x0, double y0, double x1, double y1) {
        this(x0, y0, x1, y1, null, null);
    }

    public Star(double x0, double y0, double x1, double y1, Color fillColor, Color borderColor) {
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

        //линия из точки 1 в точку 5
        org.apache.commons.math3.geometry.euclidean.twod.Line line15 = new org.apache.commons.math3.geometry.euclidean.twod.Line(new Vector2D(xmid, y0), new Vector2D(t1, y1), 0);
        //линия из точки 3 в точку 9
        org.apache.commons.math3.geometry.euclidean.twod.Line line39 = new org.apache.commons.math3.geometry.euclidean.twod.Line(new Vector2D(x1, y2), new Vector2D(x0, y2), 0);
        //ищем точку 2
        Vector2D point2 = line15.intersection(line39);

        //линия из точки 3 в точку 7
        org.apache.commons.math3.geometry.euclidean.twod.Line line37 = new org.apache.commons.math3.geometry.euclidean.twod.Line(new Vector2D(x1, y2), new Vector2D(t2, y1), 0);
        //ищем точку 4
        Vector2D point4 = line15.intersection(line37);

        //линия из точки 5 в точку 9
        org.apache.commons.math3.geometry.euclidean.twod.Line line59 = new org.apache.commons.math3.geometry.euclidean.twod.Line(new Vector2D(t1, y1), new Vector2D(x0, y2), 0);
        //ищем точку 6
        Vector2D point6 = line59.intersection(line37);


        //линия из точки 1 в точку 7
        org.apache.commons.math3.geometry.euclidean.twod.Line line17 = new org.apache.commons.math3.geometry.euclidean.twod.Line(new Vector2D(xmid, y0), new Vector2D(t2, y1), 0);
        //ищем точку 8
        Vector2D point8 = line59.intersection(line17);

        //ищем точку 10
        Vector2D point10 = line39.intersection(line17);

        if(fillColor != null) {
            graphicsContext2D.setFill(fillColor);
            graphicsContext2D.fillPolygon(
                    new double[] {xmid, point2.getX(), x1, point4.getX(), t1, point6.getX(), t2, point8.getX(), x0, point10.getX()},
                    new double[] {y0, point2.getY(), y2, point4.getY(), y1, point6.getY(), y1, point8.getY(), y2, point10.getY()},
                    10
            );
        }
        Color borderColor = getBorderColor();
        if(fillColor != null) {
            graphicsContext2D.setStroke(borderColor);
            graphicsContext2D.strokePolygon(
                    new double[] {xmid, point2.getX(), x1, point4.getX(), t1, point6.getX(), t2, point8.getX(), x0, point10.getX()},
                    new double[] {y0, point2.getY(), y2, point4.getY(), y1, point6.getY(), y1, point8.getY(), y2, point10.getY()},
                    10
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

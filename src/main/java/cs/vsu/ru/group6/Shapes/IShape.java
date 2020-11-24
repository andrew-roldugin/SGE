package cs.vsu.ru.group6.Shapes;

import javafx.scene.paint.Color;

public interface IShape {
    void select();
    void unselect();
    void resize(double width, double height);
    double getWidth();
    double getHeight();
    double getX0();
    void setX0(double x);
    double getY0();
    void setY0(double y0);
    double getX1();
    void setX1(double x);
    double getY1();
    void setY1(double y0);
    Color getBorderColor();
    void setBorderColor(Color color);
    Color getFillColor();
    void setFillColor(Color color);
    boolean isSelected();
    boolean isInsideBounds(double x, double y);
}

package cs.vsu.ru.group6.common;

import javafx.scene.paint.Color;

public class Builder{
    private Color fillColor, strokeColor;
    private double width, height;
    private double radius;
    private double x1, y1;
    private double alpha;
    private double x2, y2;
    private double radiusX, radiusY;

    public double getRadiusX() {
        return this.radiusX;
    }

    public Builder setRadiusX(double radiusX) {
        this.radiusX = radiusX;
        return this;
    }

    public double getRadiusY() {
        return radiusY;
    }

    public Builder setRadiusY(double radiusY) {
        this.radiusY = radiusY;
        return this;
    }

    public double getRadius() {
        return radius;
    }

    public Builder setRadius(double radius) {
        this.radius = radius;
        return this;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getAlpha() {
        return alpha;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public Builder setFillColor(Color color) {
        this.fillColor = color;
        return this;
    }

    public Builder setBorderColor(Color color) {
        this.strokeColor = color;
        return this;
    }

    public Builder setWidth(double width) {
        this.width = width;
        return this;
    }


    public Builder setHeight(double height) {
        this.height = height;
        return this;
    }

    public Builder setX(double x1) {
        this.x1 = x1;
        return this;
    }

    public Builder setY(double y1) {
        this.y1 = y1;
        return this;
    }

    public Builder setAngle(double alpha) {
        this.alpha = alpha;
        return this;
    }

    public Builder setXEnd(double x2) {
        this.x2 = x2;
        return this;
    }

    public Builder setYEnd(double y2) {
        this.y2 = y2;
        return this;
    }
}

/*public interface Builder {
    JavaFxPainter.Builder setFillColor(Color color);
    JavaFxPainter.Builder setBorderColor(Color color);
    JavaFxPainter.Builder setWidth(double width);
    JavaFxPainter.Builder setHeight(double height);
    JavaFxPainter.Builder setX(double x1);
    JavaFxPainter.Builder setY(double y1);
    JavaFxPainter.Builder setAngle(double alpha);
    JavaFxPainter.Builder setXEnd(double x2);
    JavaFxPainter.Builder setYEnd(double y2);

    double getX();
}
  */

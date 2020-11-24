package cs.vsu.ru.group6.common;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ResizableCanvas extends javafx.scene.canvas.Canvas {

    private final SimpleDoubleProperty width = new SimpleDoubleProperty();
    private final SimpleDoubleProperty height = new SimpleDoubleProperty();

    public ResizableCanvas(int width, int height) {
        this.resize(width, height);
    }

    /*
    @Override
    public double minHeight(double width) {
        return 0;
    }

   @Override
    public double maxHeight(double width) {
        return 1000;
    }

    @Override
    public double minWidth(double height) {
        return 0;
    }

    @Override
    public double maxWidth(double height) {
        return 10000;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    */

    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
        this.width.set(width);
        this.height.set(height);
    }

    public StringBinding getSizeProperty() {
        return new StringBinding() {
            {
                super.bind(width, height);
            }

            @Override
            protected String computeValue() {
                return width.intValue() + "x" + height.intValue();
            }
        };
    }


    public void repaint() {
        GraphicsContext g = this.getGraphicsContext2D();
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}

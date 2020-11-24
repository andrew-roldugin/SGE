package cs.vsu.ru.group6.Shapes;

import cs.vsu.ru.group6.common.*;
import javafx.scene.paint.Color;

import java.util.UUID;

public abstract class AbstractShape implements IShape, Drawable,
        Storeable,
        SMCMouseActionListener, DMCMouseActionListener {


    private UUID id;
    private double x0, y0;
    private double x1, y1;
    private boolean selected = false;
    //private int thickness = 1;
    private Color fillColor = Color.WHITE;
    private Color borderColor = Color.BLACK;
    private AbstractShape target;
    private EventManager eventManager;

    public AbstractShape(double x0, double y0, double x1, double y1, Color fillColor, Color borderColor) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        if (fillColor != null)
            this.fillColor = fillColor;
        if (borderColor != null)
            this.borderColor = borderColor;
        eventManager = new EventManager(EventType.SINGLE_CLICK, EventType.DOUBLE_CLICK);
    }

    public AbstractShape() {
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        if (fillColor != null)
            this.fillColor = fillColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        if (borderColor != null)
            this.borderColor = borderColor;
    }

    @Override
    public void select() {
        selected = true;
    }

    @Override
    public void unselect() {
        selected = false;
    }

    @Override
    public void move(double nx, double ny) {
        if (nx != getX0() || ny != getY0()) {
            //вызов слушателя для перерисовки
        }
        setX0(nx);
        setY0(ny);
    }

    @Override
    public double getWidth() {
        return 0;
    }
    public abstract void setWidth(double w);

    @Override
    public double getHeight() {
        return 0;
    }

    public abstract void setHeight(double h);

    @Override
    public double getX0() {
        return x0;
    }

    @Override
    public void setX0(double x) {
        this.x0 = x;
    }

    @Override
    public double getX1() {
        return x1;
    }

    @Override
    public void setX1(double x1) {
        this.x1 = x1;
    }

    @Override
    public double getY0() {
        return y0;
    }

    @Override
    public void setY0(double y0) {
        this.y0 = y0;
    }

    @Override
    public double getY1() {
        return y1;
    }

    @Override
    public void setY1(double y1) {
        this.y1 = y1;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    public SMCMouseActionListener getSMCListener() {
        return this;
    }

    public DMCMouseActionListener getDMCListener() {
        return this;
    }

    public void addListener(ActionListener listener) {

    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public void editShape() {
        //todo показать всплывающее окно для редактирования
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractShape that = (AbstractShape) o;

        if (Double.compare(that.x0, x0) != 0) return false;
        if (Double.compare(that.y0, y0) != 0) return false;
        if (Double.compare(that.x1, x1) != 0) return false;
        if (Double.compare(that.y1, y1) != 0) return false;
        if (!id.equals(that.id)) return false;
        if (fillColor != null ? !fillColor.equals(that.fillColor) : that.fillColor != null) return false;
        return borderColor != null ? borderColor.equals(that.borderColor) : that.borderColor == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        temp = Double.doubleToLongBits(x0);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y0);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(x1);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y1);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (fillColor != null ? fillColor.hashCode() : 0);
        result = 31 * result + (borderColor != null ? borderColor.hashCode() : 0);
        return result;
    }
}

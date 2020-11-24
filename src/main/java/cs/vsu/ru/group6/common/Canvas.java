package cs.vsu.ru.group6.common;

import cs.vsu.ru.group6.Shapes.AbstractShape;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Canvas extends ResizableCanvas{
    private int height, width;
    private List<AbstractShape> list;
    //private PaintListener paintListener;
    //private JavaFxPainter painter;

    public Canvas(int width, int height) {
        super(width, height);
        this.height = height;
        this.width = width;
        createNewCanvas();
        /*this.canvas.setOnMouseClicked((e) -> {
            EventType type = null;
            int count = e.getClickCount();
            switch (count) {
                case 1:
                    type = EventType.SINGLE_CLICK;
                    break;
                case 2:
                    type = EventType.DOUBLE_CLICK;
                    break;
                default:
                    break;
            }
            painter.setType(type, e.getX(), e.getY());
        });

         */
    }

    public AbstractShape addShape(AbstractShape shape){
        if(shape == null)
            return null;
        UUID id = UUID.randomUUID();
        shape.setId(id);
        this.list.add(shape);
        paint(shape);
        return shape;
    }

    @Override
    public void repaint() {
        super.repaint();
        paintAll();
    }

    public void paint(AbstractShape shape){
        shape.draw(this.getContext());
    }
    public void paintAll(){
        this.list.forEach( x -> x.draw(this.getContext()));
    }

    public void createNewCanvas(){
        list = new ArrayList<>();
        super.repaint();
    }

    public void resizeCanvas(double width, double height){
        this.resize(width, height);
        repaint();
    }

    public void clear(){
        this.list.clear();
    }

    public void reset(){
        this.clear();
        this.createNewCanvas();
    }

    public void saveImage(){
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));

        File file = fileChooser.showSaveDialog(null);

        try {
            WritableImage writableImage = new WritableImage(width, height);
            this.snapshot(null, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "png", file);
        } catch (IOException e) {
            new Alert( Alert.AlertType.ERROR, "Произошла ошибка!").show();
        }
    }

    public javafx.scene.canvas.Canvas getCanvas() {
        return this;
    }

    public GraphicsContext getContext(){
        return this.getGraphicsContext2D();
    }

    public void scale(double scale) {
        this.setScaleX(scale);
        this.setScaleY(scale);
       // resizeCanvas(this.width * scale, this.height * scale);
    }

    public List<AbstractShape> getList() {
        return list;
    }
}

package cs.vsu.ru.group6.UI;

import cs.vsu.ru.group6.common.Canvas;
import cs.vsu.ru.group6.common.DBManager;
import cs.vsu.ru.group6.common.JavaFxPainter;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;

public class DrawPanel extends AnchorPane {
    private Canvas canvas;

    private int height, width;
    private JavaFxPainter painter;
    private DBManager dbManager;

    public DrawPanel(Canvas canvas, DBManager dbManager) {
        //this.width = (width);
       // this.height = (height);
        this.canvas = canvas;
        this.dbManager =  dbManager;
        initWindow();
    }

    public void initWindow(){


        /*this.boundsInLocalProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observableValue, Bounds bounds, Bounds t1) {
                canvas.resizeCanvas(t1.getWidth(), t1.getHeight());
            }
        });

         */




        this.painter = new JavaFxPainter(canvas, dbManager);


        /*canvas.addShape(new Circle(200, 300, 65, 100, Color.CYAN, Color.DARKGREY));
        canvas.addShape(new Ellipse(140, 150, 50, 10,  Color.VIOLET, Color.RED));
        canvas.addShape(new Rectangle(280, 320, 150, 80, Color.WHITE, Color.DARKSLATEBLUE));
        canvas.addShape(new Line(100, 100, 200, 200));

         */

       /*circleBtn.setOnMouseClicked((e) -> {
            painter.selectShape(ShapesEnum.CIRCLE);
        });
        rectBtn.setOnMouseClicked((e) -> {
            painter.selectShape(ShapesEnum.RECTANGLE);
        });


        */
        this.setOnMousePressed((e) -> {
            painter.setStartPoint(e.getX(), e.getY());
        });
        this.setOnMouseReleased((e) -> {
            painter.setEndPoint(e.getX(), e.getY());
            painter.paint();
        });
       // canvas.paintAll();

        this.getChildren().add(canvas);
    }

    /*public Canvas getCanvas() {
        return canvas;
    }

     */

    public JavaFxPainter getPainter() {
        return painter;
    }

    public ObservableValue<? extends String> getCanvasSizeProperty() {
        return this.canvas.getSizeProperty();
    }
}

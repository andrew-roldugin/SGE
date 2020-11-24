package cs.vsu.ru.group6.UI;

import cs.vsu.ru.group6.Shapes.ShapesEnum;
import cs.vsu.ru.group6.Shapes.Star;
import cs.vsu.ru.group6.Shapes.Triangle;
import cs.vsu.ru.group6.common.Canvas;
import cs.vsu.ru.group6.common.DBManager;
import cs.vsu.ru.group6.common.JavaFxPainter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML ColorPicker bgColorPicker;
    @FXML ColorPicker fillColorPicker;
    @FXML Button plusScaleBtn;
    @FXML Button minusScaleBtn;
    @FXML Slider scaleSlider;
    @FXML Label scaleLabel;
    @FXML Label canvasSizeLabel;
    @FXML Label currentPosLabel;
    @FXML TilePane shapeSelector;
    @FXML ScrollPane paintPanel;


    private JavaFxPainter painter;
    private Canvas canvas;
    private DrawPanel drawPanel;

    DBManager dbManager = new DBManager();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.canvas = new Canvas(1280, 720);
        this.drawPanel = new DrawPanel(canvas, dbManager);
        this.painter = drawPanel.getPainter();

        shapeSelector.setHgap(15);
        shapeSelector.setVgap(15);
        for (ShapesEnum shape : ShapesEnum.values()) {
            Button b = new Button();
            b.setText(shape + "");
            b.setOnMouseClicked((e) -> this.painter.selectShape(shape));
            this.shapeSelector.getChildren().add(b);
        }
        paintPanel.setContent(drawPanel);
        setupSlider();
        setupLabels();
        setupColorPickers();
    }
    private void setupLabels(){
        scaleLabel.setText(((int) scaleSlider.getValue()) + " %");
        canvasSizeLabel.textProperty().bind(drawPanel.getCanvasSizeProperty());


        paintPanel.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                double hmin = paintPanel.getHmin();
                double hmax = paintPanel.getHmax();
                double hvalue = paintPanel.getHvalue();
                double contentWidth = canvas.getLayoutBounds().getWidth();
                double viewportWidth = paintPanel.getViewportBounds().getWidth();

                double hOffset =
                        Math.max(0, contentWidth - viewportWidth) * (hvalue - hmin) / (hmax - hmin);

                double vmin = paintPanel.getVmin();
                double vmax = paintPanel.getVmax();
                double vvalue = paintPanel.getVvalue();
                double contentHeight = canvas.getLayoutBounds().getHeight();
                double viewportHeight = paintPanel.getViewportBounds().getHeight();

                double vOffset =
                        Math.max(0,  contentHeight - viewportHeight) * (vvalue - vmin) / (vmax - vmin);

                currentPosLabel.setText(String.format("x: %d; y: %d",
                        (int) (hOffset +  mouseEvent.getX()),
                        (int) (vOffset + mouseEvent.getY())
                ));
            }
        });
    }

    private void setupColorPickers(){
        bgColorPicker.valueProperty().addListener((observableValue, color, t1) -> painter.setBorderColor(t1));
        fillColorPicker.valueProperty().addListener((observableValue, color, t1) -> painter.setFillColor(t1));
    }

    private void setupSlider(){
        scaleSlider.setValue(10);
        scaleSlider.setMin(10);
        scaleSlider.setMax(1000);
        plusScaleBtn.setOnMouseClicked((e) -> {
            double value = scaleSlider.getValue();

            switch ((int) value){
                case 10:
                case 200:
                    scaleSlider.setValue(value * 2.5);
                    canvas.scale(2.5);
                    break;
                default:
                    scaleSlider.setValue(value * 2);
                    canvas.scale(2);
                    break;
            }
        });

        minusScaleBtn.setOnMouseClicked((e) -> {
            double value = scaleSlider.getValue();

            /*paintPanel.setViewportBounds(new BoundingBox(paintPanel.getViewportBounds().getMinX(),
                    paintPanel.getViewportBounds().getMinY(),
                    paintPanel.getViewportBounds().getWidth() * value / 100,
                    paintPanel.getViewportBounds().getHeight() * value / 100
            ));

             */
            switch ((int) value){
                case 25:
                case 500:
                    scaleSlider.setValue(value / 2.5);
                    canvas.scale(1. / 2.5);
                    break;
                default:
                    scaleSlider.setValue(value / 2);
                    canvas.scale(1. / 2);
                    break;
            }
        });
        scaleSlider.valueProperty().addListener((observableVal, oldVal, newVal) -> {
            scaleLabel.setText(newVal.intValue() + " %");
        });
    }

}

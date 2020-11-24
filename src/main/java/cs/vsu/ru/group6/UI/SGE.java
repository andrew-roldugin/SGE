package cs.vsu.ru.group6.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SGE extends Application {
    private final int WIDTH = 800, HEIGHT = 600;
   // private final DrawPanel drawPanel = new DrawPanel(WIDTH, HEIGHT);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/SGE.fxml"));
        primaryStage.setTitle("Simple Graphic Editor");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }
}

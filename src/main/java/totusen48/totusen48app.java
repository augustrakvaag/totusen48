package totusen48;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class totusen48app extends Application {

    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setTitle("2048");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("totusen48UI.fxml"));
        Parent root = loader.load();
        this.controller = loader.getController();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

        primaryStage.setScene(scene);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            if(e.getCode() == KeyCode.UP){
                controller.moveUp();
            }
            if(e.getCode() == KeyCode.DOWN){
                controller.moveDown();
            }
            if(e.getCode() == KeyCode.LEFT){
                controller.moveLeft();
            }
            if(e.getCode() == KeyCode.RIGHT){
                controller.moveRight();
            }
        });

        primaryStage.show();
    }

    public static void main(String[] args){
        Application.launch(args);

    }
}
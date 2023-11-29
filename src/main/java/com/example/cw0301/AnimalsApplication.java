package com.example.cw0301;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AnimalsApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AnimalsApplication.class.getResource("animals-view.fxml"));
        int conflictThisIsFromLocal;
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Animals");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

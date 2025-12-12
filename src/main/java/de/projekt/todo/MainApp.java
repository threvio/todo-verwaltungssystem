package de.projekt.todo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        var url = MainApp.class.getResource("/de/projekt/todo/ui/main-view.fxml");
        if (url == null) {
            throw new RuntimeException("FXML not found: /de/projekt/todo/ui/main-view.fxml");
        }

        FXMLLoader loader = new FXMLLoader(url);
        Scene scene = new Scene(loader.load(), 520, 420);

        stage.setTitle("Todo-Verwaltungssystem");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

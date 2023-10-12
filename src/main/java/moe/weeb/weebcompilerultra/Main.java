package moe.weeb.weebcompilerultra;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load FXML
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("theme.fxml"));
        // Create new scene
        Scene scene = new Scene(fxmlLoader.load(), 829, 675);

        // Load CSS
        String CSS = Objects.requireNonNull(getClass().getResource("theme.css")).toExternalForm();
        scene.getStylesheets().add(CSS);

        stage.setTitle("WeebCompiler Ultra Especial Edition");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
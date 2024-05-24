package lab11.ex1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloFXMLMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/hellofxml.fxml"));
        Parent root = loader.load();

        //Set up our stage
        primaryStage.setTitle("Hello FXML!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}

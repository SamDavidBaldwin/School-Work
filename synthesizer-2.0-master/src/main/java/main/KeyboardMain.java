/* *****************************************
 * Synthesizer 2.0
 *
 * Name: Samuel Baldwin
 * Date: Final 12/8/21
 *
 * Project: Synthesizer 2.0
 * Package: main
 * Class: KeyboardMain
 *
 * Description: The Main class for the Synthesizer system
 *
 * ****************************************
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main class to run the javaFX application
 */
public class KeyboardMain extends Application {

    /**
     * Main function to launch the GUI for the synthesizer application
     * @param args - parameters passed by the operating system
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start method for the javaFX application
     * @param primaryStage - the primary stage of the javaFX application
     * @throws IOException - Exception thrown if the .fxml file cannot be found
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        // Load in the FXML file. Obtain the root node of the scene graph
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/testui.fxml"));
        Parent root = loader.load();

        // Set up our stage
        primaryStage.setTitle("Initiating Keyboard");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}

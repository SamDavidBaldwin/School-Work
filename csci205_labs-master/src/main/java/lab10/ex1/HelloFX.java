/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 10/28/21
 * Time: 7:40 PM
 *
 * Project: csci205_labs
 * Package: lab10.ex1
 * Class: HelloFX
 *
 * Description:
 *
 * ****************************************
 */


package lab10.ex1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
    // Grab some property values from System
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version");

    // Create a Label from a String showing these properties
    Label label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

    // Set up our SceneGraph. Use a StackPane layout manager to manage the scene
    StackPane pane = new StackPane();pane.getChildren().add(label);
    Scene scene = new Scene(pane,640,480);

    // Set the scene to render for the stage and show it!
   stage.setScene(scene);
   stage.show();
    }
}


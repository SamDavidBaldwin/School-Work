package lab10.ex1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.*;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HelloMain extends Application {

    private Button btn;

    private Label lblTime;

    public static void main(String[] args){ launch(args); }

    @Override
    public void start(Stage primaryStage) {
        //Set up the root node for our scene graph
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        initSceneGraph(root);

        //Add the scene graph to the stage
        primaryStage.setScene(new Scene(root, 400, 600));

        //Set the title for the main window
        primaryStage.setTitle("Hello, JavaFX!");



        //Display the scene
        primaryStage.show();

    }

    /**
     * Initialize all controls and place them in the scene graph <code>root</code>
     * @param root is the root node container of the scene graph
     */
    private void initSceneGraph(VBox root) {
        //Create a button and set up the event handler to report the current time
        btn = new Button();
        btn.setText("Report the date and time");

        lblTime = new Label();
        //Add the button and label to the scene graph
        root.getChildren().add(btn);
        root.getChildren().add(lblTime);

        btn.setOnAction(event -> {
            LocalDateTime ldt = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ssa MM/dd/yyyy");
                    //System.out.println(ldt.format(formatter));
                    lblTime.setText(ldt.format(formatter));

                });

    }
}

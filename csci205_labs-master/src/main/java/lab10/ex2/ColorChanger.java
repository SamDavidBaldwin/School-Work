package lab10.ex2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class ColorChanger extends Application {
    private HBox root;
    private Button redButton;
    private Button greenButton;
    public Shape rectangle;
    public Sphere sphere;
    public PhongMaterial material;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initSceneGraph(primaryStage);

        initializeObjects();

        setRoots();

        ButtonEventHandlers();


        primaryStage.show();


    }

    /**
     * Sets the children to the root node
     */
    private void setRoots() {
        root.getChildren().add(redButton);
        root.getChildren().add(greenButton);
        root.getChildren().add(rectangle);
        root.getChildren().add(sphere);
    }

    /**
     * Initializes all of the private variables for this Stage
     */
    private void initializeObjects() {
        greenButton = new Button("Green");
        redButton = new Button("Red");
        rectangle = new Rectangle(150,150);
        sphere = new Sphere(75);
        rectangle.setFill(Color.GRAY);
        material = new PhongMaterial();
        material.setDiffuseColor(Color.GRAY);
        material.setSpecularColor(Color.GRAY);
        sphere.setMaterial(material);
    }

    /**
     * Sets up the event Handlers for the buttons, and the shapes
     */
    private void ButtonEventHandlers() {
        redButton.setOnAction(event -> {
            rectangle.setFill(Color.RED);
            material.setSpecularColor(Color.RED);
            material.setDiffuseColor(Color.RED);
            sphere.setMaterial(material);

        });
        greenButton.setOnAction(event -> {
            rectangle.setFill(Color.GREEN);
            material.setSpecularColor(Color.GREEN);
            material.setDiffuseColor(Color.GREEN);
            sphere.setMaterial(material);
        });
        rectangle.setOnMouseClicked(event -> {
            rectangle.setFill(Color.GRAY);
        });
        sphere.setOnMouseClicked(event -> {
            material.setDiffuseColor(Color.GRAY);
            material.setSpecularColor(Color.GRAY);
            sphere.setMaterial(material);
        });
    }

    /**
     * Sets up the background stage
     * @param primaryStage the stage that is being displayed
     */
    private void initSceneGraph(Stage primaryStage) {
        root =  new HBox();
        root.setSpacing(10);
        root.setPadding(new Insets(15));

        //Add the scene graph to the new stage
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();

        //Set the title for the main window
        primaryStage.setTitle("Color Changer");
    }
}

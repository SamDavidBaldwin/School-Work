package lab10.ex2;
import com.sun.javafx.scene.SceneEventDispatcher;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class HelloMe extends Application {
    private VBox root;
    private HBox topPane;
    private TextField textFieldInputName;
    private Button btn;
    private Text textNameOutput;
    private Reflection reflection;
    private DropShadow dropShadow;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        initSceneGraph(primaryStage);
        
        //Set the alignments of the scene elements
        setAllignments();

        //Set up the private instance variables
        privateInstanceVariables();

        //Set up the children
        setChildren();
        
        //Set up the event handlers
        setUpEventHandlers();

        //Set up the visual effects of the scene graph
        effectsManagement();


        //Display the stage
        primaryStage.show();

    }

    /**
     * Handles the initialization of private variables in the program
     */
    private void privateInstanceVariables() {
        textFieldInputName = new TextField();
        btn = new Button("Show my name!");
        textNameOutput = new Text();
        reflection = new Reflection();
        dropShadow = new DropShadow();
    }

    /**
     * Display effects on the output
     */
    private void effectsManagement() {
        textNameOutput.setFont(Font.font(null, FontWeight.BOLD, 30));
        textNameOutput.setFill(Color.FUCHSIA);

        //Add reflections
        reflection.setFraction(0.9);
        textNameOutput.setEffect(reflection);

        //Chain effects
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        dropShadow.setHeight(5);
        dropShadow.setWidth(2);
        dropShadow.setColor(Color.DARKGRAY);
        reflection.setInput(dropShadow);
    }

    /**
     * Sets the children to the proper node
     */
    private void setChildren() {
        root.getChildren().add(textNameOutput);
        root.getChildren().add(new Separator());
        root.getChildren().add(btn);
        root.getChildren().add(topPane);

        topPane.getChildren().add(new Label("Your name:"));
        topPane.getChildren().add(textFieldInputName);
    }

    /**
     * Sets up the background of the root node Stage
     * @param primaryStage the stage that is being displayed
     */
    private void initSceneGraph(Stage primaryStage) {
        root =  new VBox();
        topPane = new HBox(10);
        root.setSpacing(10);
        root.setPadding(new Insets(15));

        //Add the scene graph to the new stage
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();

        //Set the title for the main window
        primaryStage.setTitle("Hello, Me!");
    }

    private void setAllignments() {
        root.setAlignment(Pos.CENTER);
        topPane.setAlignment(Pos.CENTER);
    }

    private void setUpEventHandlers() {
        btn.setOnAction(event -> {
            System.out.println("Hello, " + textFieldInputName.getText() + "!");
            textNameOutput.setText(textFieldInputName.getText());
        });
        root.setOnMouseMoved(event ->{
            dropShadow.setOffsetY(event.getX()-root.getWidth()/3);
            dropShadow.setOffsetX(event.getY()-root.getHeight()/3);
        });
    }
}

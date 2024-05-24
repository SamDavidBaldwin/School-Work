package lab10.ex3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TempConverter extends Application {
    private VBox root;
    private Label prompt;
    private FlowPane topPane;
    private TextField textFieldInputTemp;
    private Label lblResult;
    private Button convert;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initSceneGraph(primaryStage);

        initializeTopPane();

        initializeLabel();

        initializeButton();

        primaryStage.show();






    }

    /**
     * Sets up the botton to convert to Celsius
     */
    private void initializeButton() {
        convert = new Button("Convert!");
        convert.setOnAction(event -> {
        try {
                int tempF = (Integer.parseInt(textFieldInputTemp.getText()));
                Double tempC = (tempF - 32.0) * 5.0 / 9.0;
                lblResult.setText(String.valueOf(tempC));

        }
        catch (NumberFormatException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect input!");
            alert.setHeaderText("Incorrect input specified!");
            alert.setContentText(String.format("Can not convert \"%s\"",textFieldInputTemp.getText()));
            alert.show();
        }
        });
        root.getChildren().add(convert);
        textFieldInputTemp.setOnAction(convert.getOnAction());
    }

    /**
     * Sets up the label to display the converted temperature
     */
    private void initializeLabel() {
        lblResult = new Label();
        lblResult.setMinHeight(25);
        lblResult.setMinWidth(75);
        lblResult.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT)));
        root.getChildren().add(lblResult);
    }

    /**
     * Sets up the top pane which contains the Label, and TextField
     */
    private void initializeTopPane() {
        topPane = new FlowPane(Orientation.HORIZONTAL);
        topPane.setAlignment(Pos.CENTER);
        topPane.setHgap(10);
        prompt = new Label("Temperature (F):");
        textFieldInputTemp = new TextField();
        textFieldInputTemp.setAlignment(Pos.CENTER);
        textFieldInputTemp.setPrefColumnCount(5);
        topPane.getChildren().add(prompt);
        topPane.getChildren().add(textFieldInputTemp);
        root.getChildren().add(topPane);
    }

    /**
     * Set up the background stage
     * @param primaryStage the Stage that the display is being done on
     */
    private void initSceneGraph(Stage primaryStage) {
        root =  new VBox();
        root.setSpacing(5);
        root.setPrefWidth(250);
        root.setPadding(new Insets(10,5,10,5));
        root.setAlignment(Pos.CENTER);

        //Add the scene graph to the new stage
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();

        //Set the title for the main window
        primaryStage.setTitle("F to C Converter");
    }
}

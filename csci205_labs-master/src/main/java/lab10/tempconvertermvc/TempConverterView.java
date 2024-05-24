/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 *
 * Name: YOUR NAME
 * Date: 10/24/21
 * Time: 9:30 PM
 *
 * Project: csci205_lectureEx
 * Class: TempConverterView
 * Description:
 *
 * ****************************************
 */

package lab10.tempconvertermvc;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * This is the "view" in the MVC design for the temperature converter. A view class
 * does nothing more than initializes all nodes for the scene graph for this view.
 */
public class TempConverterView {

    /** The model that contains the data and logic behind this view */
    private TempConverterModel theModel;

    /** Root node for the scene graph */
    private BorderPane root;

    /** topPane is the {@link FlowPane} layout container for the top of the view */
    private FlowPane topPane;

    /** The {@link TextField} control where the user enters text */
    private TextField textFieldTempInput;

    /** The {@link Label} that shows the result of the temperature conversion */
    private Label lblResult;

    /** The {@link Button} that initiates a temperature conversion */
    private Button btnConvert;

    /**
     * The {@link Label} that shows the units of the temperature conversion
     */
    private Label lblUnits;

    /**
     * The {@link RadioButton} to switch from F to C
     */
    private RadioButton rbFtoC;

    /**
     * The {@link RadioButton} to switch from C to F
     */
    private RadioButton rbCtoF;
    /**
     * The {@link ToggleGroup} to handle the Radio Buttons
     */
    private ToggleGroup toggleGroup;
    /**
     * Construct a new instance of the entire scene graph for
     * this view
     */
    public TempConverterView(TempConverterModel theModel) {
        this.theModel = theModel;
        initSceneGraph();
    }

    /**
     * Return the root node of the scene graph for this view
     * @return the root
     */
    public Parent getRoot() {
        return root;
    }

    /**
     * Get the text field that contains the TextField control where
     * the user enters the temperature
     *
     * @return the {@link TextField} instance where the user enters the temperature
     */
    public TextField getTextFieldTempInput() {
        return textFieldTempInput;
    }

    /**
     * @return the {@link Label} where the result of the conversion should be
     * shown
     */
    public Label getLblResult() {
        return lblResult;
    }

    /**
     * @return the {@link Button} that initiates the temperature conversion
     * calculation
     */
    public Button getBtnConvert() {
        return btnConvert;
    }

    /**
     * @return the {@link RadioButton} that sets the conversion from C to F
     */
    public RadioButton getrbCtoF(){return rbCtoF;}

    /**
     * @return the {@link RadioButton} that sets the conversion from F to C
     */
    public RadioButton getRbFtoC(){return rbFtoC;}

    /**
     * @return the {@Link Label} that displays the units
     */
    public Label getLblUnits(){return lblUnits;}


    /**
     * Initialize the entire scene graph
     */
    private void initSceneGraph() {
        root = new BorderPane();
        root.setPrefWidth(300);
        root.setPrefHeight(150);
        root.setPadding(new Insets(15));
        //root.setSpacing(5);
        root.setPrefWidth(250);
        root.setPadding(new Insets(10, 5, 10, 5));
        //root.setAlignment(Pos.CENTER);

        // Set up top pane container to hold the text field to
        // enter a temperature
        topPane = new FlowPane(Orientation.HORIZONTAL);
        topPane.setAlignment(Pos.CENTER);
        topPane.setHgap(10);



        // Text Field to enter the temperature
        textFieldTempInput = new TextField();
        textFieldTempInput.setAlignment(Pos.CENTER);
        textFieldTempInput.setPrefColumnCount(5);

        // Add leaf nodes for top pane
        topPane.getChildren().add(new Label("Temperature:"));
        topPane.getChildren().add(textFieldTempInput);

        //Label to determine what the units of the input are
        lblUnits = new Label("F");
        topPane.getChildren().add(lblUnits);

        // Middle section will show the result
        lblResult = new Label("");
        lblResult.setPrefWidth(75);
        lblResult.setPrefHeight(25);

        // Set up a border to appear around the converted temp
//        lblResult.setBorder(new Border(new BorderStroke(null,
//                                                        BorderStrokeStyle.SOLID,
//                                                        new CornerRadii(4),
//                                                        BorderWidths.DEFAULT)));
        lblResult.setStyle("-fx-border-style: solid; "+
                                   "-fx-border-radius: 4");
        lblResult.setAlignment(Pos.CENTER);

        // Set up the button to initiate the conversion
        btnConvert = new Button("Convert!");
        root.setAlignment(btnConvert, Pos.CENTER);

        //Create the radio button to select which temp conversion
        toggleGroup = new ToggleGroup();
        rbCtoF = new RadioButton("C to F");
        rbCtoF.setToggleGroup(toggleGroup);
        rbFtoC = new RadioButton("F to C");
        rbFtoC.setToggleGroup(toggleGroup);
        rbFtoC.setSelected(true);
        VBox leftPane = new VBox(10);
        leftPane.getChildren().addAll(rbFtoC,rbCtoF);



        // Add the three main sections for the VBox root container
        //root.getChildren().add(topPane);
        //root.getChildren().add(lblResult);
        //root.getChildren().add(btnConvert);
        root.setTop(topPane);
        root.setCenter(lblResult);
        root.setBottom(btnConvert);
        root.setLeft(leftPane);
    }
}

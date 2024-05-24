/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 *
 * Name: YOUR NAME
 * Date: 10/24/21
 * Time: 9:30 PM
 *
 * Project: csci205_lectureEx
 * Package: csci205.lec27.javafx_mvc.tempconvertermvc
 * Class: TempConverterController
 * Description:
 *
 * ****************************************
 */

package lab10.tempconvertermvc;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import javafx.scene.layout.Border;

/**
 * This is the MVC controller class for our temperature converter app
 */
public class TempConverterController {
    private TempConverterModel theModel;
    private TempConverterView theView;

    /**
     * Construct a controller that connects the model and the view for our
     * temperature conversion program
     *
     * @param theModel the model that is being controlled
     * @param theView the view that is being controlled
     */
    public TempConverterController(TempConverterModel theModel, TempConverterView theView) {
        this.theModel = theModel;
        this.theView = theView;

        initEventHandlers();
        initBindings();
    }

    /**
     * This is an internal helper method to initialize the event handlers
     */
    private void initEventHandlers() {
        this.theView.getBtnConvert().setOnAction(this::handleActionEvent);
        this.theView.getTextFieldTempInput().setOnAction(this::handleActionEvent);

        this.theView.getrbCtoF().setOnAction(this::handleActionEvent);
        this.theView.getRbFtoC().setOnAction(this::handleActionEvent);

        this.theModel.lastTempConvertedInCProperty().addListener((observable, oldValue, newValue)->{
            double hue = (1- newValue.doubleValue()/40)*240;
            if (hue > 240) hue = 240;
            if(hue < 0) hue = 0;
            Color c = Color.hsb(hue, 1, 0.75);
            Border b = new Border(new BorderStroke(c,
                                                    BorderStrokeStyle.SOLID,
                                                    new CornerRadii(4),
                                                    new BorderWidths(2)));
            this.theView.getLblResult().setBorder(b);
            this.theView.getLblResult().setTextFill(c);
        });
    }

    /**
     * Invoked when a specific event of the type for which this handler is
     * registered happens.
     *
     * @param event the event which occurred
     */
    public void handleActionEvent(ActionEvent event) {
        try {
            String s = this.theView.getTextFieldTempInput().getText();
            if (s.length() >  0) {
                String result = this.theModel.strTempConvert(s);
                this.theView.getLblResult().setText(result);
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect input!");
            alert.setHeaderText("Incorrect input specified!");
            alert.setContentText(String.format("Can not convert \"%s\"",
                                               this.theView.getTextFieldTempInput().getText()));
            alert.show();
        }
    }

    private void initBindings(){
        theModel.isIsSetForFtoCProperty().bind(theView.getRbFtoC().selectedProperty());
        theModel.isIsSetForCtoFProperty().bind(theView.getrbCtoF().selectedProperty());
        theView.getLblUnits().textProperty().bind(Bindings.when(theModel.isIsSetForCtoFProperty()).then("F").otherwise("C"));

    }

}

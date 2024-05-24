package lab11.ex1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HelloFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Slider sliderBlue;

    @FXML
    private Slider sliderGreen;

    @FXML
    private Slider sliderRed;

    @FXML
    private Slider sliderSize;

    @FXML
    private TextField textFieldName;

    @FXML
    private Text textName;

    @FXML
    private Label lblStatusBar;

    @FXML
    void initialize() {
        assert sliderBlue != null : "fx:id=\"sliderBlue\" was not injected: check your FXML file 'hellofxml.fxml'.";
        assert sliderGreen != null : "fx:id=\"sliderGreen\" was not injected: check your FXML file 'hellofxml.fxml'.";
        assert sliderRed != null : "fx:id=\"sliderRed\" was not injected: check your FXML file 'hellofxml.fxml'.";
        assert sliderSize != null : "fx:id=\"sliderSize\" was not injected: check your FXML file 'hellofxml.fxml'.";
        assert textFieldName != null : "fx:id=\"textFieldName\" was not injected: check your FXML file 'hellofxml.fxml'.";
        assert textName != null : "fx:id=\"textName\" was not injected: check your FXML file 'hellofxml.fxml'.";

        //Update the text object based on the textField changes
        textName.textProperty().bind(textFieldName.textProperty());

        Color c = (Color)textName.getFill();
        sliderRed.setValue(c.getRed());
        sliderBlue.setValue(c.getBlue());
        sliderGreen.setValue(c.getGreen());
        sliderSize.setValue(textName.getFont().getSize());

        initSliderHandlers();

    }

    /**
     * initSliderHandlers - initalize the event handlers for the sliders
     */

    private void initSliderHandlers() {
        sliderGreen.valueProperty().addListener((observable, oldValue, newValue) -> {
            Color c = (Color) textName.getFill();
            textName.setFill(Color.color(newValue.doubleValue(), c.getGreen(), c.getBlue()));
            updateStatusBar();
        });
        sliderBlue.valueProperty().addListener((observable, oldValue, newValue) -> {
            Color c = (Color) textName.getFill();
            textName.setFill(Color.color(c.getRed(), c.getGreen(), newValue.doubleValue()));
            updateStatusBar();
        });
        sliderRed.valueProperty().addListener((observable, oldValue, newValue) -> {
            Color c = (Color) textName.getFill();
            textName.setFill(Color.color(c.getRed(), newValue.doubleValue(), c.getBlue()));
            updateStatusBar();
        });
        sliderSize.valueProperty().addListener((observable, oldValue, newValue) -> {
            Font f = textName.getFont();
            textName.setFont(Font.font(f.getFamily(), newValue.doubleValue()));
            updateStatusBar();
        });
    }

    /**
     * Update the status bar with the current color and size of the text
     */
    private void updateStatusBar(){
        Color c = (Color) textName.getFill();
        lblStatusBar.setText(String.format("Color: %s    Size: %.1f", c.toString(),textName.getFont().getSize()));
    }

}

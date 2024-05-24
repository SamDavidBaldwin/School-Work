/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 11/6/21
 * Time: 11:11 PM
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc
 * Class: TrafficLightView
 *
 * Description: The view class for the TrafficLight MVC
 *
 * ****************************************
 */
package lab10.trafficlightmvc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;


public class TrafficLightView {
    /**
     * The traffic light model
     */
    private TrafficLightModel theModel;
    /**
     * The root node of the Scene
     */
    private VBox root;
    /**
     * A CheckBox, to detemine if the AutoOff is true or false
     */
    private CheckBox checkBoxAutoOff;
    /**
     * An array of Circle objects to represent lights
     */
    private ArrayList<Circle> lights;


    /**
     * Default TrafficLightView
     * @param theModel the TrafficLightModel object that is being represented
     *
     */

    public TrafficLightView(TrafficLightModel theModel){
        initModel(theModel);
        addElements(theModel);
        setChildren();
    }

    private void setChildren() {
        this.root.getChildren().add(checkBoxAutoOff);
        this.root.getChildren().addAll(lights);
    }

    /**
     * Add the necessary elements to the main model, i.e the checkbox and the lights
     * @param theModel the model which to add the elements to
     */
    private void addElements(TrafficLightModel theModel) {
        //Adding the Auto off CheckBox
        checkBoxAutoOff = new CheckBox("Auto off");
        checkBoxAutoOff.setPadding(new Insets(10));
        this.checkBoxAutoOff.setSelected(theModel.isIsAutoOff());

        //Adding the lights to the view
        lights = new ArrayList<>();
        for(Light modelLight : theModel.getLights()){
            Circle c = new Circle(50,modelLight.getColor());
            DropShadow s = new DropShadow(5,5,5, Color.BLACK);
            c.setEffect(s);
            lights.add(c);
        }


    }

    private void initModel(TrafficLightModel theModel) {
        this.theModel = theModel;
        root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.setMinHeight(300);
    }

    public VBox getRoot(){
        return root;
    }

    public CheckBox getCheckBoxAutoOff() {
        return checkBoxAutoOff;
    }

    public ArrayList<Circle> getLights() {
        return lights;
    }

}
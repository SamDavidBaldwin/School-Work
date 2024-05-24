/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 11/6/21
 * Time: 11:05 PM
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc
 * Class: TrafficLightModel
 *
 * Description: The model class for the TrafficLight MVC
 *
 * ****************************************
 */
package lab10.trafficlightmvc;

import javafx.beans.property.SimpleBooleanProperty;

import java.awt.*;
import java.util.ArrayList;

public class TrafficLightModel {

    /**
     * The array that represents the 3 lights in a traffic light
     */
    private ArrayList<Light> lights;

    /**
     * Determines if the program automatically shut off each light that gets turned on
     */
    private SimpleBooleanProperty isAutoOff;

    /**
     * Initialize a simple TrafficLight model
     */
    public TrafficLightModel(){
        this.lights = new ArrayList<>();
        for(LightColorEnum light: LightColorEnum.values())
            this.lights.add(new Light(light.getColorOn(),light.getColorOff()));
        this.isAutoOff = new SimpleBooleanProperty(true);
    }

    /**
     *
     * @return the lights array
     */
    public ArrayList<Light> getLights() {
        return lights;
    }

    /**
     *
     * @return if the AutoOff setting is true or false
     */
    public boolean isIsAutoOff() {
        return isAutoOff.get();
    }

    /**
     *
     * @return the AutoOff property
     */
    public SimpleBooleanProperty isAutoOffProperty() {
        return isAutoOff;
    }

    /**
     * Gets the light at a specific index
     * @param i the index value to search in the lights array
     * @return the Light object at the index i
     */
    public Light getLight(int i){
        return this.lights.get(i);
    }
}

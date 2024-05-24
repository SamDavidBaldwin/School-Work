/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 11/6/21
 * Time: 10:54 PM
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc
 * Class: Light
 *
 * Description: A basic class that models a light
 *
 * ****************************************
 */
package lab10.trafficlightmvc;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Paint;

import java.util.TreeMap;

public class Light {
    /**
     * If the light is on or off
     */
    private SimpleBooleanProperty isOn;

    /**
     * The color of the light when it is on
     */
    private Paint colorOn;

    /**
     * The color of the light when it is off
     */
    private Paint colorOff;

    /**
     * Array of Paint object properties
     */
    private SimpleObjectProperty<Paint> colorProperty;
    /**
     * The current color of the Light
     */
    private Paint currentColor;

    /**
     * Simple light instance
     * @param colorOn The color of the light when it is off
     * @param colorOff The color of the light when it is on
     */
    public Light(Paint colorOn, Paint colorOff){
        this.colorOn = colorOn;
        this.colorOff = colorOff;
        this.isOn = new SimpleBooleanProperty(false);
        this.currentColor = this.colorOn;
        this.colorProperty = new SimpleObjectProperty<>(this.currentColor);
    }

    public Paint getCurrentColor() {
        return currentColor;
    }

    public Paint getColor() {
        return this.currentColor;
    }

    public boolean isIsOn() {
        return isOn.get();
    }

    public SimpleBooleanProperty isOnProperty() {
        return isOn;
    }

    /**
     * Switches the status of the light
     */
    public void toggle(){
        this.isOn.set(!this.isOn.get());
        if (this.isIsOn()) {
            this.colorProperty.set(colorOff);
            this.currentColor = colorOff;
        }
        else{
            this.colorProperty.set(colorOn);
            this.currentColor = colorOn;
        }
    }

    /**
     * Turns on the light for a given time in ms
     * @param ms the amount of time to turn the light on
     */
    public void turnOnForMS(long ms){
        Runnable r = () -> {
            try{
                if (!this.isIsOn()){
                    toggle();
                    Thread.sleep(ms);
                }
            }
            catch(InterruptedException ex){
            }
            finally {
                if(this.isIsOn()){
                    toggle();
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    public Paint getColorOn() {
        return colorOn;
    }

    public Paint getColorOff() {
        return colorOff;
    }

    public Paint getColorProperty() {
        return colorProperty.get();
    }

}
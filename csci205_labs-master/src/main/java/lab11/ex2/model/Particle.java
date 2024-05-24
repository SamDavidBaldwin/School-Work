/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 11/5/21
 * Time: 10:56 PM
 *
 * Project: csci205_labs
 * Package: lab11.ex2.model
 * Class: Particle
 *
 * Description: A simple class to model the behavior of a single particle
 *
 * ****************************************
 */
package lab11.ex2.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.sql.Time;
import java.util.Random;

public class Particle {

    /**
     * The R value for the particle
     */
    private int Red;
    /**
     * The G value for the particle
     */
    private int Green;
    /**
     * The B value for the particle
     */
    private int Blue;

    /** The current x-coordinate of the particle*/
    private final DoubleProperty x;

    /** The current y-coordinate of the particle */
    private final DoubleProperty y;

    /**
     * The color of the particle
     */
    private final Color color;

    /**
     * The Timeline object that JavaFX provides to help auto update the particle
     */
    private Timeline timeline;

    /**
     * Start the timeline animation
     */
    public void play(){
        this.timeline.play();
    }

    /**
     * Pause the timeline animation
     */
    public void pause(){
        this.timeline.pause();
    }

    /**
     * Stop the current animation and reset the timeline back to the beginning
     */
    public void stopAndReset(){
        this.timeline.stop();
    }

    /**
     * This initializes a new timeline to allow a particle to move over time
     *
     * @param startX the starting x-coordinate for the particle
     * @param startY the starting y-coordinate for the particle
     * @param duration the number of seconds the particle should remain alive
     * @param xDeltaPerSec - the change in x-coordinate per second
     * @param yDeltaPerSec - the change in y-coordinate per second
     */

    private void initTimeline(double startX, double startY, double duration, double xDeltaPerSec, double yDeltaPerSec){
         this.timeline = new Timeline(
                 new KeyFrame(Duration.ZERO,
                         new KeyValue(x, startX),
                         new KeyValue(y, startY)
                 ),
                new KeyFrame(Duration.seconds(duration),
                 new KeyValue(x, startX + (xDeltaPerSec * duration)),
                 new KeyValue(y, startY + (yDeltaPerSec * duration))
                )
        );
    }

    /**
     * Initialize a new particle
     *
     * @param startX starting x-coordinate
     * @param startY starting y-coordinate
     * @param duration how long should this particle stay alive?
     * @param xDeltaPerSec the update amount of the x-coordinate
     * @param yDeltaPerSec the update amount of the y-coordinate
     * @param color the color of the particle
     */
    public Particle(double startX, double startY, double duration, double xDeltaPerSec, double yDeltaPerSec, Color color){
        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();
        Random rand = new Random();
        this.Red = rand.nextInt(255);
        this.Green = rand.nextInt(255);
        this.Blue = rand.nextInt(255);
        Color newColor = Color.rgb(this.Red,this.Green,this.Blue);
        this.color = newColor;
        this.initTimeline(startX,startY,duration,xDeltaPerSec,yDeltaPerSec);
    }

    public Color getColor() {
        return color;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public double getX() {
        return x.get();
    }

    public double getY() {
        return y.get();
    }


    public DoubleProperty yProperty() {
        return y;
    }

    public DoubleProperty xProperty() {
        return x;
    }

}
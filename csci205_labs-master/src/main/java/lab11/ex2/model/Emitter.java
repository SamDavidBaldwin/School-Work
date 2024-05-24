/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 11/5/21
 * Time: 11:07 PM
 *
 * Project: csci205_labs
 * Package: lab11.ex2.model
 * Class: Emitter
 *
 * Description: An object that emits multiple particles in random directions
 *
 * ****************************************
 */
package lab11.ex2.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Emitter {

    //Particle will be emitted in random directions from the source
    private  static Random rng = new Random();

    //Keep a list of the particles being emitted
    private List<Particle> listOfParticles;

    //Number of particles that will be emitted
    private int numParticles;

    //Position of the source of the emitter
    private double x, y;

    /**
     * Maximum velocity allowed in pixels per second
     */
    public static final double MAX_VELOCITY_PER_SEC = 150.0;

    /**
     * Maximum duration in seconds
     */
    public static final double MAX_DURATION = 5.0;

    /**
     * Initialize a new emitter
     *
     * @param numParticles the number of particles that will be emitted
     * @param x The x-coordinate of the emitter
     * @param y The y-coordinate of the emitter
     */
    public Emitter(int numParticles, double x, double y) {
        this.numParticles = numParticles;
        this.x = x;
        this.y = y;
        this.listOfParticles = new ArrayList<>();
        this.initParticles();
    }

    /**
     * Initialize the set of particles that will be emitted from this emitter
     */

    private void initParticles(){
        while(listOfParticles.size() < this.numParticles){
            double durationInSec = rng.nextDouble() * MAX_DURATION;
            double xDeltaPerSec = rng.nextDouble() * MAX_VELOCITY_PER_SEC + -(MAX_VELOCITY_PER_SEC /2);
            double yDeltaPerSec = rng.nextDouble() * MAX_VELOCITY_PER_SEC + -(MAX_VELOCITY_PER_SEC /2);
            Particle p = new Particle(x, y, durationInSec, xDeltaPerSec,yDeltaPerSec, Color.YELLOW);
            listOfParticles.add(p);
        }
    }

    /**
     * Return a Stream object representing a stream of particles
     */
    public Stream<Particle> particleStream(){
        return this.listOfParticles.stream();
    }

    /**
     * Go through each particle and start the timeline
     */
    public void play(){
        particleStream().forEach(Particle::play);
    }

    public void stopAndReset(){
        particleStream().forEach(Particle::stopAndReset);
    }

}

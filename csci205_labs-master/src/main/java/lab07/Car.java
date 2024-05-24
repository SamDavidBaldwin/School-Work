/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 10/24/21
 * Time: 3:34 PM
 *
 * Project: csci205_labs
 * Package: lab07
 * Class: Car
 *
 * Description: A simple Class definition for a Car
 *
 * ****************************************
 */
package lab07;
import java.util.*;

/**
 * Main Car class that models a simple car and gives statistics
 * @author Samuel Baldwin
 */
public class Car {
    /**
     * The miles per gallon the car gets
     */
    private double mpg;
    /**
     * The number of cylinders in the cars engine
     */
    private int cylinders;
    /**
     * Engine displacement is the measure of the cylinder volume swept by all of the pistons of a piston engine, excluding the combustion chambers.
     */
    private double displacement;
    /**
     * The horsepower of the engine
     */
    private double horsepower;
    /**
     * The weight of the car
     */
    private double weight;
    /**
     * The acceleration of the car
     */
    private double accel;
    /**
     * The year the car was made
     */
    private int modelYear;
    /**
     * Country of origin
     */
    private int origin;
    /**
     * The name of the car
     */
    private String carName;

    /**
     * Default constructor for a car
     * @param csvRecord A lne from a CSV line, from which the Car gets its attributes
     */
    public Car(String csvRecord){
        String[] inputs = csvRecord.split(",");
        try {
            this.mpg = Double.parseDouble(inputs[0]);
            this.cylinders = Integer.parseInt(inputs[1]);
            this.displacement = Double.parseDouble(inputs[2]);
            this.horsepower = Double.parseDouble(inputs[3]);
            this.weight = Double.parseDouble(inputs[4]);
            this.accel = Double.parseDouble(inputs[5]);
            this.modelYear = Integer.parseInt(inputs[6]);
            this.origin = Integer.parseInt(inputs[7]);
            this.carName = inputs[8];
        }
        catch(NumberFormatException ex){
            this.horsepower = 0.0;
        }
        catch (InputMismatchException exception) {
            this.horsepower = 0.0;
        }

    }

    public double getAccel() {
        return accel;
    }

    public double getDisplacement() {
        return displacement;
    }

    public double getHorsepower() {
        return horsepower;
    }

    public double getMpg() {
        return mpg;
    }

    public double getWeight() {
        return weight;
    }

    public int getCylinders() {
        return cylinders;
    }

    public int getModelYear() {
        return modelYear;
    }

    public int getOrigin() {
        return origin;
    }

    public String getCarName() {
        return carName;
    }

    @Override
    public String toString() {
        return "Car{" +
                "mpg=" + mpg +
                ", cylinders=" + cylinders +
                ", displacement=" + displacement +
                ", horsepower=" + horsepower +
                ", weight=" + weight +
                ", accel=" + accel +
                ", modelYear=" + modelYear +
                ", origin=" + origin +
                ", carName='" + carName + '\'' +
                '}';
    }
}




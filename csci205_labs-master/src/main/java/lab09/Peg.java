/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 10/25/21
 * Time: 6:18 PM
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: Peg
 *
 * Description:
 *
 * ****************************************
 */
package lab09;

public class Peg {
    /**
     * The color of the peg
     */
    private String color;

    /**
     * Default Peg object
     * @param color The color value that Peg object holds
     */
    public Peg(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.getColor();
    }
}

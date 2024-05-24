/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 10/22/21
 * Time: 7:52 AM
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: Guess
 *
 * Description:
 *
 * ****************************************
 */
package lab09;

import java.util.*;

public class Line {
    /**
     * The first colour in the array
     */
    private Peg firstPosition;
    /**
     * The second colour in the array
     */
    private Peg secondPosition;
    /**
     * The third colour in the array
     */
    private Peg thirdPosition;
    /**
     * The fourth colour in the array
     */
    private Peg fourthPosition;
    /**
     * Number of Red pegs in a line
     */
    private int numRedInLine;
    /**
     * Number of Blue pegs in a line
     */
    private int numBlueInLine;
    /**
     * Number of Yellow pegs in a line
     */
    private int numYellowInLine;
    /**
     * Number of Green pegs in a line
     */
    private int numGreenInLine;
    /**
     * Number of Purple pegs in a line
     */
    private int numPurpleInLine;
    /**
     * Number of Black pegs in a line
     */
    private int numBlackInLine;

    public String getFirstColour() {
        return firstPosition.getColor();
    }

    public String getFourthColour() {
        return fourthPosition.getColor();
    }

    public String getSecondColour() {
        return secondPosition.getColor();
    }

    public String getThirdColour() {
        return thirdPosition.getColor();
    }

    public int getNumBlackInLine() {
        return numBlackInLine;
    }

    public int getNumBlueInLine() {
        return numBlueInLine;
    }

    public int getNumGreenInLine() {
        return numGreenInLine;
    }

    public int getNumPurpleInLine() {
        return numPurpleInLine;
    }

    public int getNumRedInLine() {
        return numRedInLine;
    }

    public int getNumYellowInLine() {
        return numYellowInLine;
    }

    public void setNumBlackInLine(int numBlackInLine) {
        this.numBlackInLine = numBlackInLine;
    }

    public void setNumBlueInLine(int numBlueInLine) {
        this.numBlueInLine = numBlueInLine;
    }

    public void setNumGreenInLine(int numGreenInLine) {
        this.numGreenInLine = numGreenInLine;
    }

    public void setNumPurpleInLine(int numPurpleInLine) {
        this.numPurpleInLine = numPurpleInLine;
    }

    public void setNumRedInLine(int numRedInLine) {
        this.numRedInLine = numRedInLine;
    }

    public void setNumYellowInLine(int numYellowInLine) {
        this.numYellowInLine = numYellowInLine;
    }

    /**
     * Manually alter the color of a position within the board
     * @param position the position within the line to alter
     * @param color the color to set the peg to
     */
    public void setPositions(int position, String color) {
        switch(position){
            case 0:
                firstPosition.setColor(color);
                break;
            case 1:
                secondPosition.setColor(color);
                break;
            case 2:
                thirdPosition.setColor(color);
                break;
            case 3:
                fourthPosition.setColor(color);
                break;
            default:
                break;
        }

    }

    /**
     * Default constructor of a line, a list of 4 Peg objects
     */
    public Line(){
        Scanner scnr = new Scanner(System.in);
        Peg[] currentGuess = new Peg[4]; //Initialize the list
        int i = 0;
        while(i <= 3) { //Loop until the list is full
            System.out.println("Please enter the colour at position "+ (i+1) +" in the code\nPossible colors (Red),(Yellow),(Blue),(Green),(Purple),(Black)");
            String colour = scnr.next();
            switch (colour.toLowerCase()) {
                case "red":
                    currentGuess[i] = new Peg(colour.replaceFirst("r", "R"));
                    numRedInLine++;
                    i++;
                    break;
                case "blue":
                    currentGuess[i] = new Peg(colour.replaceFirst("b", "B"));
                    numBlueInLine++;
                    i++;
                    break;
                case "yellow":
                    currentGuess[i] = new Peg(colour.replaceFirst("y", "Y"));
                    numYellowInLine++;
                    i++;
                    break;
                case "green":
                    currentGuess[i] = new Peg(colour.replaceFirst("g", "G"));
                    numGreenInLine++;
                    i++;
                    break;
                case "purple":
                    currentGuess[i] = new Peg(colour.replaceFirst("p", "P"));
                    numPurpleInLine++;
                    i++;
                    break;
                case "black":
                    currentGuess[i] = new Peg(colour.replaceFirst("b", "B"));
                    numBlackInLine++;
                    i++;
                    break;
                default:
                    System.out.println("Please enter a valid colour.");
            }

        }
        this.firstPosition = currentGuess[0];
        this.secondPosition = currentGuess[1];
        this.thirdPosition = currentGuess[2];
        this.fourthPosition = currentGuess[3];
    }

    /**
     * A Parametrized constructor of a line object taking 4 Peg objects as arguments
     * @param pos1 the Peg object at position 1
     * @param pos2 the Peg object at position 2
     * @param pos3 the Peg object at position 3
     * @param pos4 the Peg object at position 4
     */
    public Line(Peg pos1, Peg pos2, Peg pos3, Peg pos4){
        Peg[] currentGuess = new Peg[4];
        currentGuess[0] = pos1;
        currentGuess[1] = pos2;
        currentGuess[2] = pos3;
        currentGuess[3] = pos4;
        this.firstPosition = pos1;
        this.secondPosition = pos2;
        this.thirdPosition = pos3;
        this.fourthPosition = pos4;
    }

    @Override
    public String toString() {
        String s = "";
        s += "[" + this.firstPosition + " " + this.secondPosition +" " + this.thirdPosition + " " + this.fourthPosition + "]";
        return s;
    }
}
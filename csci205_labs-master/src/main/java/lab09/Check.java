/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 10/22/21
 * Time: 7:57 AM
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: CheckingSystem
 *
 * Description:
 *
 * ****************************************
 */
package lab09;

public class Check {
    /**
     * The number of guesses that have the correct color and position
     */
    private int correctPositionGuesses = 0;
    /**
     * The number of guesses that have the correct color and not position
     */
    private int correctColourGuesses = 0;

    /**
     * Handles the operation of the chack
     * @param masterCode The code to be checked against
     * @param guess The guess provided by the user
     */
    public Check(Line masterCode, Line guess) {
        checkCorrect(masterCode);
        checkPositions(masterCode, guess);
        checkCorrectColours(masterCode,guess);
    }

    /**
     * Checks each specific position, to see if the user guessed that positional value and color correct
     * @param masterCode The code to be checked against
     * @param guess The guess provided by the user
     */
    private void checkPositions(Line masterCode, Line guess) {
        checkCorrectPositions(masterCode.getFirstColour(), guess.getFirstColour());
        checkCorrectPositions(masterCode.getSecondColour(), guess.getSecondColour());
        checkCorrectPositions(masterCode.getThirdColour(), guess.getThirdColour());
        checkCorrectPositions(masterCode.getFourthColour(), guess.getFourthColour());
        System.out.println("Correct Positional Guesses: " + correctPositionGuesses);
    }

    /**
     * Checking a singular position to see if the two values in th edistinct arrays are equal
     * @param master The code to be checked against
     * @param guess The guess provided by the user
     */
    public void checkCorrectPositions(String master, String guess){
        if(master.equalsIgnoreCase(guess)){
            correctPositionGuesses += 1;
        }

    }

    /**
     * Checks if the guess supplied by the user has similar colors to the master, not including the colors that are in the correct positions
     * @param master The code to be checked against
     * @param guess The guess provided by the user
     */
    public void checkCorrectColours(Line master, Line guess) {
        if (master.getNumBlackInLine() > 0 && guess.getNumBlackInLine() > 0) {
            if (master.getNumBlackInLine() >= guess.getNumBlackInLine()) {
                correctColourGuesses += guess.getNumBlackInLine();
            } else {
                correctColourGuesses += master.getNumBlackInLine();
            }
        }
        if (master.getNumRedInLine() > 0 &&  guess.getNumRedInLine() > 0){
            if(master.getNumRedInLine() >= guess.getNumRedInLine()){
                correctColourGuesses += guess.getNumRedInLine();
            }
            else{
                correctColourGuesses += master.getNumRedInLine();
            }
        }if (master.getNumGreenInLine() > 0 &&  guess.getNumGreenInLine() > 0){
            if(master.getNumGreenInLine() >= guess.getNumGreenInLine()){
                correctColourGuesses += guess.getNumGreenInLine();
            }
            else{
                correctColourGuesses += master.getNumGreenInLine();
            }
        }if (master.getNumPurpleInLine() > 0 &&  guess.getNumPurpleInLine() > 0){
            if(master.getNumPurpleInLine() >= guess.getNumPurpleInLine()){
                correctColourGuesses += guess.getNumPurpleInLine();
            }
            else{
                correctColourGuesses += master.getNumPurpleInLine();
            }
        }if (master.getNumYellowInLine() > 0 &&  guess.getNumYellowInLine() > 0){
            if(master.getNumYellowInLine() >= guess.getNumYellowInLine()){
                correctColourGuesses += guess.getNumYellowInLine();
            }
            else{
                correctColourGuesses += master.getNumYellowInLine();
            }
        }if (master.getNumBlueInLine() > 0 &&  guess.getNumBlueInLine() > 0){
            if(master.getNumBlueInLine() >= guess.getNumBlueInLine()){
                correctColourGuesses += guess.getNumBlueInLine();
            }
            else{
                correctColourGuesses += master.getNumBlueInLine();
            }
        }
        System.out.println("Correct Colour Guesses: " + correctColourGuesses);
    }

    /**
     * A check to see if all of the positions have been guessed correctly i.e. the code has been guessed
     * @param master The master code that has been guessed
     * @return Boolean value, true if the code has been guessed, false if the code has not been guessed
     */
    public boolean checkCorrect(Line master){
        if (this.correctPositionGuesses == 4){
            System.out.println("Congratulations you guessed the code!");
            System.out.println("The code was: [" + master.getFirstColour() + ", "  + master.getSecondColour() + ", " + master.getThirdColour() + ", " + master.getFourthColour() + "]");
            return true;
        }
        else{
            return false;
        }


    }

}

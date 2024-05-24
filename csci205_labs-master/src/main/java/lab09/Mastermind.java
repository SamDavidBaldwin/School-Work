/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 10/25/21
 * Time: 6:37 PM
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: Mastermind
 *
 * Description:
 *
 * ****************************************
 */
package lab09;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Mastermind {
    /**
     * The current GameState
     */
    private GameState state;

    /**
     * The game board
     */
    private Board board;

    public Mastermind(){
        this.state = GameState.NEW_GAME;
    }

    /**
     * Method to start and handle a game of Mastermind
     */
    public void play(){
        Scanner scnr = new Scanner(System.in);
        int guesses = BoardInitialization(scnr);
        Line masterCode = SetMasterCode();
        GuessProcess(guesses, masterCode);
    }

    /**
     * Method to initialize the Board object for the game, takes user input to define the dimensions
     * @param scnr a Scanner instance
     * @return the number of guesses that the user gets
     */
    private int BoardInitialization(Scanner scnr){
        this.state = GameState.PLAYING;
        System.out.println("How many guesses would you like the user to have?");
            try {
                int guesses = scnr.nextInt();
                if (guesses > 0) {
                    this.board = new Board(guesses + 1, 4);
                }
                else{
                    this.board = new Board(1, 4);
                }
                this.board.setBoardPosition(0 , new String[]{"XXXXX","XXXXX","XXXXX","XXXXX"});
                return guesses;

            } catch (InputMismatchException ex) {
                throw new InputMismatchException("The number of guesses requires an integer input");
            }
    }

    /**
     * Establishes the master code, whether it is manually input, or randomly generated
     * @return the master code for that instance of the game
     */
    private Line SetMasterCode() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("***** MASTER CODE *****\nWould you like to use a random code? (Y/N)");
        String userChoice = scnr.next();
        if(userChoice.equalsIgnoreCase("y")| userChoice.equalsIgnoreCase("yes")){
            Line masterCode = generateRandomSequence();
            System.out.println(masterCode);
            return masterCode;
        }
        else{
            Line masterCode = new Line();
            //System.out.println(masterCode);
            return masterCode;
        }
    }

    /**
     * Random generation of a Line object that contains a code
     * @return Line object
     */
    private Line generateRandomSequence() {
        //Maybe do userChoice.contains("y"){
        Line masterCode = new Line(new Peg("null"),new Peg("null"), new Peg("null"),new Peg("null"));
        for(int i = 0; i<4; i++){
            int randomNum= 0;
            randomNum += (int)(Math.random()*6);
            switch(randomNum){
                case 0:
                    masterCode.setPositions(i, "Red");
                    masterCode.setNumRedInLine(masterCode.getNumRedInLine()+1);
                    break;
                case 1:
                    masterCode.setPositions(i, "Yellow");
                    masterCode.setNumYellowInLine(masterCode.getNumYellowInLine()+1);
                    break;
                case 2:
                    masterCode.setPositions(i, "Blue");
                    masterCode.setNumBlueInLine(masterCode.getNumBlueInLine()+1);
                    break;
                case 3:
                    masterCode.setPositions(i, "Green");
                    masterCode.setNumGreenInLine(masterCode.getNumGreenInLine()+1);
                    break;
                case 4:
                    masterCode.setPositions(i, "Purple");
                    masterCode.setNumPurpleInLine(masterCode.getNumPurpleInLine()+1);
                    break;
                case 5:
                    masterCode.setPositions(i, "Black");
                    masterCode.setNumBlackInLine(masterCode.getNumBlackInLine()+1);
                    break;
            }
        }
        return masterCode;
    }

    /**
     * Handles the process of asking the user for the guess, and checking it against the master code
     * @param guesses The number of guesses that the user gets
     * @param masterCode The master code for that instance of the game, to check against
     */
    private void GuessProcess(int guesses, Line masterCode) {
        while (this.state == GameState.PLAYING) {
            for (int i = 0; i < guesses; i++) {
                Line guess = new Line();
                Check check = new Check(masterCode, guess);
                if (WinCase(masterCode, i, check)){
                    UpdateBoard(0, masterCode);
                    UpdateBoard(i+1, guess);
                    break;
                }else{
                    LossCase(guesses, masterCode, i);
                }
                UpdateBoard(i+1,guess);
                System.out.println("You have " + (guesses-i-1) + " guesses left");
            }
        }
    }

    /**
     * Checks if the user has reached the loss condition, and if so, handles the Loss sequence and exits
     * @param guesses The number of guesses the user gets
     * @param masterCode The master code for that instance
     * @param i The number of guesses the user has currently used
     */
    private void LossCase(int guesses, Line masterCode, int i) {
        if((i +1) >= guesses){
            System.out.println("You couldn't guess the code!");
            System.out.println("The code was: [" + masterCode.getFirstColour() + ", " + masterCode.getSecondColour() + ", " + masterCode.getThirdColour() + ", " + masterCode.getFourthColour() + "]");
            this.state = GameState.LOSE;
            UpdateBoard(0, masterCode);
        }
    }

    /**
     * Checks if the user has reached the Win condition -> guessing the code correctly and handles the win case and exits
     * @param masterCode The master code for that instance of the game
     * @param i THe number of guesses the user has taken
     * @param check A check object
     * @return Boolean value, true if the game has been won, false otherwise
     */
    private boolean WinCase(Line masterCode, int i, Check check) {
        if (check.checkCorrect(masterCode)) {
            System.out.println("It took " + i + " guesses to correctly guess the code.");
            this.board.setBoardPosition(0, new String[]{masterCode.getFirstColour(), masterCode.getSecondColour(), masterCode.getThirdColour(), masterCode.getFourthColour()});
            this.state = GameState.WIN;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * @param i the current guess number
     * @param guess The guess Line object that the user created
     */
    private void UpdateBoard(int i, Line guess) {
        this.board.setBoardPosition(i,  new String[]{guess.getFirstColour(), guess.getSecondColour(), guess.getThirdColour(), guess.getFourthColour()});
        System.out.println(this.board);
    }
}

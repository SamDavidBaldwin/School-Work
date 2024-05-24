/* *****************************************
 * Winter 2021
 *
 * Name: Samuel Baldwin
 * Date: 12/19/21
 * Time: 3:54 PM
 *
 * Project: Synthesizer 2.0
 * Package: main
 * Class: Score
 *
 * Description: A simple score class, to represent the output of the program in a visual way
 *
 * ****************************************
 */
package main;

import java.util.Arrays;

public class Score {
    private String[] score;

    public Score(){
        score = new String[7];
        for(int i = 0; i<7; i++){
            score[i] = "\u2015";
        }
    }

    /**
     * Returns the value of the Score at a given row
     * @param i the row that is being called
     * @return the String value of that row
     */
    public String getScore(int i){
        return this.score[i];
    }

    /**
     * Updates the score object at a given row, to a given value
     * @param row The row of the score to update
     * @param value What value to add to the row
     */
    public void updateScore(int row, String value){
        if(this.score[row]!= null) {
            this.score[row] += value;
        }
        else{
            this.score[row] = value;
        }
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + Arrays.toString(score) +
                '}';
    }
}
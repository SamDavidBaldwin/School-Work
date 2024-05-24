/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 9/15/21
 * Time: 8:45 AM
 *
 * Project: csci205_labs
 * Package: lab03
 * Class: BadCoder
 *
 * Description: A practice on fixing bad coding habits.
 *
 * ****************************************
 */
package lab03;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BadCoder {
    public static final int NUM_INTS = 10;

    public static void main(String[] args) {
        String name = greetUser();
        int[] x = getArrayOfRandomInts(100);
        System.out.println(name + ", our array is: " + Arrays.toString(x));
        }

    /**
     * Generate an array of random ints
     * @param bound the maximum value to randomly generate (exclusively, meaning all integers from 0 (inclusive) to bound (exclusive)
     *
     * @return the array of int, filled with random integers
     */

    private static int[] getArrayOfRandomInts(int bound) {
        Random rand = new Random();
        int[] x = new int[NUM_INTS];
        for(int i = 0; i< NUM_INTS; i++) {
            x[i] = rand.nextInt(bound);
        }
        return x;
    }

    /**
     * Greets and asks the user for their name
     * @return the user Name
     */
    private static String greetUser() {
        //Greet the user and ask for their name
        Scanner scnr = new Scanner(System.in);
        System.out.println("Greetings. What is your name?");
        String name = scnr.nextLine();
        return name;
    }

}

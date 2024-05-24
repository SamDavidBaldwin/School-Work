/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 9/13/21
 * Time: 8:46 AM
 *
 * Project: csci205_labs
 * Package: lab02
 * Class: BinaryConverter
 *
 * Description: A simple binary converter
 *
 * ****************************************
 */
package lab02;

import java.util.Scanner;
import java.lang.*;

/**
 * A program that takes a user input and converts it from binary to numerical
 *
 * @author Samuel Baldwin
 */

public class BinaryConverter {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcomes to the Binary Converter.");
        System.out.println("Please enter a binary number:");
        String userInput = scnr.nextLine();

        boolean continueCycling = true;
        boolean goodInput = true;
        int i;
        while (goodInput) {
            for (i = 0; i < userInput.length(); i++) {
                if (userInput.charAt(i) != '1' && userInput.charAt(i) != '0') {
                    goodInput = false;
                    System.out.println("ERROR! Try again. Enter a binary number:");
                    userInput = scnr.nextLine();
                }
            }

            System.out.println(Integer.parseInt(userInput, 2));
            System.out.println("Try again? [Y|N]");
            String repeatInput = scnr.nextLine();
            if(repeatInput.charAt(0) == 'n'){
                System.out.println("Goodbye!");
                return;
            }
            System.out.println("Please enter a binary number:");
            userInput = scnr.nextLine();
            }
        }
        }







/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 *
 * Name: Sam Baldwin
 * Date: 9/27/21
 * Time: 10:00 AM
 *
 * Project: csci205_labs
 * Package: lab05.start
 * Class: ExceptionExample
 * Description: An example Exception
 *
 * ****************************************
 */

package lab05;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionExample {
    public static final int SIZE = 10;

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int[] x = new int[SIZE];
        int i = 0;

        do {
            try {
                System.out.print("Enter an index to fill, or " + SIZE + " to finish: ");
                i = scnr.nextInt();
                if (i != SIZE)
                    x[i] = i;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(i + " is out of range! Try again!");
                continue;
            }
            catch (InputMismatchException e){
                System.out.println("Please enter integers! Try again");
                //Empty the input buffer
                scnr.nextLine();
                continue;
            }
        } while (i != SIZE);
    }

}

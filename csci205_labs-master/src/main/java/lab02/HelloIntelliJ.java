/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 9/13/21
 * Time: 12:42 AM
 *
 * Project: csci205_labs
 * Package: lab02
 * Class: HelloIntelliJ
 *
 * Description: A simple program to help us become familiar with IntelliJ
 *
 * ****************************************
 */


package lab02;
import java.util.Scanner;
/**
 * Main program to ask the user for their name and do some basic analysis with it.
 * @author Samuel Baldwin
 */
public class HelloIntelliJ{


    private static String sFirst;
    private static String sLast;
    private static String sFullName;

    /**
     * Main method for the program
     * @param args - arguments passed from the command line
     */

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        askUserForName(scnr);
        int countVowels = countVowelsInName();
        PrintResults(countVowels);
    }

    /**
     * Print the result of our vowel analysis to {@link System#out}
     *
     * @param countVowels is the number of vowels reported
     */
    private static void PrintResults(int countVowels) {
        System.out.printf("You have %d letters in your name\n", sFirst.length() + sLast.length());
        System.out.printf("You have %d vowels in your name\n", countVowels);
        System.out.printf("%.1f%% of your name consists of vowels.", 100.0* countVowels /(sFullName.length()));
    }

    /**
     * Counts the vowels in a name that was entered
     *
     * @return the number of vowels in the first and last name
     */
    private static int countVowelsInName() {
        int countVowels = 0;
        for (char ch : sFullName.toLowerCase().toCharArray()){
            switch (ch) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    countVowels++;
                    break;

            }
        }
        return countVowels;
    }

    /**
     * Asks the user for their first and last name. The results are the class variables
     * {@link HelloIntelliJ#sFirst} and {@link HelloIntelliJ#sLast}. The concatenated
     * names with a space in between will be stored as {@link HelloIntelliJ#sFullName}.
     *
     * @param scnr The Scanner object used to take user input.
     */
    private static void askUserForName(Scanner scnr) {
        //Ask the user for their first and last name
        System.out.println("Please enter your first name:");
        sFirst = scnr.nextLine().strip();
        System.out.println("Please enter your last name:");
        sLast = scnr.nextLine().strip();
        sFullName = sFirst + " " + sLast;
        System.out.println("Your full name is: " + sFullName);
    }
}
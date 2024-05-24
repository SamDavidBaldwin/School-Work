/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 *
 * Name: YOUR NAME
 * Date: 9/15/21
 * Time: 12:18 PM
 *
 * Project: csci205_labs
 * Package: lab03
 * Class: DateProcessor
 * Description: A system designed to process Date and DateTime objects
 *
 * ****************************************
 */

package lab03;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.time.*;
import java.util.*;



/**
 * DateProcessor is a simple class to help with learning the {@link java.time.LocalDate} and
 * {@link java.time.LocalTime} classes
 */
public class DateProcessor {

    /**
     * The main program. It presents the user with a menu, asks them for a choice
     * and carries out the appropriate choice.
     *
     * @param args - command line arguments. Not used.
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome to the LocalDate processor");


        // This main method will step through each required task of this lab exercise
        int choice = -1;
        boolean isDone = false;

        do {
            displayMenu();
            choice = getUserChoice(scnr);
            switch(choice) {
                case 1:
                    task1(scnr);
                    break;
                case 2:
                    task2(scnr);
                    break;
                case 3:
                    task3(scnr);
                    break;
                case 4:
                    isDone = true;
                    break;
                default:
                    System.out.println("Bad choice! Try again");
            }
        } while (!isDone);
        System.exit(0);
    }

    /**
     * Show a menu to the user prompting them with the user choices
     *
     */
    private static void displayMenu() {
        System.out.println("Please choose: ");
        System.out.println("  1) Print today's date nicely formatted");
        System.out.println("  2) My feelings on today");
        System.out.println("  3) How long have you been alive? ");
        System.out.println("  4) EXIT");
    }

    /**
     * Read the choice from the user until they guess a correct choice
     *
     * @param scnr - Scanner to read user input from
     * @return the user choice as an integer
     */
    private static int getUserChoice(Scanner scnr) {
        int choice = 0;

        // Show the user the main menu
        boolean isValidChoice = false;
        while (!isValidChoice) {
            String sInput = scnr.nextLine().strip();
            if (sInput.matches("^[1234]$")) {
                choice = Integer.parseInt(sInput);
                isValidChoice = true;
            }
            else {
                System.out.print("Invalid choice. Try again: ");
            }
        }
        return choice;
    }

    /**
     * Solution for task 1
     *
     * @param scnr - The Scanner object to use to read user input
     */
    private static void task1(Scanner scnr) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MMM- yyyy");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy");
        System.out.println(formatter3.format(localDate));
        System.out.println(formatter2.format(localDate));
        System.out.println(formatter.format(localDate));
        Locale locale = new Locale("ES");
        System.out.println("Today is " + localDate.format(formatter3.localizedBy(locale)));

    }

    /**
     * Solution for task 2
     *
     * @param scnr - The Scanner object to use to read user input
     */
    private static void task2(Scanner scnr) {
        LocalDate localDate = LocalDate.now();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        switch(dayOfWeek) {
            case SUNDAY:
                System.out.println("Sunday Funday");

            case FRIDAY:
                System.out.println("TGIF");

            case MONDAY:
                System.out.println("I hate mondays");
            default:
                System.out.println("This isn't an interesting day");
        }
    }

    /**
     * Solution for task 3
     *
     * @param scnr - The Scanner object to use to read user input
     */
    private static void task3(Scanner scnr) {
        //Sets the current date, as both a DateTime, and Date format
        LocalDate dateNow = LocalDate.now();
        LocalDateTime dateTimeNow = LocalDateTime.now();
        //Asks the user for their birthday
        System.out.println("Enter your birthday in the format YYYY-MM-DD");
        //Established Date and DateTime variables for the users birthday
        LocalDate dateOfBirth = LocalDate.parse(scnr.nextLine());
        LocalDateTime birthday = dateOfBirth.atTime(LocalTime.MIDNIGHT);
        //Calculates the period between the birth date and current date
        Period p = Period.between(dateOfBirth,dateNow);
        printStatistics(dateTimeNow, birthday, p);
    }

    /**
     * Takes the given values established in ehe functions previously, and runs several print statements
     * @param dateTimeNow Current date and time
     * @param birthday User supplied value for date of birth.
     * @param p Period between current date and birth date
     *
     */
    private static void printStatistics(LocalDateTime dateTimeNow, LocalDateTime birthday, Period p) {
        System.out.println("You are " + p.getYears() + " years, " + p.getMonths() + " months, and " + p.getDays() + " days old.");
        System.out.println("It has been " + ChronoUnit.WEEKS.between(birthday, dateTimeNow) + " days since you were born");
        System.out.println("It has been " + ChronoUnit.DAYS.between(birthday, dateTimeNow) + " days since you were born");
        System.out.println("It has been " + ChronoUnit.SECONDS.between(birthday, dateTimeNow) + " days since you were born");
    }

}

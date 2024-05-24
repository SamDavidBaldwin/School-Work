/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 *
 * Name: Samuel Baldwin
 * Date: 9/15/21
 * Time: 11:24 PM
 *
 * Project: csci205_labs
 * Package: lab03
 * Class: DateListTester
 * Description: Tester for the DateList Class
 *
 * ****************************************
 */

package lab03;

import java.time.LocalDate;

public class DateListTester {
    public static void main(String[] args) {
        DateList dateList = new DateList();

        System.out.println("Adding some dates...");
        dateList.addDate("1996-12-31");
        dateList.addDate(LocalDate.now());
        dateList.addDate(LocalDate.now().minusMonths(1).minusYears(2));
        dateList.addDate("2022-05-15");
        dateList.addDate(LocalDate.now().minusDays(100));
        dateList.addDate("2008-08-22");

        System.out.printf("There are %d dates in the list%n",dateList.size());
        System.out.println(dateList);

        System.out.println("Looking for " + LocalDate.now()
                                   + ": " + dateList.has(LocalDate.now()));
        System.out.println("Looking for " + "1990-01-01"
                                   + ": " + dateList.has("1990-01-01"));


        // Sorting them...
        System.out.println("Sorting...");
        dateList.sort();
        System.out.println(dateList);

        // Testing out the stream
        dateList.getStream()
                .forEach(d -> System.out.println(d + " is on a " + d.getDayOfWeek()));
        System.out.println("The following are leap years");
        dateList.getStream()
                .filter(d -> d.isLeapYear())
                .forEach(d -> System.out.println(d));

        System.out.println("Goodbye!");
    }
}

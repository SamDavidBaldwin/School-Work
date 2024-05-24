/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 9/21/21
 * Time: 10:23 PM
 *
 * Project: csci205_labs
 * Package: lab03
 * Class: DateList
 *
 * Description: Creating a DateList system, to make an ArrayList of dates
 *
 * ****************************************
 */
package lab03;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class DateList {

    private ArrayList<LocalDate> dates = new ArrayList<>();

    /**
     * Converts elements within ArrayList to string
     *
     * @return string representation of DateList
     */
    public String toString() {
        return ("DateList{" + dates + "}");
    }

    /**
     * Adds date to dates list by converting a string to a LocalDate, and adding
     *
     * @param addedDate takes a string representation to add to the dates list
     */
    public void addDate(String addedDate) {
        dates.add(LocalDate.parse(addedDate));
    }

    /**
     * Adds date to dates list by adding a LocalDate object
     *
     * @param localDate takes a LocalDate obj and adds it to the dates list
     */
    public void addDate(LocalDate localDate) {
        dates.add(localDate);
    }

    /**
     * Gets the size of the dates array
     *
     * @return int size of the dates array
     */
    public int size() {
        return (dates.size());
    }

    /**
     * Sorts the existing dates array
     */
    public void sort() {
        Collections.sort(dates);
    }

    /**
     * Checks if a specific date string is in the array dates
     *
     * @param x Takes a LocalDate Object
     * @return returns True if the search param is in the array, false otherwise
     */
    public boolean has(String x) {
        return (dates.contains(x));
    }

    /**
     * Checks if a specific LocalDate object is in the array dates
     *
     * @param x takse a LocalDate object x
     * @return returns if the  given LocalDate object is within the string
     */
    public boolean has(LocalDate x) {
        return (dates.contains(x));
    }

    /**
     * A method to return the object array dates
     *
     * @return returns the dates array
     */
    public ArrayList<LocalDate> getList() {
        return (dates);
    }

    public Stream<LocalDate> getStream() {
        return (this.dates.stream());
    }
}



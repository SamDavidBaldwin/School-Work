/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 10/18/21
 * Time: 9:00 AM
 *
 * Project: csci205_labs
 * Package: lab06
 * Class: Contractor
 *
 * Description: A contractor object that extends the Payable interface
 *
 * ****************************************
 */
package lab06;

import java.time.LocalDate;

/**
 * Basic contractor object that implements the payable interface
 * @author Samuel Baldwin
 */

public class Contractor implements Payable {
    /**
     * The hourly rate that the contractor is paid
     */
    private double hourlyRate;
    /**
     * The social security number of the contractor
     */
    private int ssNum;
    /**
     * The first name of the contractor
     */
    private String firstName;
    /**
     * The last name of the contractor
     */
    private String lastName;
    /**
     * The ID number assigned to the contractor
     */
    private int ID;

    /**
     * Calculate the amount that should be paid to the contractor
     * @param numHours The amount of hours worked
     * @return The total pay
     */
    @Override
    public double calculatePay(double numHours) {
        return this.getHourlyRate() * numHours;
    }

    /**
     * Gives the name of who a check is paid to
     * @return FirstName, LastName
     */
    @Override
    public String getPayTo() {
        return this.getFirstName() + ", " + this.getLastName();
    }

    /**
     * Gives a short pay memo about the check
     * @return String memo
     */
    @Override
    public String getPayMemo() {
        return "Contractor ID: " + this.getID() + ", Pay Date: " + HRDateUtils.dateToStr(LocalDate.now());
    }

    /**
     * Explicit constructor to create new employee
     * @param ID     Employee id
     * @param firstName First name
     * @param lastName  Last name
     * @param ssNum     Social Security Number
     * @param hourlyRate  Hourly Rate
     */
    public Contractor(int ID, String firstName, String lastName, int ssNum, double hourlyRate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.ssNum = ssNum;
        this.hourlyRate = hourlyRate;

    }


    public int getID() {
        return ID;
    }

    public int getSsNum() {
        return ssNum;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        String s = this.getID() + "," + this.lastName + "," + this.firstName;
        s += String.format(",%09d", this.ssNum);
        s += String.format(",%.2f", this.hourlyRate);
        return s;
    }
}
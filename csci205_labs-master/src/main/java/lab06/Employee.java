/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 *
 *
 * Name: Samuel Baldwin
 * Date: 8/6/2021
 * Time: 8:42 am
 *
 * Project: csci205
 * Package: lab06
 * File: Employee
 * Description:
 * A very basic abstraction for an employee in a simple HR system
 * ****************************************
 */
package lab06;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * A basic representation for an Employee to be stored in an HR database system
 *
 * @author Brian King
 */
public class Employee implements Payable{

    /**
     * Calculator for the amount to pay in a transaction, including overtime pay over 40 hours
     * @param numHours The number of hours to be billed
     * @return The amount to pay the Payable object
     */
    @Override
    public double calculatePay(double numHours) {
        double pay = 0;
        if (numHours > 0){
            if (numHours > 40) {
                pay = Math.round(40 * (this.salary / (40 * 52)) + (numHours - 40) * ((this.salary * 1.5 / (40 * 52))));
            }
            else{
                pay = Math.round(40 * (this.salary / (40 * 52)));
            }
        }
        return pay;

    }

    /**
     *
     * @return The first and last name of the Payable object getting paid
     */
    @Override
    public String getPayTo() {
        return this.getFirstName() + ", " + this.getLastName();

    }

    /**
     *
     * @return A short memo about the Employee that is getting paid
     */
    @Override
    public String getPayMemo() {
        return "Employee ID: " + this.getEmpID() + ", Pay Date: " + HRDateUtils.dateToStr(LocalDate.now());

    }

    /** A factory to generate unique IDs in a safe way*/
    private static class IDFactory{

        /**
         * HashSet of all the ID numbers that have been assigned
         */
        private static HashSet<Integer> setOfAssignedIDs = new HashSet<>();

        /**
         * Function to generate unique ID numbers and assign them to Employees
         * @return A unique ID number
         */
        private static Integer generateID(){
            Integer i = 1;
            while (setOfAssignedIDs.contains(i)) {
                i++;
            }
            setOfAssignedIDs.add(i);
            return i;
        }

        /**
         * Checks if a given ID does not already exist within the system, and is greater than 0
         * If the ID doesn't meet either of these criteria, generates a new ID
         * @param empID The ID to check
         * @return empID
         */
        public static Integer safeToUse(Integer empID){
            if (setOfAssignedIDs.contains(empID) == false && empID >= 1){
                setOfAssignedIDs.add(empID);
                return empID;
            }
            else{
                return generateID();
            }
        }
    }

    /** Employee id */
    private int empID;

    /** First name */
    private String firstName;

    /** Last name */
    private String lastName;

    /** Social Security number */
    private int ssNum;

    /** Date employee was hired */
    private LocalDate hireDate;

    /** Current salary of the employee */
    private double salary;

    /**
     * Explicit constructor to create new employee
     *
     * @param empID     Employee id
     * @param firstName First name
     * @param lastName  Last name
     * @param ssNum     Social Security Number
     * @param hireDate  Hire date (as {@link LocalDate} object
     * @param salary    Current employee salary
     */
    public Employee(int empID, String firstName, String lastName, int ssNum, LocalDate hireDate, double salary) {
        this.empID = IDFactory.safeToUse(empID);
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssNum = ssNum;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    /**
     * @return the employee id
     */
    public int getEmpID() { return empID; }

    /**
     * @return employee first name
     */
    public String getFirstName() { return firstName; }

    /**
     * @return Last name
     */
    public String getLastName() { return lastName; }

    /**
     * @return Social Security number
     */
    public int getSsNum() {
        return ssNum;
    }

    /**
     * @return {@link LocalDate} employee was hired
     */
    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * @return current employee salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Change the name of the employee
     *
     * @param first - New first name
     * @param last - New last name
     */
    public void changeName(String first, String last) {
        this.firstName = first;
        this.lastName = last;
    }

    /**
     * Raise the salary by <code>salaryAdj</code> dollars.
     *
     * @param salaryAdj - amount to add to the current salary
     * @return the new salary
     */
    public double raiseSalary(double salaryAdj) {
        this.salary += salaryAdj;
        return this.salary;
    }

    /**
     * Return a string representation of the Employee
     *
     * @return the String of comma delimited values
     */
    @Override
    public String toString() {
        String s = this.empID + "," + this.lastName + "," + this.firstName;
        s += String.format(",%09d", this.ssNum);
        s += "," + HRDateUtils.dateToStr(this.hireDate);
        s += String.format(",%.2f", this.salary);
        return s;
    }

    /**
     * @param o the other Object to compare
     * @return True if this Employee has the same SS# as another employee
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return ssNum == employee.ssNum;
    }

}


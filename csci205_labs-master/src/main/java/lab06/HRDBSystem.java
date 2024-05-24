/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 *
 * Name: YOUR NAME
 * Date: 10/01/19
 * Time: 1:45 PM
 *
 * Project: csci205_labs
 * Package: lab06
 * Class: HRDBSystem
 * Description:This is a complete class that tests out an implementation of Contractor, Employee, and Manager objects
 * some of which inherits the Payable interface
 *
 * ****************************************
 */

package lab06;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.*;

public class HRDBSystem {

    private static ArrayList<Employee> empList;
    private static ArrayList<Manager> mgrList;
    private static ArrayList<Contractor> contractorList;
    private static Account account;

    public static void main(String[] args) throws ParseException, ManagerException {
        initEmployees();
        initManagers();
        initContractors();
        assignEmployeesToManagers();
        displayEveryone();
        testAcct();
        sortAndPrintEmployeesByName();
        sortAndPrintEmployeesByID();
    }


    private static void testAcct() {
        account = new Account(40000);
        System.out.println(account);
        try{
            System.out.println("Test: Printing a check to employee ID: " + empList.get(1).getEmpID());
            account.prepareCheckandDebit(empList.get(1), 10);
            System.out.println(account.writeCheck());

            System.out.println("Test: Printing a check to contractor ID: " + contractorList.get(0).getID());
            account.prepareCheckandDebit(contractorList.get(0), 35);
            System.out.println(account.writeCheck());

            System.out.println("Test: Printing a check to contractor ID: " + contractorList.get(1).getID());
            account.prepareCheckandDebit(contractorList.get(1), 35);
            System.out.println(account.writeCheck());
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        }
        System.out.println(account);
    }

    /**
     * Method to sort existing employees by last name with streams
     */
    private static void sortAndPrintEmployeesByName(){
        System.out.println("*** Sorted Employee List By Name ***");
        empList.stream()
               .sorted(Comparator.comparing(Employee::getLastName))
                .forEach((s) -> displayEmployee(s));
    }

    /**
     * Method to sort existing employees by ID number with streams
     */
    private static void sortAndPrintEmployeesByID(){
        System.out.println("*** Sorted Employee List By ID***");
        empList.stream()
                .sorted((s1,s2) ->  Double.compare(s1.getEmpID(),s2.getEmpID()))
                .forEach((s) -> displayEmployee(s));
    }

    /**
     * Displays all people within the HR system
     */
    private static void displayEveryone() {
        System.out.println("*** Our complete list ***");
        displayEmployees(empList);
        System.out.println("*** A manager example ***");
        displayManager(mgrList.get(0));
        System.out.println("*** Contractors ***");
        System.out.println(contractorList.get(0));
        System.out.println(contractorList.get((1)));
    }

    /**
     * Initialize new Contractors
     */
    private static void initContractors() {
        contractorList = new ArrayList<>();
        contractorList.add( new Contractor(73, "Builder", "Bob", 342942039, 50.00));
        contractorList.add(new Contractor(007, "James", "Bond", 102951908, 1000000));
    }

    /**
     * Asign existing employees to existing managers
     * @throws ManagerException If the employee being assigned is already in the list
     */
    private static void assignEmployeesToManagers() throws ManagerException {
        mgrList.get(0).addEmployee(empList.get(1));
        mgrList.get(0).addEmployee(empList.get(2));
        mgrList.get(0).addEmployee(empList.get(3));
        mgrList.get(1).addEmployee(empList.get(5));
        mgrList.get(1).addEmployee(empList.get(6));
        mgrList.get(1).addEmployee(empList.get(7));
        mgrList.get(1).addEmployee(empList.get(8));
    }

    /**
     * Initialize the managers
     */
    private static void initManagers() {
        mgrList = new ArrayList<>();
        mgrList.add(new Manager(0, "Pat", "Mather", 101010101, HRDateUtils.strToDate("2015-07-15"), 125000, "ENGINEERING"));
        empList.add(0, mgrList.get(0));

        mgrList.add(new Manager(-1, "John", "Bravman", 121230103,
                                HRDateUtils.strToDate("2010-02-19"), 250000, "ADMIN"));
        empList.add(4, mgrList.get(1));
    }

    /**
     * Initialize the employees
     */
    private static void initEmployees() {
        empList = new ArrayList<>();
        empList.add(new Employee(1, "Brian", "King", 123456789,
                HRDateUtils.strToDate("2010-08-20"), 60000));
        empList.add(new Employee(2, "Chris", "Dancy", 402040302,
                HRDateUtils.strToDate("2015-07-02"), 65000));
        empList.add(new Employee(200, "Florence", "Machine", 149285729,
                HRDateUtils.strToDate("2014-12-01"), 62500));
        empList.add(new Employee(10, "Bonnie", "Raitt", 849324810, HRDateUtils.strToDate("2003-07-20"), 150000));
        empList.add(new Employee(10, "Robert", "Randolph", 121212121, LocalDate.now(), 145000));
        empList.add(new Employee(2, "Jimi", "Hendrix", 000000001, LocalDate.now(), 250000));
        empList.add(new Employee(201, "Nancy", "Wilson", 111111111,
                HRDateUtils.strToDate("1989-02-10"), 60000));
    }

    /**
     * Displays a given employee and the information on that employee
     * @param emp the employee to display
     */
    public static void displayEmployee(Employee emp) {
        System.out.printf("%4d: %s %s", emp.getEmpID(), emp.getFirstName(),
                          emp.getLastName());
        if (emp instanceof Manager) {
            System.out.print(" [MANAGER]");
        }
        System.out.println();
    }

    /**
     * Calls the display employee method on all employees in a list
     * @param listOfEmps the list of employees
     */
    public static void displayEmployees(List<Employee> listOfEmps) {
        for (Employee emp : listOfEmps) {
            displayEmployee(emp);
        }
    }

    /**
     * Helper method to print out details of a Manager, including a list
     * of employees the manager manages.
     *
     * @param mgr The Manager who is being displayed
     */
    public static void displayManager(Manager mgr) {
        System.out.printf("Manager:     %s %s\n", mgr.getFirstName(),
                          mgr.getLastName());
        System.out.printf("Department:  %s\n", mgr.getDeptName());
        System.out.printf("# Employees: %d\n", mgr.getEmpList().size());
        displayEmployees(mgr.getEmpList());
    }



}

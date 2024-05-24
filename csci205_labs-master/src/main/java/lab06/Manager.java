/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 *
 * Name: Samuel Baldwin
 * Date: 9/19/19
 * Time: 1:37 PM
 *
 * Project: csci205_labs
 * Package: lab06
 * Class: Manager
 * Description: A simple abstraction of a Manager object
 *
 * ****************************************
 */

package lab06;

import java.time.LocalDate;
import java.util.*;

/**
 * Checked exception representing any issues that might arise from the Manager
 * class
 *
 */
class ManagerException extends Exception {
    public ManagerException(String message) {
        super(message);
    }
}

public class Manager extends Employee {

    /** The Department the Manager manages*/
    private String departmentName;
    /** The list of Employees the Manager manages*/
    private List<Employee> listManagedEmps;
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
     * Default manager object constructur
     * @param empID             The employee ID
     * @param firstName         The managers first name
     * @param lastName          The managers last name
     * @param ssNum             The manager Social Security number
     * @param hireDate          The date on which the manager was hired
     * @param salary            The managers salary
     * @param departmentName    The department the manager manages
     *
     */
    public Manager(int empID, String firstName, String lastName, int ssNum, LocalDate hireDate, double salary, String departmentName){
        super(empID, firstName, lastName, ssNum,  hireDate, salary);
        this.departmentName = departmentName;
        List<Employee> employeeList = new ArrayList<>();
        this.listManagedEmps = employeeList;
    }
    public List<Employee> getEmpList(){
        return this.listManagedEmps;
    }

    public String getDeptName() {
        return departmentName;
    }

    public void setDeptName(String departmentName) {
        this.departmentName = departmentName;
    }


    public void addEmployee(Employee employee) throws ManagerException{
        for (Employee listManagedEmp : this.listManagedEmps) {
            if (listManagedEmp == employee) {
                String msg = ("This employee is already in the list");
                throw new ManagerException(msg);
            }

        }
        this.listManagedEmps.add(employee);


    }
    public void removeEmployee(Employee employee) throws ManagerException {
        for (int i = 0; i < this.listManagedEmps.size(); i++)
            if (this.listManagedEmps.get(i) == employee) {
                String msg = ("This employee is not in the list");
                throw new ManagerException(msg);
            } else {
                this.listManagedEmps.remove(employee);
            }
    }
    @Override
    public String toString() {
        String s =(super.toString() + "Manager," + this.getDeptName());
        return s;
        }
}

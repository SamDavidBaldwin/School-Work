package lab06;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private static final double EPSILON = 1.0E-10;

    @Test
    void changeName() {
        Employee emp1 = new Employee(1, "A", "B", 1000000000, LocalDate.now(), 10000);
        emp1.changeName("Samuel", "Baldwin");
        assertEquals("Samuel", emp1.getFirstName());
        assertEquals("Baldwin", emp1.getLastName());

    }

    @Test
    void raiseSalary() {
        Employee emp1 = new Employee(1, "A", "B", 1000000000, LocalDate.now(), 10000);
        assertEquals(10000, emp1.getSalary());
        emp1.raiseSalary(1000);
        assertEquals(11000, emp1.getSalary());
        emp1.raiseSalary(-1000);
        assertEquals(10000, emp1.getSalary());
    }


    @Test
    void testEquals() {
        Employee emp1 = new Employee(1, "A", "B", 1000000000, LocalDate.now(), 10000);
        Employee emp2 = new Employee(2, "A", "B", 1000000000, LocalDate.now(), 10000);
        assertEquals(emp2,emp1);
    }

    @Test
    void testIDFactory(){
        Employee emp1 = new Employee(100, "A", "B", 1000000000, LocalDate.now(),10000);
        Employee emp2 = new Employee(100, "A", "B", 1000000000, LocalDate.now(),10000);
        Employee emp3 = new Employee(-100, "A", "B", 1000000000, LocalDate.now(),10000);
        assertEquals(100, emp1.getEmpID());
        assertEquals(1, emp2.getEmpID());
        assertEquals(2, emp3.getEmpID());
    }
    @Test
    void testPayable(){
        Employee emp1 = new Employee(100, "A", "B", 1000000000, LocalDate.now(),52*40*10);
        assertEquals(emp1.getFirstName() + ", " + emp1.getLastName(),emp1.getPayTo());
        assertEquals(400, emp1.calculatePay(40), EPSILON);
        assertEquals(550, emp1.calculatePay(50), EPSILON);
    }
}
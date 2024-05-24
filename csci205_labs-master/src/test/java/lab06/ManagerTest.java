package lab06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private Manager mgr;
    private Employee emp1;
    private Employee emp2;

    @BeforeEach
    void setUp(){
        mgr = new Manager(1,"Erin", "Jablonski", 123456789, HRDateUtils.strToDate("2021-07-15"),100000.0, "Engineering");
        emp1 = new Employee(1, "Brian", "King", 123456789, HRDateUtils.strToDate("2010-08-20"), 60000);
        emp2 = new Employee(2,"Chris","Dancy", 402040302, HRDateUtils.strToDate("2015-07-02"), 65000);

    }

    @Test
    void getEmpList() throws ManagerException {
        ArrayList<Employee> testList = new ArrayList<>();
        testList.add(emp1);
        testList.add(emp2);
        mgr.addEmployee(emp1);
        mgr.addEmployee(emp2);
        assertEquals(testList, mgr.getEmpList());
    }

    @Test
    void getDeptName() {
        assertEquals("Engineering", mgr.getDeptName());
    }

    @Test
    void setDeptName() {
        mgr.setDeptName("Computer Science");
        assertEquals("Computer Science", mgr.getDeptName());
    }

    @Test
    void addEmployee() throws ManagerException {
        mgr.addEmployee(emp1);
        assertEquals(1, mgr.getEmpList().size());
        mgr.addEmployee(emp2);
        assertEquals(2, mgr.getEmpList().size());
    }

    @Test
    void removeEmployee() throws ManagerException {
        mgr.addEmployee(emp1);
        mgr.addEmployee(emp2);
        assertEquals(2, mgr.getEmpList().size());
        mgr.removeEmployee(emp2);
        assertEquals(1, mgr.getEmpList().size());

    }

    @Test
    void addEmployeeException() throws ManagerException{
        mgr.addEmployee(emp1);
        assertThrows(ManagerException.class, () -> mgr.addEmployee(emp1));
    }
}
package lab06;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private static final double EPSILON = 1.0E-10;

    private Account acct;

    private Employee emp1;

    @BeforeEach
    void setUp() {
        //Set up an account to have an initial balance of 1000
        this.acct = new Account(1000.0);

        //Set up the employee to be paid $10/hr
        this.emp1 = new Employee(1, "Brian", "King", 123459876, LocalDate.now(), 10*52*40);

        //Check that the account has balance of 1000
        assertEquals(1000.0, acct.getBalance(),EPSILON);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void credit() {
        assertEquals(1000.0, acct.getBalance(), EPSILON);
        acct.credit(1000);
        assertEquals(2000.0, acct.getBalance(),EPSILON);
    }

    @Test
    void debit() throws InsufficientFundsException {
        assertEquals(1000.0, acct.getBalance(), EPSILON);
        acct.debit(1000);
        assertEquals(0, acct.getBalance(),EPSILON);
    }

    @Test
    void prepareCheckandDebit() throws InsufficientFundsException{
        acct.prepareCheckandDebit(emp1, 40);
        assertEquals(1000-400, acct.getBalance(),EPSILON);

        //Verify check value
        assertEquals(400, acct.getCheckAmount(), EPSILON);
    }

    @Test
    void getCheckAmount() throws InsufficientFundsException {
        acct.prepareCheckandDebit(emp1, 40);
        assertEquals(400,acct.getCheckAmount());
    }

    @Test
    void writeCheck() throws InsufficientFundsException {
        acct.prepareCheckandDebit(emp1, 40);
        String sResult = acct.writeCheck();
        assertTrue(sResult != null);
        assertFalse(sResult.equals(""));
        System.out.println(acct.writeCheck());
    }

    @Test
    void testToString() {
    }

    @Test
    void debitException(){
        assertThrows(InsufficientFundsException.class, () -> this.acct.debit(10000));

    }

}
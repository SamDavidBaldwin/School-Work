package lab05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCashRegisterTest {

    /**Delta constant for checking floating point assertions */
    private static final double EPSILON = 1.0E-12;

    /**Cash register used in every test */
    private SimpleCashRegister register;

    @BeforeEach
    void setUp() {
        this.register = new SimpleCashRegister();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPurchaseCount() {
        int result = register.getPurchaseCount();
        // A new register should have no purchases
        assertEquals(0, result);

        register.scanItem(0.55);
        register.scanItem(1.55);
        assertEquals(2, register.getPurchaseCount());
    }

    @Test
    void getListOfPurchases() {
        // Make sure that a new transaction returns an empty list
        LinkedList<Double> testList = new LinkedList<Double>();
        assertEquals(testList, register.getListOfPurchases());

        //Scan 2 items, and then check if the two lists are equal
        register.scanItem(0.5);
        register.scanItem(1.27);
        testList.add(0.5);
        testList.add(1.27);
        assertEquals(testList, register.getListOfPurchases());
    }

    @Test
    void getTransactionTotal() {
        // Make sure the transaction total is 0
        assertEquals(0.0, register.getTransactionTotal(), EPSILON);

        // Scan 2 items then check again
        register.scanItem(0.55);
        register.scanItem(1.27);
        assertEquals(1.82, register.getTransactionTotal(), EPSILON);
    }

    @Test
    void scanItem() {
        // Make sure the initial number of items is 0
        assertEquals(0, register.getPurchaseCount());

        // Scan 2 items then check if item number is 2
        register.scanItem(0.55);
        register.scanItem(1.27);
        register.scanItem(0);
        assertEquals(3, register.getPurchaseCount());
        assertEquals(1.82, register.getTransactionTotal());
    }

    @Test
    void collectPayment() {
        // Scan 2 items, to get an initial balance
        register.scanItem(0.55);
        register.scanItem(1.27);
        assertEquals(1.82, register.getTransactionTotal(), EPSILON);

        // Pay 1 dollar of the balance, and check if the transaction cost is correct and the amount collected is correct
        register.collectPayment(Money.DOLLAR, 1);
        assertEquals(1.0, register.getPaymentCollected(), EPSILON);
        assertEquals(0.82, register.getTransactionTotal() - register.getPaymentCollected(), EPSILON);
    }

    @Test
    void giveChange() throws ChangeException {
        // Scan 2 items in, and fully pay for the two items with change
        register.scanItem(0.55);
        register.scanItem(1.27);
        register.collectPayment(Money.DOLLAR, 10);
        assertEquals(8.18, register.giveChange(), EPSILON);

    }

    @Test
    @DisplayName("scanItem() - bad scan value")
    void scanItemException(){
        // Scan for a negative price
        assertThrows(IllegalArgumentException.class, () -> register.scanItem(-0.5));

        // Scan for a ridiculously large price
        assertThrows(IllegalArgumentException.class, () -> register.scanItem(10000.00));
    }

    @Test
    @DisplayName("collectPayment() - negative count given")
    void collectPaymentException(){
        // Negative count of money given in change
        assertThrows(IllegalArgumentException.class, () -> register.collectPayment(Money.DIME, -1));
    }

    @Test
    @DisplayName("giveChange() - called with outstanding balance")
    void giveChangeException(){
        // Not enough change is given for a transaction throws ChangeException
        register.scanItem(100.0);
        assertThrows(ChangeException.class, () -> register.giveChange());

    }

    @Test
    void testEquals(){
        // Create a second SimpleCashRegister
        SimpleCashRegister registerToCompare = new SimpleCashRegister();
        assertEquals(register, registerToCompare);

        // Add items, and compare again
        registerToCompare.scanItem(0.5);
        registerToCompare.scanItem(1.27);
        registerToCompare.collectPayment(Money.DOLLAR, 1);
        assertNotEquals(register, registerToCompare);

        register.scanItem(0.5);
        register.scanItem(1.27);
        register.collectPayment(Money.DOLLAR, 1);
        assertEquals(register, registerToCompare);
    }

}

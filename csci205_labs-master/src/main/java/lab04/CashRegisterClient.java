/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 *
 * Name: Samuel Baldwin
 * Date: 9/18/2021
 * Time: 5:40 PM
 *
 * Project: csci205_labs
 * Package: lab04
 * Class: CashRegisterClient
 *
 * Description:
 * This is a complete class that tests out a student
 * implementation of CashRegister and Transaction
 * ****************************************
 */

package lab04;

public class CashRegisterClient {

    public static double EPISILON = 1E-10;
    private static CashRegister reg1;
    private static CashRegister reg2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // First, let's evaluate a transaction
        testTransaction();

        // Now, let's create a couple of cash registers
        createCashRegisters();

        // Check renaming
        testRenamingRegister();

        // Check signing into a register
        testRegisterSignIn();

        // Now, let's test out an actual transaction with one register with payments
        testRegister1Transaction1();

        // Test out a second transaction with payments
        testRegister1Transaction2();

        // Done for the day - sign out both registers
        testSigningOut();

        System.out.println(reg1 + "\n" + reg2);
        System.out.println("CONGRATULATIONS!");
    }

    /**
     * Basic test for a single transaction
     */
    private static void testTransaction() {
        System.out.println("TESTING: Transaction class");
        Transaction trans = new Transaction();
        if (trans.getNumItems() != 0) errMsg("testTransaction() - getNumItems should be 0");
        if (trans.getTotalCost() != 0.0) errMsg("testTransaction() - getTotalCost should be 0.0");
        trans.addItemToTransaction(10);
        trans.addItemToTransaction(2.50);
        if (trans.getNumItems() != 2) errMsg("testTransaction() - getNumItems() expected 2 items");
        if (trans.getTotalCost() - 12.50 > EPISILON) errMsg("testTransaction() - getTotalCost() should be 12.50");
    }

    /**
     * Instantiate and validate two cash registers
     */
    private static void createCashRegisters() {
        System.out.println("TESTING: Constructing 2 CashRegister objects");
        reg1 = new CashRegister();
        if (!reg1.isNotReady()) errMsg("createCashRegisters() - a new register should be in state NOT_READY");
        if (reg1.getCashInDrawer() != 0.0) errMsg("createCashRegisters() - a new register should have no cash!");
        System.out.println(reg1);
        reg2 = new CashRegister("Register 2");
        if (!reg2.getName().equals("Register 2")) errMsg("createCashRegisters() - The name for reg2 should be " +
                                                                 "\"Register 2\"");
        System.out.println(reg2);
    }

    /**
     * Test renaming a register
     */
    private static void testRenamingRegister() {
        System.out.println("TESTING: renaming reg1 to Register 1");
        reg1.setName("Register 1");
        if (!reg1.getName().equals("Register 1")) errMsg("testRenamingRegister - FAIL");
        if (reg2.getName().equals("Register 1")) errMsg("testRenamingRegister - reg2 name should not have changed!");
        System.out.println("Constructed:\n" + reg1 + "\n" + reg2);
    }

    /**
     * Test signing into a register, which should only be allowed if the register is in
     * a valid state to allow signing in
     */
    private static void testRegisterSignIn() {
        // Test - must not allow scanning item before register is ready
        if (reg1.scanItem(10.0))
            errMsg("scanItem should not allow scan before signing in");

        double d;     // Common double for testing
        // Signing in
        System.out.println("TESTING: signing in");
        if (!reg1.signIn(100.0))
            errMsg("reg1.signIn - should be allowed");
        else if (reg1.signIn(100.0))
            errMsg("reg1.signIn - should not allow a second signIn");
        else if (!reg1.isReady())
            errMsg("reg1.isReady - should be in READY state");
        else if ((d = reg1.getCashInDrawer()) != 100.0)
            errMsg("reg1.getCashInDrawer() == %.2f - should be 100.0",d);
        else if ((d = reg1.collectPayment(10)) != Double.MIN_VALUE)
            errMsg("reg1.collectPayment should not be allowed before scanning any items!");

        if (!reg2.signIn(200.0))
            errMsg("reg2.signIn - should be allowed");

        System.out.println("SUCCESS: Both signed in.\n" + reg1 + "\n" + reg2);
    }

    /**
     * Step through an entire transaction and payment on register 1
     */
    private static void testRegister1Transaction1() {
        double d;
        System.out.println("TESTING: reg1 - New transaction: $2.50, $9.95, $5.50 = $17.95");
        if (!reg1.scanItem(2.50) || !reg1.scanItem(9.95) || !reg1.scanItem(5.50))
            errMsg("reg1 scanItem - Fail");
        else if (!reg1.isScanning())
            errMsg("reg1 - isScanning is false, should be true");
        else if ((d = reg1.getAmountOwed()) != 17.95)
            errMsg("reg1.getAmountOwed is wrong. Expected 17.95, ACTUAL = %.2f",d);
        else if (reg1.signOut() >= 0.0)
            errMsg("reg1.signOut should NOT be allowed in the middle of a transaction!");
        else
            System.out.println("SUCCESS: reg1\n" + reg1);

        System.out.println("TESTING: reg1: Making payments of 15.00 then 10.00");
        if ((d = reg1.collectPayment(15.0)) + 2.95 > EPISILON)
            errMsg("reg1.collectPayment(15.0) - expected -2.95, ACTUAL = %.2f",d);
        else if (!reg1.isReceivingPmt())
            errMsg("reg1 should still be in RECEIVE_PMT state because money is still owed");
        else if ((d = reg1.collectPayment(10)) - 7.05 > EPISILON)
            errMsg("reg1.collectPayment(10.0) - expect 7.05 change, ACTUAL = %.2f",d);
        else if (!reg1.isReady())
            errMsg("reg1 should be ready, ACTUAL state = " + reg1.getState());
        else if ((d = reg1.collectPayment(10)) != Double.MIN_VALUE)
            errMsg("reg1.collectPayment should NOT be allowed! It should return Double.MIN_VALUE. " +
                           "ACTUAL = %f",d);
        else if ((d = reg1.getCashInDrawer()) - 117.95 > EPISILON)
            errMsg("reg1 should have 117.95 in drawer. ACTUAL = %.2f",d);
        else
            System.out.println("SUCCESS: reg1\n" + reg1);

    }

    /**
     * Test out a second transaction
     */
    private static void testRegister1Transaction2() {
        double d;
        boolean test;
        System.out.println("TESTING: reg1 - New transaction: $10, $7.50, $19.95, $5 = $42.45");
        test = reg1.scanItem(10) &&
                reg1.scanItem(7.50) &&
                reg1.scanItem(19.95) &&
                reg1.scanItem(5.00);
        if (!test)
            errMsg("reg1 transaction #2 scanning fail");
        else if ((d = reg1.getAmountOwed()) != 42.45)
            errMsg("reg1 getAmountOwed = 42.45. ACTUAL = %.2f",d);
        if ((d = reg1.collectPayment(42.45)) != 0)
            errMsg("reg1.colletPayment(42.45) should be 0, ACTUAL = %.2f",d);
        else if (!reg1.isReady())
            errMsg("reg1.isReady() should be true for transaction #2");
        else if ((d = reg1.getCashInDrawer()) != 160.40)
            errMsg("reg1.getCashInDrawer() should be 160.40. ACTUAL = %.2f",d);
        else
            System.out.println("SUCCESS: reg1\n" + reg1);
    }

    /**
     * Test signing out of register 1, and validate that register2 has not been altered
     */
    private static void testSigningOut() {
        double d;
        System.out.println("TESTING: signing out");
        if ((d = reg1.signOut()) - 160.40 > EPISILON)
            errMsg("reg1.signOut should return 160.40. ACTUAL = %.2f",d);
        else if (!reg1.isNotReady())
            errMsg("reg1.signOut - state should be NOT_READY");
        else if (reg1.getCashInDrawer() != 0.0)
            errMsg("reg1.signOut - cash drawer should be empty");

        if ((d = reg2.signOut()) - 200 > EPISILON)
            errMsg("reg2.signOut - money returned should be 200. ACTUAL = %.2f",d);
    }


    /**
     * This is a little helper function to print an error message
     * and terminate the program
     *
     * @param sMsg - the error message to output
     */
    public static void errMsg(String sMsg) {
        System.err.println("ERROR! - " + sMsg);
        System.exit(1);
    }

    /**
     * Another helper function to allow the user to print a format error message with
     * arguments to pass to print
     *
     * @param sFormat
     * @param fmtArgs
     */
    public static void errMsg(String sFormat, Object... fmtArgs) {
        System.err.format("ERROR! - " + sFormat + "%n", fmtArgs);
        System.exit(1);
    }
}

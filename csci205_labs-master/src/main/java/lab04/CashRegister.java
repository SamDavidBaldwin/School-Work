/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 9/24/21
 * Time: 12:53 AM
 *
 * Project: csci205_labs
 * Package: lab04
 * Class: CashRegister
 *
 * Description: A Cash Register object that handles the behavior of a transaction
 *
 * ****************************************
 */

package lab04;

/**
 * The different states that a cash register can be in
 */
enum CashRegisterState{
    NOT_READY,          // Not ready for a shift
    READY,              // Register ready to start a transaction
    SCANNING,           // Processing a transaction
    RECEIVE_PMNT,       // Receiving payment for a transaction
}

/**
 * Simple class that implements a cash register
 */
public class CashRegister {
    /**
     * The name of the cash register
     */
    private String name;

    /**
     * the amount of cash the register is currently holding
     */
    private double cashInDrawer;

    /**
     * The current transaction or null if there is no transaction taking place
     */
    private Transaction currentTransaction;

    /**
     * The current state of the cash register
     */
    private CashRegisterState state;

    /**
     * The total amount of money collected toward the transaction
     */
    private double paymentRecieved;

    /**
     * Initalize a default empty cash register
     */
    public CashRegister(){
        this.cashInDrawer = 0;
        this.currentTransaction = null;
        this.name = "Default";
        this.state = CashRegisterState.NOT_READY;
        this.paymentRecieved = 0;
    }

    /**
     * Initalize a cash register with a given name
     */
    public CashRegister(String name){
        this.cashInDrawer = 0;
        this.currentTransaction = null;
        this.name = name;
        this.state = CashRegisterState.NOT_READY;
        this.paymentRecieved = 0;
    }

    public boolean isNotReady(){
        return(this.state == CashRegisterState.NOT_READY);
    }

    public boolean isReady(){
        return(this.state == CashRegisterState.READY);
    }

    public boolean isScanning(){
        return(this.state == CashRegisterState.SCANNING);
    }

    public boolean isReceivingPmt(){
        return(this.state == CashRegisterState.RECEIVE_PMNT);
    }

    public double getPaymentRecieved() {
        return paymentRecieved;
    }

    public CashRegisterState getState() {
        return state;
    }

    public double getCashInDrawer() {
        return cashInDrawer;
    }

    public Double getAmountOwed(){
        return this.currentTransaction.getTotalCost();
    }

    public String getName() {
        return name;
    }
    public String toString(){
       return("CashRegister{sName = '" + this.name  + "' , cashInDrawer = "+ getCashInDrawer() + ", currentTransaction = " + currentTransaction + ", paymentRecieved = "+  getPaymentRecieved()+ ", state = " + getState() + "}");
    }

    public void setCashInDrawer(double cashInDrawer) {
        this.cashInDrawer = cashInDrawer;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPaymentRecieved(double paymentRecieved) {
        this.paymentRecieved = paymentRecieved;
    }

    public void setState(CashRegisterState state) {
        this.state = state;
    }

    /**
     * Checks if the register can scan at this time, and scans an item into the transaction if true
     * @param itemCost takes the cost of an item
     * @return true if the register is ready or in the process of scanning items
     */
    public boolean scanItem(double itemCost){
        if (this.state == CashRegisterState.READY || this.state == CashRegisterState.SCANNING){
            this.currentTransaction.addItemToTransaction(itemCost);
            this.state = CashRegisterState.SCANNING;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * method to sign in to the register, only if the state is NOT_READY
     * @param initalCash takes a value to set the initial cash in the register to
     * @return True if the register can be signed into, false otherwise
     */
    public boolean signIn(double initalCash){
        if (this.state == CashRegisterState.NOT_READY){
            this.cashInDrawer = initalCash;
            this.state = CashRegisterState.READY;
            this.currentTransaction = new Transaction();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * method to collect payment towards the current transaction
     * @param paymentRecieved amount of money recieved towards the transaction
     * @return the current balance of the transaction or Double.MIN_VALUE if there are no items in the transaction
     */
    public double collectPayment(double paymentRecieved){
        if(this.currentTransaction.getNumItems() <= 0){
            return Double.MIN_VALUE;
        }
        if(this.currentTransaction.getTotalCost() > 0){
            this.state = CashRegisterState.RECEIVE_PMNT;
            this.paymentRecieved = paymentRecieved;
            this.cashInDrawer += paymentRecieved;
            double currentBalance = -1 * this.currentTransaction.getTotalCost();
            this.currentTransaction.receivePayment(paymentRecieved);
            currentBalance = currentBalance + paymentRecieved;
            currentBalance = (Math.round(currentBalance * 100)) / 100.0;
            currentBalance = clearBalance(currentBalance);
            return currentBalance;
        }
        else{
            return Double.MIN_VALUE;
        }
    }

    /**
     * A function to handle change given once enough money has been given
     * @param currentBalance The amount of change that is due to the customer
     * @return balance after change has been given
     */
    private double clearBalance(double currentBalance) {
        if (currentBalance >= 0) {
            this.cashInDrawer -= currentBalance;
            currentBalance = 0;
            this.state = CashRegisterState.READY;
            this.currentTransaction.setTotalCost(0);
        }
        return currentBalance;
    }


    /**
     * method to sign out of the register
     * @return the amount of cas in the register at the time of signout.
     */
    public double signOut(){
        if (this.state == CashRegisterState.SCANNING) {
            return -1;
        }
        else{
            double cashInDrawer = getCashInDrawer();
            setCashInDrawer(0);
            this.state = CashRegisterState.NOT_READY;
            return cashInDrawer;
        }
    }
}
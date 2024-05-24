/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 10/17/21
 * Time: 5:14 PM
 *
 * Project: csci205_labs
 * Package: lab06
 * Class: Account
 *
 * Description: A class that models a simple account
 *
 * ****************************************
 */
package lab06;
/** An exception to be thrown when there are not enough funds in an account to finish a transaction*/
class InsufficientFundsException extends  Exception{
    public InsufficientFundsException(String message) {
        super(message);
    }
}

/**
 * Main account class that models an account object
 * @author Samuel Baldwin
 */
public class Account {
    /** The balance of an account*/
    private double balance;
    /** The last Payable object that has been through the system*/
    private Payable lastPayee;
    /** The amount paid in the last transaction*/
    private double lastAmountPaid;

    /**
     * Default constructor of an account object
     * @param initBalance the initial balance within the account
     */
    public Account(double initBalance){
        this.balance = initBalance;

    }
    public double getBalance(){
        return this.balance;
    }

    /**
     * Method to add money to an account through a transaction
     * @param amount the amount of money to be added
     */
    public void credit(double amount){
        this.balance += amount;
    }

    /**
     * Method to remove money from an account through a transaction
     * @param amount The amount to be withdrawn
     * @throws InsufficientFundsException If there is not enough money in the account withdrawing, throws
     */
    public void debit(double amount) throws InsufficientFundsException{
        if (this.balance < amount){
            System.out.println("INSUFFICIENT FUNDS! Required: " + amount + " Available: " + this.balance);
            throw new InsufficientFundsException("INSUFFICIENT FUNDS! Required: " + amount + " Available: " + this.balance);
        }
        else {
            this.balance -= amount;
        }
    }

    /**
     * Prepares a check, and calls the debit method on a transaction
     * @param payee the Payable object that will be paying
     * @param hoursBilled The number of hours that are being billed for
     * @throws InsufficientFundsException If there is not enough money in the account withdrawing, throws
     */
    public void prepareCheckandDebit(Payable payee, double hoursBilled) throws InsufficientFundsException {
        double amntToBePaid =payee.calculatePay(hoursBilled);
        this.lastPayee = payee;
        this.lastAmountPaid = amntToBePaid;
        this.debit(amntToBePaid);


    }

    public double getCheckAmount(){
        return this.lastAmountPaid;
    }

    /**
     * Writes a check string to be printed
     * @return A string that mimics a check
     */
    public String writeCheck(){
        return "Pay to: " + lastPayee.getPayTo() + "\nPay memo: " + lastPayee.getPayMemo() +"\nPay amount: " + this.lastAmountPaid;
    }

    public String toString(){
        return("The current balance of this account is $"+this.balance);

    }

}
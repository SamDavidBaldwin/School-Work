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
 * Class: Transaction
 *
 * Description: A program that models the behavior of a transaction
 *
 * ****************************************
 */
package lab04;

/**
 * Simple class that models a transaction
 *
 * @author Samuel Baldwin
 */
public class Transaction {
    /**
     * Number of items in the transaction
     */
    private int numItems;
    /**
     * Total cost of the transaction
     */
    private double totalCost;

    public Transaction(){
        totalCost = 0;
        numItems = 0;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public int getNumItems(){
        return this.numItems;
    }

    public double getTotalCost(){
        return this.totalCost;
    }

    /**
     * Method for adding an item to a transaction
     * @param itemCost takes the cost of the item being added
     * @return the total cost of that transaction
     */
    public double addItemToTransaction(double itemCost){
        this.numItems = getNumItems() + 1;
        this.totalCost = getTotalCost() + itemCost;
        return totalCost;
    }

    public String toString(){
        return("Transaction{numItems = " + getNumItems() + ", transactionTotal = " + getNumItems());
    }

    /**
     * Simple method to handle payment on transactions.
     * @param payment payment given towards the transaction
     * @return adjusted cost of the transaction
     */
    public double receivePayment(double payment){
        this.totalCost = getTotalCost() - payment;
        return this.totalCost;
    }
}
/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 *
 * Name: YOUR NAME
 * Date: 9/11/21
 * Time: 2:40 PM
 *
 * Project: csci205_labs
 * Package: lab05
 * Class: Money
 * Description:
 *
 * This is part of a larger exercise in this lab. This class design is adapted
 * from an old CSCI 204 lab that was originally designed by former
 * Bucknell CS Professor Rick Zaccone.
 * ****************************************
 */

package lab05;

/**
 * A simple enum type to represent distinct denominations of money.
 */
public enum Money {
    PENNY(0.01, "penny"),
    NICKEL(0.05, "nickel"),
    DIME(0.1, "dime"),
    QUARTER(0.25, "quarter"),
    HALF(0.5, "half"),
    DOLLAR(1.0, "dollar"),
    FIVE(5.0, "five"),
    TEN(10.0, "ten"),
    TWENTY(20.0, "twenty");

    private double value;
    private String name;

    /**
     * Constructs a new currency value.
     *
     * @param value the value of this currency
     */
    Money(double value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Gets the value of this currency.
     *
     * @return value of the currency
     */
    public double getValue() {
        return value;
    }

    /**
     * Gets the name of this currency.
     *
     * @return the name of the currency
     */
    public String getName() {
        return name;
    }
}

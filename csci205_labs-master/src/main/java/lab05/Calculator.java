/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 10/3/21
 * Time: 11:23 PM
 *
 * Project: csci205_labs
 * Package: lab05
 * Class: Calculator
 *
 * Description: A simple calculator that uses MathOp expressions
 *
 * ****************************************
 */
package lab05;

/**
 * A calculator class using MathOp expressions, that handles the behavior of a user utilization
 * @author Samuel Baldwin
 */
public class Calculator {
    /**
     * The first operand in an expression
     */
    private int operand1;
    /**
     * The second operand in an expression
     */
    private int operand2;
    /**
     * The type of expression to be evaluated
     */
    private MathOp op;
    /**
     * The name correlated to the type of operation
     */
    private String name;

    /**
     * General constructor for a new equation
     * @param operator Type of operation to be evaluated
     * @param opName Name of the operation
     */
    public Calculator(MathOp operator, String opName){
        this.op = operator;
        this.name = opName;
    }

    /**
     * Sets the values to be calculated
     * @param operand1 the first value in the operation
     * @param operand2 the second value in the operation
     */
    public void setOperands(int operand1, int operand2){
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    /**
     * Sets the operator type to evaluate
     * @param operator the type of operation wanted
     */
    //TODO Change to a switch operation
    public void setOperator(MathOp operator){
        this.op = operator;
        if (operator == BinaryOperators.comb){
            this.name = "comb";
        }
        if (operator == BinaryOperators.perm){
            this.name = "perm";
        }
        if (operator == BinaryOperators.gcd){
            this.name = "gcd";
        }
        if (operator == BinaryOperators.lcm){
            this.name = "lcm";
        }
    }

    /**
     * Gets the answer for an operation
     * @return returns the result of the given function evaluated for operand 1 and 2
     * @throws ArithmeticException If either number is 0, throws exception
     */
   public int getAnswer() throws ArithmeticException{
        if (this.operand1 == 0 || this.operand2 == 0){
            throw new ArithmeticException();
        }
        return(op.func(operand1,operand2));
    }

    public MathOp getOperator(){
        return this.op;
    }
    public int getOperand1(){ return this.operand1;}
    public int getOperand2(){
        return this.operand2;
    }

    @Override
    public String toString(){
        if (this.getOperand2() == 0 || this.getOperand1() == 0){
            return( "name(?, ?) = ?");
        }
        else {
            return (this.name + "(" + operand1 + ", " + operand2 + ") = " + op.func(operand1, operand2));
        }
    }
}
/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 10/3/21
 * Time: 11:02 PM
 *
 * Project: csci205_labs
 * Package: lab05
 * Class: MathOp
 *
 * Description: Simple Functional Interface MathOp
 *
 * ****************************************
 */
package lab05;

@FunctionalInterface
public interface MathOp{
    int func(int x, int y);
}

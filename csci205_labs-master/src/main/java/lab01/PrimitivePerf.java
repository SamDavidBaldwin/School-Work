/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 * Instructor: Brian King
 * Section: 8:30 - MWF
 *
 * Name: Samuel Baldwin
 * Date: 9/5/2021
 *
 * Lab / Assignment: lab01
 *
 * Description:
 * This is a simple program to help you understand wrapper classes and autoboxing,
 * and understand that object convenience may come at a computational cost.
 * *****************************************/

package lab01;

public class PrimitivePerf {

    /** The quantity of numbers to compute a sum for */
    private static final int MAX_NUMBERS = 10000000;

    /** MAIN PROGRAM */
    public static void main(String[] args) {
        // Our time durations we will use to store our results
        long primitiveDuration = 0L;
        long wrappedDuration = 0L;

        // Evaluate the test with primitive types
        long ts = System.nanoTime();
        long primitiveResult = testPrimitive();
        primitiveDuration = System.nanoTime() - ts;
        System.out.println("Primitive:\nAnswer = " + primitiveResult + ". Time = " + primitiveDuration + " ns");

        // Now, evaluate the test with the wrapper class type
        ts = System.nanoTime();
        Long wrappedResult = testWrapped();
        wrappedDuration = System.nanoTime() - ts;
        double comp = (1.0*wrappedDuration/primitiveDuration);

        System.out.println("Wrapped:\nAnswer = " + wrappedResult + ". Time = " + wrappedDuration + " ns");
        System.out.print("Primitive types used ");
        System.out.printf("%.1f", comp );
        System.out.print("% of the time of the wrapper objects");

        // TODO - Print results, timing, and pct difference between times

    }

    /**
     * Simple function to evaluate the speed of adding numbers that are
     * primitive types
     */
    public static long testPrimitive() {
        // Store the result as a primitive type
        long sum = 0L;
        for (int i = 0; i < MAX_NUMBERS; i++) {
            sum = sum + i;
        }

        return sum;
    }

    /**
     * Simple function to evaluate the speed of adding numbers that are stored
     * as an object.
     */
    public static Long testWrapped() {
        // Store the result as an object Long, not the primitive type!
        Long objSum = 0L;
        for (int i = 0; i < MAX_NUMBERS; i++) {
            objSum = objSum + i;
        }

        return objSum;
    }
}

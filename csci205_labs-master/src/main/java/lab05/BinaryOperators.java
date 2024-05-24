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
 * Class: BinaryOperators
 *
 * Description: A couple Binary Operator methods that implement MathOp
 *
 * ****************************************
 */
package lab05;

/**
 * A series of 4 lambda expressions
 *
 * @author Samuel Baldwin
 */
class BinaryOperators{
    /**
     * Finds the choose value of xCy
     */
    public static MathOp comb = (x,y) ->{
        long top = Factorial(x);
        long bottom1 = Factorial(y);
        long bottom2 = Factorial(x-y);
        return (int) ((top)/(bottom1*bottom2));
    };

    private static long Factorial(int n) {
        long factorial =1;
        for(int i = 1; i < n+1; i++) {
            factorial *= i;
        }
        return factorial;
    }
    /**
     * Returns the permutations of x and y
     */
    public static MathOp perm = (x,y) -> solvePermutations(x, y);

    private static int solvePermutations(int x, int y) {
        long top = Factorial(x);
        long bottom = Factorial(x-y);
        return (int) (top/bottom);
    }

    /**
     * Returns the greatest common divisor of x and y
     */
    public static MathOp gcd  = (x,y) -> {
            int currMaxDiv = 1;
            if (x > y) {
                for (int i = 1; i < y+1; i++) {
                    if (x % i == 0 && y % i == 0) {
                        currMaxDiv = i;
                    }
                }
            } else {
                for (int i = 1; i < x+1; i++) {
                    if (x % i == 0 && y % i == 0) {
                        currMaxDiv = i;
                    }
                }
            }
            return currMaxDiv;
        };
    /**
     * Returns the lowest common multiple of x and y
     */
    public static MathOp lcm = (x,y) -> {
            int adjX = Math.abs(x);
            int adjY = Math.abs(y);
            int larger = Math.max(adjX, adjY);
            int smaller = Math.min(adjX, adjY);
            int multiple = larger;
            while (multiple % smaller != 0){
                multiple += larger;
            }
            return multiple;
        };
}
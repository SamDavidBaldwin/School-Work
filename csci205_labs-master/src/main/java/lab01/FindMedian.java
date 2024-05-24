/* *****************************************
*CSCI205 -Software Engineering and Design
* Fall 2021
* Instructor: Brian R. King
* Section: MWF 1 - 8:30am
*
* Name: Samuel Baldwin
* Date: 9/6/2021
*
* Lab / Assignment: Lab01
*
* Description: A simple program to find the median
* of an array without sorting it.
* *****************************************/
package lab01;
import java.util.*;

public class FindMedian{
  public static final int MAX_INT = 30;
  public static final int SIZE = 21;

  public static void main(String[] args) {
    int [] numArray = initArray(SIZE);
    int median = (SIZE/2);
    int [] freqArray = new int[MAX_INT];
    int temp = 0;
    for(int i=0;i<SIZE;i++){
      temp = numArray[i];
      freqArray[temp] += 1;
    }
    for(int i=0;i<MAX_INT; i++){
      if (freqArray[i] >= 0 && median > 0){
        median -= freqArray[i];
      }
      else{
        median =  i;
        break;
      }
    }
    System.out.print("Array:\n[");
    for (int i=0; i<SIZE-1; i++){
      System.out.print(numArray[i]+", ");
    }
    System.out.println(numArray[SIZE-2] + "]");
    System.out.println("Median:\n"+median);

  }



  /*** Create and initialize an array of size with
  * random integers between 0 and MAX_INT inclusive.
  *
  * @param size of the array to fill
  * @return an int array of size random numbers between 0 and MAX_INT
  */
  public static int[] initArray(int size){
    int result[] = new int[size];
    Random rng = new Random(1000);
    for (int i =0; i<size; i++){
      result[i] = rng.nextInt(MAX_INT+1);
    }
    return result;

  }



}

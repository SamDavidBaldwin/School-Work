/* *****************************************
*CSCI205 -Software Engineering and Design
* Fall 2021
* Instructor: Brian R. King
* Section: MWF 1 - 8:30am
*
* Name: Samuel Baldwin
* Date: 9/3/2021
*
* Lab / Assignment: Lab00 / Die Simulator
*
* Description:
* A simple weighted die simulator
* *****************************************/

package lab00;
import java.util.*;

public class DieSimulator{

  public static Random rng;

  public static final int MAX_ROLLS = 1000000;

  public static void main(String[] args) {

    rng = new Random(1000);
    Scanner scnr = new Scanner(System.in);
    int roll;
    double totalOfAllRolls = 0;

    int biasedFaceValue = askUserForBiasedFaceValue(scnr);
    double biasProbability = askUserForProbability(scnr);

    for(int i = 0; i < MAX_ROLLS; i++){
      double p = rng.nextDouble();

      if (p <= biasProbability){
        roll = biasedFaceValue;
        totalOfAllRolls += roll;
      }
      else{
        roll = rng.nextInt(6) + 1;
        if (roll == biasedFaceValue){
          roll = rng.nextInt(6) +1;
        }
        totalOfAllRolls += roll;
        //gen random number between 1 and 6, if generated number is biasedFaceValue reroll
      }


    }
    System.out.println("Average: " + totalOfAllRolls/MAX_ROLLS);

    }

  private static int askUserForBiasedFaceValue(Scanner scnr){
    boolean isDoneBias = false;
    int biasedFaceValue = 0;
    while (!isDoneBias){
      System.out.println("Which face value is biased?");
      biasedFaceValue =  scnr.nextInt();
      if ((biasedFaceValue < 1)||(biasedFaceValue > 6)){
        System.out.println("Enter a face value 1-6 only!");
      }
      else{
        isDoneBias = true;
      }
    }
    return biasedFaceValue;
    }

  private static double askUserForProbability(Scanner scnr){
    boolean isDoneProb = false;
    double probability = 0.0;
    while (!isDoneProb){
      System.out.println("Probability of this value?");
      probability = scnr.nextDouble();
      if ((probability > 1.0)|| (probability < 0.0)){
        System.out.println("Enter a value between 0.0 and 12.0!");
      }
      else{
        isDoneProb = true;
      }
      }
    return probability;
    }


    }

/* *****************************************
*CSCI205 -Software Engineering and Design
* Fall 2021
* Instructor: Brian R. King
* Section: MWF 1 - 8:30am
*
* Name: Samuel Baldwin
* Date: 9/3/2021
*
* Lab / Assignment: Lab00
*
* Description:
* A very basic Hello World program
* *****************************************/
package lab00;


public class Hello
{
  public static void main(String[] args) {
    long startTime = System.nanoTime();
    long elapsedNanos = System.nanoTime() - startTime;
    System.out.println("Programming is not a spectator sport!\n Time to execute " + elapsedNanos + " ms");
    System.exit(0);
  }
}

/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Giles Thomas
 * Section: 01 at 8:30am
 * Date: 11/15/2021
 * Time: 8:30 AM
 *
 * Project: csci205_final_project
 * Package: main
 * Class: JMusicTest
 *
 * Description:
 *
 * ****************************************
 */
package main.experiments;

import jm.JMC;
import jm.music.data.*;
import jm.util.Play;

public final class JMusicTest implements JMC {

    public static void main(String[] args) {
            Play.midi(new Note(C4, QN));
    }
}


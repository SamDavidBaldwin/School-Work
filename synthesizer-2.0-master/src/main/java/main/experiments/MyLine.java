/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Giles Thomas
 * Section: 01 at 8:30am
 * Date: 11/19/2021
 * Time: 8:48 AM
 *
 * Project: csci205_final_project
 * Package: main
 * Class: jMusicRealTime
 *
 * Description:
 *
 * ****************************************
 */
package main.experiments;

import jm.JMC;
import jm.music.data.*;
import jm.audio.*;
import jm.music.rt.*;

public final class MyLine extends RTLine implements JMC {

    public static void main(String[] args) {
        SineInstRT sineInst = new SineInstRT(44100, 2, new double[]{0.0, 1.0, 1.0, 1.0});
        sineInst.setFrequency(440);
        new MyLine(new Instrument[] {sineInst});
    }

    public MyLine(Instrument[] insts) {
        super(insts);
        RTMixer mixer = new RTMixer(new RTLine[] {this});
        mixer.begin();
    }

    public Note getNextNote() {
        Note n = new Note((int)(Math.random() * 12 + 60), 5000);
        return n;
    }

    public synchronized void externalAction(Object obj, int actionNumber){

    }
}

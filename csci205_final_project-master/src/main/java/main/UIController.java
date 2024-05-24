/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin, Giles Thomas, Griffin Miller, Rah Hite
 * Section: MWF 8:30am
 * Date: Final 12/8/21
 * Time: 11:59 PM
 *
 * Project: csci205_final_project
 * Package: main
 * Class: UIController
 *
 * Description: The UI handlers for the Synthesizer system
 *
 * ****************************************
 */
package main;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Rectangle;

import jm.JMC;
import jm.music.data.*;
import jm.util.Play;


public class UIController implements JMC{

    @FXML
    private URL location;

    /**
     * The rectangle that acts as the A-key
     */
    @FXML
    private Rectangle aKey;

    /**
     * The label that acts as the score record for A notes played
     */
    @FXML
    private Label aLabel;

    /**
     * The rectangle that acts as the ASharp-key
     */
    @FXML
    private Rectangle aSharpKey;

    /**
     * The rectangle that acts as the B-key
     */
    @FXML
    private Rectangle bKey;

    /**
     * The label that acts as the score record for A notes played
     */
    @FXML
    private Label bLabel;

    /**
     * The button that changes octaves down
     */
    @FXML
    private Button btnOctaveDown;

    /**
     * The button that changes octaves up
     */
    @FXML
    private Button btnOctaveUp;

    /**
     * The button that toggles recording on and off
     */
    @FXML
    private ToggleButton btnRecord;

    /**
     * The button that resets the score
     */
    @FXML
    private Button btnReset;

    /**
     * The rectangle that acts as the C-key
     */
    @FXML
    private Rectangle cKey;

    /**
     * The label that acts as the score record for C notes played
     */
    @FXML
    private Label cLabel;

    /**
     * The rectangle that acts as the CSharp-key
     */
    @FXML
    private Rectangle cSharpKey;

    /**
     * The rectangle that acts as the D-key
     */
    @FXML
    private Rectangle dKey;

    /**
     * The label that acts as the score record for D notes played
     */
    @FXML
    private Label dLabel;

    /**
     * The rectangle that acts as the DSharp-key
     */
    @FXML
    private Rectangle dSharpKey;

    /**
     * The rectangle that acts as the E-key
     */
    @FXML
    private Rectangle eKey;

    /**
     * The label that acts as the score record for E notes played
     */
    @FXML
    private Label eLabel;

    /**
     * The rectangle that acts as the F-key
     */
    @FXML
    private Rectangle fKey;

    /**
     * The label that acts as the score record for E notes played
     */
    @FXML
    private Label fLabel;

    /**
     * The rectangle that acts as the FSharp-key
     */
    @FXML
    private Rectangle fSharpKey;

    /**
     * The rectangle that acts as the G-key
     */
    @FXML
    private Rectangle gKey;

    /**
     * The label that acts as the score record for E notes played
     */
    @FXML
    private Label gLabel;

    /**
     * The rectangle that acts as the GSharp-key
     */
    @FXML
    private Rectangle gSharpKey;

    /**
     * The checkbox that handles sustain
     */
    @FXML
    private CheckBox infNoteCheck;

    /**
     * Label for the volume slider
     */
    @FXML
    private Label lblVolume;

    /**
     * The slider that handles the output volume
     */
    @FXML
    private Slider sliderVolume;

    /**
     * The slider that handles the length of notes
     */
    @FXML
    private Slider sliderNoteLength;

    /**
     * The label that shows the current key being hovered over
     */
    @FXML
    private Label statusLabel;


    /**
     * The default for the label that shows the note that is being hovered
     */
    private String defaultStatusLabel = "Note: ";

    /**
     * The default note length
     */
    private double noteLength = 1.0;
    /**
     * The initial volume level
     */
    private int volumeLevel = 85;
    /**
     * The value indicating the starting octave
     */
    private int octaveValue = 0;

    /**
     * These are all the default Jmusic note values for the scale
     */
    public int cDefault = 60;
    public int cSharpDefault = 61;
    public int dDefault = 62;
    public int dSharpDefault = 63;
    public int eDefault = 64;
    public int fDefault = 65;
    public int fSharpDefault = 66;
    public int gDefault = 67;
    public int gSharpDefault = 68;
    public int aDefault = 69;
    public int aSharpDefault = 70;
    public int bDefault = 71;

    /**
     * The array of strings of which the score is composed
     */
    public String[] score;
    /**
     * Indicates whether the program is or isn't recording the notes played on the score
     */
    public boolean recordOn = false;


    @FXML
    void initialize() {
        assert aKey != null : "fx:id=\"aKey\" was not injected: check your FXML file 'testui.fxml'.";
        assert aLabel != null : "fx:id=\"aLabel\" was not injected: check your FXML file 'testui.fxml'.";
        assert aSharpKey != null : "fx:id=\"aSharpKey\" was not injected: check your FXML file 'testui.fxml'.";
        assert bKey != null : "fx:id=\"bKey\" was not injected: check your FXML file 'testui.fxml'.";
        assert bLabel != null : "fx:id=\"bLabel\" was not injected: check your FXML file 'testui.fxml'.";
        assert btnOctaveDown != null : "fx:id=\"btnOctaveDown\" was not injected: check your FXML file 'testui.fxml'.";
        assert btnOctaveUp != null : "fx:id=\"btnOctaveUp\" was not injected: check your FXML file 'testui.fxml'.";
        assert btnRecord != null : "fx:id=\"btnRecord\" was not injected: check your FXML file 'testui.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'testui.fxml'.";
        assert cKey != null : "fx:id=\"cKey\" was not injected: check your FXML file 'testui.fxml'.";
        assert cLabel != null : "fx:id=\"cLabel\" was not injected: check your FXML file 'testui.fxml'.";
        assert cSharpKey != null : "fx:id=\"cSharpKey\" was not injected: check your FXML file 'testui.fxml'.";
        assert dKey != null : "fx:id=\"dKey\" was not injected: check your FXML file 'testui.fxml'.";
        assert dLabel != null : "fx:id=\"dLabel\" was not injected: check your FXML file 'testui.fxml'.";
        assert dSharpKey != null : "fx:id=\"dSharpKey\" was not injected: check your FXML file 'testui.fxml'.";
        assert eKey != null : "fx:id=\"eKey\" was not injected: check your FXML file 'testui.fxml'.";
        assert eLabel != null : "fx:id=\"eLabel\" was not injected: check your FXML file 'testui.fxml'.";
        assert fKey != null : "fx:id=\"fKey\" was not injected: check your FXML file 'testui.fxml'.";
        assert fLabel != null : "fx:id=\"fLabel\" was not injected: check your FXML file 'testui.fxml'.";
        assert fSharpKey != null : "fx:id=\"fSharpKey\" was not injected: check your FXML file 'testui.fxml'.";
        assert gKey != null : "fx:id=\"gKey\" was not injected: check your FXML file 'testui.fxml'.";
        assert gLabel != null : "fx:id=\"gLabel\" was not injected: check your FXML file 'testui.fxml'.";
        assert gSharpKey != null : "fx:id=\"gSharpKey\" was not injected: check your FXML file 'testui.fxml'.";
        assert infNoteCheck != null : "fx:id=\"infNoteCheck\" was not injected: check your FXML file 'testui.fxml'.";
        assert lblVolume != null : "fx:id=\"lblVolume\" was not injected: check your FXML file 'testui.fxml'.";
        assert sliderNoteLength != null : "fx:id=\"sliderNoteLength\" was not injected: check your FXML file 'testui.fxml'.";
        assert sliderVolume != null : "fx:id=\"sliderVolume\" was not injected: check your FXML file 'testui.fxml'.";
        assert statusLabel != null : "fx:id=\"statusLabel\" was not injected: check your FXML file 'testui.fxml'.";

        addKeyPresses();
        addKeytracking();
        addOctaves();
        initSliderHandlers();
        initScore();

        btnReset.setOnAction(event -> initScore());
        if(recordOn == false){
            btnRecord.setOnAction(event -> recordingSwitch());
        }
    }

    /**
     * Handles the switch of recording on and off, and resets the score when turning off recording
     */
    private void recordingSwitch(){
        if(recordOn == true){
            initScore();
            recordOn = false;
            btnRecord.setText("Recording Stopped");
        }
        else{
            recordOn = true;
            btnRecord.setText("Recording...");
        }
    }

    /**
     * Initializes a new Array of strings, that act as a score, and sets the first value of the score
     */
    private void initScore(){
        score = new String[7];
        for(int k =0; k<7; k++) {
            score[k] = "_";
        }
        cLabel.setText(score[0]);
        dLabel.setText(score[1]);
        eLabel.setText(score[2]);
        fLabel.setText(score[3]);
        gLabel.setText(score[4]);
        aLabel.setText(score[5]);
        bLabel.setText(score[6]);
    }

    /**
     * Each time that a note is played, updates the score with a _ for all notes that aren't being played
     * @param note the note that is being played
     */
    private void updateScore(int note){
        if(recordOn == true){
            for (int i = 0; i < 7; i++) {
                if (i != note) {
                    score[i] += "_";
                }
            }
            cLabel.setText(score[0]);
            dLabel.setText(score[1]);
            eLabel.setText(score[2]);
            fLabel.setText(score[3]);
            gLabel.setText(score[4]);
            aLabel.setText(score[5]);
            bLabel.setText(score[6]);
        }
    }

    /**
     * Handles the adding of the 'o' to indicate a specific note being played
     * @param note The key that is pressed to play a note
     */
    private void addPlayedNoteToScore(int note){
        if(recordOn == true){
            int value = (note + octaveValue)%12;
            System.out.println(value);
            switch(value) {
                case 0:
                    score[0] += "o";
                    updateScore(0);
                    break;
                case 1:
                    score[0] += "o#";
                    updateScore(0);
                    updateScore(0);

                    break;
                case 2:
                    score[1] += "o";
                    updateScore(1);

                    break;
                case 3:
                    score[1] += "o#";
                    updateScore(1);
                    updateScore(1);

                    break;
                case 4:
                    score[2] += "o";
                    updateScore(2);

                    break;
                case 5:
                    score[3] += "o";
                    updateScore(3);

                    break;
                case 6:
                    score[3] += "o#";
                    updateScore(3);
                    updateScore(3);

                    break;
                case 7:
                    score[4] += "o";
                    updateScore(4);

                    break;
                case 8:
                    score[4] += "o#";
                    updateScore(4);
                    updateScore(4);

                    break;
                case 9:
                    score[5] += "o";
                    updateScore(5);
                    break;
                case 10:
                    score[5] += "o#";
                    updateScore(5);
                    updateScore(5);
                    break;
                case 11:
                    score[6] += "o";
                    updateScore(6);
                    break;
            }
        }
    }

    /**
     * Handles the establishing of the event handlers for the rectangles
     */
    private void addKeyPresses() {
        aKey.setOnMousePressed(event -> keyClickHandler(aKey, (aDefault + octaveValue), noteLength));
        aSharpKey.setOnMousePressed(event -> keyClickHandler(aSharpKey, (aSharpDefault + octaveValue), noteLength));
        bKey.setOnMousePressed(event -> keyClickHandler(bKey, (bDefault + octaveValue), noteLength));
        cKey.setOnMousePressed(event -> keyClickHandler(cKey, (cDefault + octaveValue), noteLength));
        cSharpKey.setOnMousePressed(event -> keyClickHandler(cSharpKey, (cSharpDefault + octaveValue), noteLength));
        dKey.setOnMousePressed(event -> keyClickHandler(dKey, (dDefault + octaveValue), noteLength));
        dSharpKey.setOnMousePressed(event -> keyClickHandler(dSharpKey, (dSharpDefault + octaveValue), noteLength));
        eKey.setOnMousePressed(event -> keyClickHandler(eKey, (eDefault + octaveValue), noteLength));
        fKey.setOnMousePressed(event -> keyClickHandler(fKey, (fDefault + octaveValue), noteLength));
        fSharpKey.setOnMousePressed(event -> keyClickHandler(fSharpKey, (fSharpDefault + octaveValue), noteLength));
        gKey.setOnMousePressed(event -> keyClickHandler(gKey, (gDefault + octaveValue), noteLength));
        gSharpKey.setOnMousePressed(event -> keyClickHandler(gSharpKey, (gSharpDefault + octaveValue), noteLength));
    }


    /**
     * Functionality for telling the user what note they are hovering over
     */
    public void addKeytracking(){
        bKey.setOnMouseEntered(event -> statusLabel.setText("Note: B"));
        bKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        aKey.setOnMouseEntered(event -> statusLabel.setText("Note: A"));
        aKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        aSharpKey.setOnMouseEntered(event -> statusLabel.setText("Note: A#"));
        aSharpKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        cSharpKey.setOnMouseEntered(event -> statusLabel.setText("Note: C#"));
        cSharpKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        cKey.setOnMouseEntered(event -> statusLabel.setText("Note: C"));
        cKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        dSharpKey.setOnMouseEntered(event -> statusLabel.setText("Note: D#"));
        dSharpKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        dKey.setOnMouseEntered(event -> statusLabel.setText("Note: D"));
        dKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        eKey.setOnMouseEntered(event -> statusLabel.setText("Note: E"));
        eKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        fSharpKey.setOnMouseEntered(event -> statusLabel.setText("Note: F#"));
        fSharpKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        fKey.setOnMouseEntered(event -> statusLabel.setText("Note: F"));
        fKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        gKey.setOnMouseEntered(event -> statusLabel.setText("Note: G"));
        gKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        gSharpKey.setOnMouseEntered(event -> statusLabel.setText("Note: G#"));
        gSharpKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));
    }

    /**
     * Handles the playing of a note when a rectangle is clicked
     * Also gives visual feedback when a rectangle is clicked in form of a dropshadow
     * @param rect The rectangle that is clicked
     * @param noteValue The note value that is corresponded to that rectangle
     * @param noteLength The length of the note to play
     */
    private void keyClickHandler(Rectangle rect, int noteValue, double noteLength) {
        if (infNoteCheck.isSelected()){
            noteLength = 100000;
        }
        rect.setEffect(new DropShadow());
        Note note = new Note(noteValue, noteLength);
        note.setDynamic(volumeLevel);
        Play.midi(note);
        rect.setOnMouseReleased(event -> rect.setEffect(null));
        if(recordOn == true) {
            addPlayedNoteToScore(noteValue);
        }
    }

    /**
     * Handles the button events of the Octave up and down buttons
     */
    private void addOctaves() {
        btnOctaveDown.setOnAction(event -> changeOctaves(-12));
        btnOctaveUp.setOnAction(event -> changeOctaves(12));
    }


    /**
     * Handles the behavior of changing octaves, by changing the default note values
     * @param octaveDiff The amount to change the note values by (12 in the case of this system)
     */
    private void changeOctaves(int octaveDiff) {
        if (octaveValue <= -48 && octaveDiff == -12) {
            System.out.println("Lowest pitch reached.");
        }
        else if (octaveValue >= 48 && octaveDiff == 12) {
            System.out.println("Highest pitch reached.");
            octaveDiff = 0;
        }
        else {
            octaveValue += octaveDiff;
        }
    }

    /**
     * Handles the two sliders and their functions
     * For the note length slider, adjust the default note length
     * For the volume slider, adjusts the default volume level
     */
    private void initSliderHandlers() {
        sliderNoteLength.valueProperty().addListener((observable, oldValue, newValue) ->
                changeNoteLength((Double) newValue));
        sliderVolume.valueProperty().addListener((observable, oldValue, newValue) ->
                changeVolumeLevel((Double) newValue));
    }

    /**
     * Adjust the length of the note to be played
     * @param newNoteLength
     */
    private void changeNoteLength(double newNoteLength) {
        noteLength = newNoteLength;
    }

    /**
     * Adjust the volume level for the outputted sound
     * @param newValue The new volume level for the output
     */
    private void changeVolumeLevel(double newValue){
        volumeLevel = (int) newValue;
    }

}

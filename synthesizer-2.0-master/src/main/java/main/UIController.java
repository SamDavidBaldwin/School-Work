/* *****************************************
 * Winter 2021
 *
 * Name: Samuel Baldwin, Giles Thomas, Griffin Miller, Rah Hite
 * Section: MWF 8:30am
 * Date: Final 12/8/21
 * Time: 11:59 PM
 *
 * Project: Synthesizer_2.0
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
import javafx.scene.Scene;
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
     * Middle C octave default note values for JMusic
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
     * A Score object to display the notes played
     */
    public Score score = new Score();
    /**
     * A score object that holds the archived notes
     */
    public Score archivedScore = new Score();
    /**
     * Indicates whether the program is or isn't recording the notes played on the score
     */
    public boolean recordOn = false;
    /**
     * Default note view
     */
    String output= "";
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
        noteAdjustment();
        addKeytracking();
        addOctaves();
        initSliderHandlers();
        setTooltips();
        btnReset.setOnAction(event -> resetScore());
        if(recordOn == false){
            btnRecord.setOnAction(event -> recordingSwitch());
        }
    }
    /**
     * Updates each label in the score display to the corresponding String
     */
    private void labelAdjustment(){
        cLabel.setText(score.getScore(0));
        dLabel.setText(score.getScore(1));
        eLabel.setText(score.getScore(2));
        fLabel.setText(score.getScore(3));
        gLabel.setText(score.getScore(4));
        aLabel.setText(score.getScore(5));
        bLabel.setText(score.getScore(6));
    }
    public void archiveScore(){
            archivedScore.updateScore(0,cLabel.getText());
            archivedScore.updateScore(1,dLabel.getText());
            archivedScore.updateScore(2,eLabel.getText());
            archivedScore.updateScore(3,fLabel.getText());
            archivedScore.updateScore(4,gLabel.getText());
            archivedScore.updateScore(5,aLabel.getText());
            archivedScore.updateScore(6,bLabel.getText());
            score = new Score();
            labelAdjustment();
    }

    /**
     * Sets up the tooltips for the UI elements
     */
    public void setTooltips() {
        btnReset.setTooltip(new Tooltip("Reset the score"));
        sliderNoteLength.setTooltip(new Tooltip("Adjusts the length of the note played by the synthesizer:\n1 -> Quarter Note\n2 -> Half Note\n4 -> Full Note"));
        sliderVolume.setTooltip(new Tooltip("Adjust the volume of the output"));
        infNoteCheck.setTooltip(new Tooltip("Sustains the notes played"));
        recordTooltips();
        octaveTooltips();
    }
    /**
     * Sets up and adjusts the tooltips for the octave buttons
     */
    public void octaveTooltips(){
        Tooltip btnOctaveUpTT = new Tooltip();
        if((octaveValue/12+4)==8){btnOctaveUpTT.setText("Cannot increase the octave more");}
        else{btnOctaveUpTT.setText("Increases the octave to C" + (octaveValue/12+5));}
        btnOctaveUp.setTooltip(btnOctaveUpTT);
        Tooltip btnOctaveDownTT = new Tooltip();
        if((octaveValue/12+4)==0){btnOctaveDownTT.setText("Cannot decrease the octave more");}
        else{btnOctaveDownTT.setText("Decreases the octave to C" + (octaveValue/12+3));}
        btnOctaveDown.setTooltip(btnOctaveDownTT);
    }

    /**
     * Sets up and adjusts the tooltips for the recording button
     */
    public void recordTooltips() {
        Tooltip btnRecordTT = new Tooltip();
        if (recordOn) {
            btnRecordTT.setText("Stops the recording");
        } else {
            btnRecordTT.setText("Starts the recording of the notes played in a score");
        }
        btnRecord.setTooltip(btnRecordTT);
    }
    /**
     * Resets the score and re-updates the visual display
     */
    public void resetScore(){
        score = new Score();
        labelAdjustment();
        recordOn = false;
        btnRecord.setText("Record");
    }
    /**
     * Handles the switch of recording on and off, and resets the score when turning off recording
     */
    private void recordingSwitch(){
        if(recordOn == true){
            recordOn = false;
            btnRecord.setText("Recording Stopped");
        }
        else{
            recordOn = true;
            btnRecord.setText("Recording...");
            recordTooltips();
        }
    }
    /**
     * Each time that a note is played, updates the score with a /u2015 for all notes that aren't being played
     * @param note the note that is being played
     */
    private void updateScore(int note, int spaces){
        if(recordOn == true){
            for (int i = 0; i < 7; i++) {
                if (i != note) {
                    if(spaces == 2) {
                        score.updateScore(i, "\u2015");
                    }
                    if(spaces == 3){
                        score.updateScore(i, "\u2015\u2015");
                    }
                }
            }
            labelAdjustment();
            if(aLabel.getWidth() >= 1000){
                archiveScore();
            }
        }
    }
    /**
     * Changes the output character based on the note length
     */
    public void noteAdjustment(){
        if(noteLength == 0){
            output = "_";
        }
        else if(noteLength == 1){
            output = "\u2669";
        }
        else if(noteLength == 2){
            output = "\u266A";
        }
        else{
            output = "o";
        }
    }
    /**
     * Handles the adding of the 'o' to indicate a specific note being played
     * @param note The key that is pressed to play a note
     */
    private void addPlayedNoteToScore(int note){
            int value = (note + octaveValue)%12;
            switch(value) {
                case 0:
                    score.updateScore(0,output + " ");
                    updateScore(0,2);
                    break;
                case 1:
                    score.updateScore(0,output+"\u266f");
                    updateScore(0,3);
                    break;
                case 2:
                    score.updateScore(1,output+ " ");
                    updateScore(1,2);
                    break;
                case 3:
                    score.updateScore(1,output+"\u266f");
                    updateScore(1,3);
                    break;
                case 4:
                    score.updateScore(2,output+ " ");
                    updateScore(2,2);
                    break;
                case 5:
                    score.updateScore(3,output+ " ");
                    updateScore(3,2);

                    break;
                case 6:
                    score.updateScore(3,output+"\u266f");
                    updateScore(3,3);
                    break;
                case 7:
                    score.updateScore(4,output+ " ");
                    updateScore(4,2);
                    break;
                case 8:
                    score.updateScore(4,output+"\u266f");
                    updateScore(4,3);
                    break;
                case 9:
                    score.updateScore(5,output+ " ");
                    updateScore(5,2);
                    break;
                case 10:
                    score.updateScore(5,output+"\u266f");
                    updateScore(5,3);
                    break;
                case 11:
                    score.updateScore(6,output+ " ");
                    updateScore(6,2);
                    break;
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
        bKey.setOnMouseEntered(event -> statusLabel.setText("Octave: C" + (octaveValue/12+4) + " Note: B"));
        bKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        aKey.setOnMouseEntered(event -> statusLabel.setText("Octave: C" + (octaveValue/12+4) +" Note: A"));
        aKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        aSharpKey.setOnMouseEntered(event -> statusLabel.setText("Octave: C" + (octaveValue/12+4) +" Note: A#"));
        aSharpKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        cSharpKey.setOnMouseEntered(event -> statusLabel.setText("Octave: C" + (octaveValue/12+4) +" Note: C#"));
        cSharpKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        cKey.setOnMouseEntered(event -> statusLabel.setText("Octave: C" + (octaveValue/12+4) +" Note: C"));
        cKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        dSharpKey.setOnMouseEntered(event -> statusLabel.setText("Octave: C" + (octaveValue/12+4) +" Note: D#"));
        dSharpKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        dKey.setOnMouseEntered(event -> statusLabel.setText("Octave: C" + (octaveValue/12+4) +" Note: D"));
        dKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        eKey.setOnMouseEntered(event -> statusLabel.setText("Octave: C" + (octaveValue/12+4) +" Note: E"));
        eKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        fSharpKey.setOnMouseEntered(event -> statusLabel.setText("Octave: C" + (octaveValue/12+4) +" Note: F#"));
        fSharpKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        fKey.setOnMouseEntered(event -> statusLabel.setText("Octave: C" + (octaveValue/12+4) +" Note: F"));
        fKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        gKey.setOnMouseEntered(event -> statusLabel.setText("Octave: C" + (octaveValue/12+4) +" Note: G"));
        gKey.setOnMouseExited(event -> statusLabel.setText(defaultStatusLabel));

        gSharpKey.setOnMouseEntered(event -> statusLabel.setText("Octave: C" + (octaveValue/12+4) +" Note: G#"));
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
        if(recordOn) {
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
        }
        else {
            octaveValue += octaveDiff;
        }
        octaveTooltips();
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
        noteAdjustment();
    }
    /**
     * Adjust the volume level for the outputted sound
     * @param newValue The new volume level for the output
     */
    private void changeVolumeLevel(double newValue){
        volumeLevel = (int) newValue;
    }
}
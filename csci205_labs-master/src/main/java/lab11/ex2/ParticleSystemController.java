/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 11/6/21
 * Time: 12:00 AM
 *
 * Project: csci205_labs
 * Package: lab11.ex2.model
 * Class: ParticleSystemController
 *
 * Description: Controller for an emitter/particle animation
 *
 * ****************************************
 */
package lab11.ex2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import lab11.ex2.model.Particle;
import lab11.ex2.model.ParticleSystemModel;

public class ParticleSystemController {

    /** Our AnimationTimer object that will keep the canvas updating with cool stuff*/
    private AnimationTimer animationTimer;

    /** A reference to the model this controller must work with*/
    private ParticleSystemModel theModel;

    /** The Graphics Context of the canvas*/
    private GraphicsContext gc;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGenerate;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnStop;

    @FXML
    private Canvas canvas;

    @FXML
    private CheckBox checkboxContinuous;

    @FXML
    private Slider numSlider;

    @FXML
    void initialize() {
        assert btnGenerate != null : "fx:id=\"btnGenerate\" was not injected: check your FXML file 'particlesim.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'particlesim.fxml'.";
        assert btnStop != null : "fx:id=\"btnStop\" was not injected: check your FXML file 'particlesim.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'particlesim.fxml'.";
        assert checkboxContinuous != null : "fx:id=\"checkboxContinuous\" was not injected: check your FXML file 'particlesim.fxml'.";
        assert numSlider != null : "fx:id=\"numSlider\" was not injected: check your FXML file 'particlesim.fxml'.";


        //We're going to need to access the graphics context of a canvas. A lot.
        this.gc = canvas.getGraphicsContext2D();
        this.btnStart.setOnAction(event ->{

            //If we already have an animation timer, then just stop the timeline and reset it to the beginning
            if(this.animationTimer != null){
                this.theModel.stopAndReset();
            }
            else{
                //We don't have a timer, so create it
                this.animationTimer = createAnimationTimer();
            }
            //Update the timeline to start playing
            this.theModel.play();

            //Start the animationTimer
            this.animationTimer.start();
        });
    }

    /**
     * Let's set our model instance that this controller is going to be referring to,
     * and we'll set up our event handlers and bindings here too.
     *
     * @param  theModel a reference to the {@Link lab11.ex2.mode.ParticleSystemModel} we're working with
     */

    public void setModel(ParticleSystemModel theModel){
        this.theModel = theModel;

        this.btnGenerate.setOnAction(event -> {
            this.theModel.generateRandomEmitter((int)this.canvas.getWidth(), (int)this.canvas.getHeight(), (int)numSlider.getValue());
        });
    }

    /**
     * Construct an instance of an {@Link AnimationTimer} that will update every particle from every emitter
     * @return an AnimationTimer
     */
    private AnimationTimer createAnimationTimer(){
        return (new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = ParticleSystemController.this.gc;
                gc.setFill(Color.BLACK);
                gc.fillRect(0,0,ParticleSystemController.this.canvas.getWidth(),ParticleSystemController.this.canvas.getHeight());
                ParticleSystemModel theModel = ParticleSystemController.this.theModel;

                //Now, go through every particle of every emitter and draw an updated oval based
                //on its current position
                theModel.emitterStream()
                        .forEach(e -> e.particleStream()
                                .forEach(p ->{
                                    gc.setFill(p.getColor());
                                    gc.fillOval(p.getX(),p.getY(),5,5);
                                }
                                )
                        );
            }
        });
    }


}

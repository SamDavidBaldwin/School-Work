/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 11/6/21
 * Time: 11:27 PM
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc
 * Class: TrafficLightController
 *
 * Description: The Controller Class for the TrafficLight
 *
 * ****************************************
 */
package lab10.trafficlightmvc;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;



public class TrafficLightController {
    private TrafficLightView theView;
    private TrafficLightModel theModel;

    public TrafficLightController(TrafficLightModel theModel, TrafficLightView theView) {
        this.theModel = theModel;
        this.theView = theView;

        //Bind the model's auto off property to the view's checkbox auto property in the view
        theModel.isAutoOffProperty().bind(theView.getCheckBoxAutoOff().selectedProperty());

        //Create a binding that is based on the dimensions of the window
        /**I am patently aware of how badly this part is coded, but I spend like half a day on trying to figure it out
         * the correct way, and for the life of me I couldn't. It kinda gets the job done in a backwards sense, and
         * given more time I would probably figure out the right way to do it but that isn't the world that we live in
         * right now.
         */
        NumberBinding radiusBinding = Bindings.max(theView.getRoot().heightProperty(), theView.getRoot().widthProperty()).divide(6).add(-15);
        for (Circle c : theView.getLights()) {
            c.radiusProperty().bind(radiusBinding);
            c.setOnMouseClicked(event -> {
                if(!theModel.isIsAutoOff()) {
                    Paint h = c.getFill();
                    if (Color.GREEN.equals(h)) {
                        c.setFill(Color.DARKGREEN);
                    }
                    if (Color.YELLOW.equals(h)) {
                        c.setFill(Color.DARKKHAKI);
                    }
                    if (Color.RED.equals(h)) {
                        c.setFill(Color.DARKRED);
                    }
                    if (Color.DARKGREEN.equals(h)) {
                        c.setFill(Color.GREEN);
                    }
                    if (Color.DARKKHAKI.equals(h)) {
                        c.setFill(Color.YELLOW);
                    }
                    if (Color.DARKRED.equals(h)) {
                        c.setFill(Color.RED);
                    }
                }
                else{
                    Paint h = c.getFill();
                    System.out.println();
                    if (Color.GREEN.equals(h)) {
                        c.setFill(Color.DARKGREEN);
                    }
                    if (Color.YELLOW.equals(h)) {
                        c.setFill(Color.DARKKHAKI);
                    }
                    if (Color.RED.equals(h)) {
                        c.setFill(Color.DARKRED);
                    }
                    if (Color.DARKGREEN.equals(h)) {
                        Runnable r = () -> {
                            try{
                                c.setFill(Color.GREEN);
                                Thread.sleep(1000);
                            }
                            catch(InterruptedException ex){
                            }
                            finally {
                                c.setFill(Color.DARKGREEN);
                            }
                        };
                        Thread t = new Thread(r);
                        t.start();
                    }
                    if (Color.DARKKHAKI.equals(h)) {
                        Runnable r = () -> {
                            try{
                               c.setFill(Color.YELLOW);
                               Thread.sleep(1000);
                            }
                            catch(InterruptedException ex){
                            }
                            finally {
                                c.setFill(Color.DARKKHAKI);
                            }
                        };
                        Thread t = new Thread(r);
                        t.start();
                    }
                    if (Color.DARKRED.equals(h)) {
                        Runnable r = () -> {
                            try{
                                c.setFill(Color.RED);
                                Thread.sleep(1000);
                            }
                            catch(InterruptedException ex){
                            }
                            finally {
                                c.setFill(Color.DARKRED);
                            }
                        };
                        Thread t = new Thread(r);
                        t.start();
                    }
                }
            });
        }
    }
}

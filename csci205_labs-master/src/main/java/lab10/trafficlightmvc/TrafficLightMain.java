/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 11/6/21
 * Time: 11:20 PM
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc
 * Class: TrafficLightMain
 *
 * Description:
 *
 * ****************************************
 */
package lab10.trafficlightmvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrafficLightMain extends Application {
    /**
     * The model that is the backend of the window
     */
    private TrafficLightModel theModel;
    /**
     * The view that is shown in the window
     */
    private TrafficLightView theView;
    /**
     * The controls of the model/view
     */
    private TrafficLightController theController;

    @Override
    public void init() throws Exception{
        super.init();
        this.theModel = new TrafficLightModel();
        this.theView = new TrafficLightView(this.theModel);
        this.theController = new TrafficLightController(this.theModel, this.theView);

    }

    @Override
    public void start(Stage primaryStage){

        Scene scene = new Scene(theView.getRoot());

        primaryStage.setTitle("Traffic Light Sim");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
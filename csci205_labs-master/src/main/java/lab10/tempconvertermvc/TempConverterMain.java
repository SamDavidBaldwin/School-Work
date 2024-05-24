/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 *
 * Author: Prof. Brian King
 *
 * File: TempConverter
 * Description:
 * Basic class file to present a very simple GUI to do temperature conversion
 * using JavaFX
 *
 * NOTE: This is the solution to completing a refactoring of the original code
 * into a compliant MVC version of the code
 * ****************************************
 */

package lab10.tempconvertermvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main program that initializes all classes for our ppp
 */
public class TempConverterMain extends Application {

    private TempConverterModel theModel;
    private TempConverterView theView;
    private TempConverterController theController;

    /**
     * The application initialization method. This method is called immediately
     * after the Application class is loaded and constructed, but before the
     * start() method is invoked.
     */
    @Override
    public void init() throws Exception {
        super.init();
        this.theModel = new TempConverterModel();
        this.theView = new TempConverterView(theModel);
        this.theController = new TempConverterController(theModel,theView);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     */
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(this.theView.getRoot());
        primaryStage.setTitle("Temperature Calculator");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * Our standard main program for a JavaFX application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}

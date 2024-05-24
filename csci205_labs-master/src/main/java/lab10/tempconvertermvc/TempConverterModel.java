/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2021
 *
 * Name: YOUR NAME
 * Date: 10/24/21
 * Time: 9:30 PM
 *
 * Project: csci205_lectureEx
 * Class: TempConverterModel
 * Description:
 *
 * ****************************************
 */

package lab10.tempconvertermvc;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * This is the "model" for our temp converter program. There really is not
 * much to a model in this particular program. The only logic that can be separated
 * out are those functions that do conversions
 */
public class TempConverterModel {
    private static final String DEFAULT_FORMATTER = "%.1f";
    private String sFormatter;
    /**
     * If the radio button is set to C
     */
    private SimpleBooleanProperty isSetForCtoF;
    /**
     * If the radio button is set to F
     */
    private SimpleBooleanProperty isSetForFtoC;
    /**
     * Holds the last temperature converted in celsius
     */
    private SimpleDoubleProperty lastTempConvertedInC;

    /**
     * Initialize a new model
     */
    public TempConverterModel() {
        this.sFormatter = DEFAULT_FORMATTER;
        isSetForCtoF = new SimpleBooleanProperty(true);
        isSetForFtoC = new SimpleBooleanProperty(false);
        this.lastTempConvertedInC = new SimpleDoubleProperty(0);

    }

    public SimpleBooleanProperty isIsSetForFtoCProperty() {
        return isSetForFtoC;
    }

    public SimpleBooleanProperty isIsSetForCtoFProperty() {
        return isSetForCtoF;
    }

    public boolean isIsSetForCtoF() {
        return isSetForCtoF.get();
    }

    public boolean isIsSetForFtoC() {
        return isSetForFtoC.get();
    }

    public double getLastTempConvertedInC() {
        return lastTempConvertedInC.get();
    }

    public SimpleDoubleProperty lastTempConvertedInCProperty() {
        return lastTempConvertedInC;
    }

    /**
     * A simple function that takes a string represents a temperatre
     * in Fahrenheit, and converts it to Celsius
     * @param sFTemp the temperature in Farenheit
     * @return a String representing the temp in celsius.
     */
    public String convertFtoC(String sFTemp) {
        Double tempF = Double.parseDouble(sFTemp);
        Double tempC = (tempF - 32.0) * 5.0 / 9.0;
        this.lastTempConvertedInC.set(tempC);
        return String.format(this.sFormatter, tempC);
    }
    /**
     * A simple function that takes a string represents a temperatre
     * in Celsius, and converts it to Farenheit
     * @param sCTemp the temperature in Celsius
     * @return a String representing the temp in celsius.
     */
    public String convertCtoF(String sCTemp){
        Double tempC = Double.parseDouble(sCTemp);
        this.lastTempConvertedInC.set(tempC);
        Double tempF = (tempC * (9.0/5.0)) + 32.0;
        return String.format(this.sFormatter, tempF);
    }

    public String strTempConvert(String strTemp){
        if (this.isIsSetForFtoC()){
            return this.convertFtoC(strTemp);
        }
        if (this.isIsSetForCtoF()){
            return this.convertCtoF(strTemp);
        }
        else{
            return strTemp;
        }
    }


}

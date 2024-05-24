package lab10.trafficlightmvc;


import javafx.scene.paint.*;

/**
 * The colors that a Light can have, both on and off
 */
public enum LightColorEnum {
    RED(Color.RED, Color.DARKRED),
    YELLOW(Color.YELLOW,Color.DARKKHAKI),
    GREEN(Color.GREEN,Color.DARKGREEN);

    private Paint colorOn;
    private Paint colorOff;

    private LightColorEnum(Paint colorOn, Paint colorOff){
        this.colorOn = colorOn;
        this.colorOff = colorOff;
    }

    public Paint getColorOn() {
        return colorOn;
    }

    public Paint getColorOff() {
        return colorOff;
    }
}

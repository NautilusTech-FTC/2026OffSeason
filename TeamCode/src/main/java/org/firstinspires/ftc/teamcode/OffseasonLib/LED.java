package org.firstinspires.ftc.teamcode.OffseasonLib;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class LED {
    private Servo led;

    public enum colors {
        OFF,
        RED,
        ORANGE,
        YELLOW,
        SAGE,
        GREEN,
        TURQUOISE,
        BLUE,
        INDIGO,
        PURPLE,
        WHITE
    }
    private colors currentColor;

    public enum mode {
        SET,
        CUSTOM,
        CYCLE
    }

    private mode ledMd = mode.SET;

    public enum cycleMode {
        FORWARDS,
        BACKWARDS,
        SINE
    }
    private cycleMode cycleMd = cycleMode.SINE;
    private double cycleTimer = 0;
    private double cycleTime = 0;

    private double colorRangeMin = 0;
    private double colorRangeMax = 1;

    private double setColor = 0;


    public void mapLED(HardwareMap hardwareMap, String name)  {
        led = hardwareMap.get(Servo.class, name);
    }

    public void setMode(mode mode) {
        ledMd = mode;
    }

    public void setColor(colors color) {
        currentColor = color;
        set();
    }

    public void setColor(double value) {
        ledMd = mode.CUSTOM;
        setColor = value;
        set();
    }

    public void setRange(double lower, double higher) {
        colorRangeMin = lower;
        colorRangeMax = higher;
    }

    public void setCycle(cycleMode mode, double cycleTime) {
        cycleMd = mode;
        this.cycleTime = cycleTime;
    }

    public void cycleLoop(double runtime) {
        cycleTimer = (runtime % cycleTime)/cycleTime;
        if (ledMd == mode.CYCLE) {
            cycle();
        }
    }

    private void set() {
        switch (currentColor) {
            case OFF:
                led.setPosition(0);
                break;
            case RED:
                led.setPosition(0.278);
                break;
            case ORANGE:
                led.setPosition(0.333);
                break;
            case YELLOW:
                led.setPosition(0.338);
                break;
            case SAGE:
                led.setPosition(0.444);
                break;
            case GREEN:
                led.setPosition(0.5);
                break;
            case TURQUOISE:
                led.setPosition(0.555);
                break;
            case BLUE:
                led.setPosition(0.611);
                break;
            case INDIGO:
                led.setPosition(0.666);
                break;
            case PURPLE:
                led.setPosition(0.722);
                break;
            case WHITE:
                led.setPosition(1);
                break;
            default:
                led.setPosition(0);
        }
    }

    private void custom() {
        led.setPosition(colorRangeMin + (setColor * (colorRangeMax-colorRangeMin)));
    }

    private void cycle() {
        switch (cycleMd) {
            case FORWARDS:
                setColor = cycleTimer;
                break;
            case BACKWARDS:
                setColor = -cycleTimer;
                break;
            case SINE:
                setColor = 0.5+Math.sin(cycleTimer*Math.PI*2)/2;
        }
        custom();
    }
}

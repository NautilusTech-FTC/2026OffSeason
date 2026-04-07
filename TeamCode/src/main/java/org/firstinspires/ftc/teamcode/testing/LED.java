package org.firstinspires.ftc.teamcode.testing;

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
        WHITE,
        OTHER
    }
    private colors currentColor;

    public LED(HardwareMap hardwareMap, String name) {
        led = hardwareMap.get(Servo.class, name);
    }

    public void setColor(colors color) {
        currentColor = color;
        set();
    }

    public void setColor(double value) {
        currentColor = colors.OTHER;
        led.setPosition(value);
    }

    private void set() {
        switch(currentColor) {
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
            case OTHER:
                break;
            default:
                led.setPosition(0);
        }
    }
}

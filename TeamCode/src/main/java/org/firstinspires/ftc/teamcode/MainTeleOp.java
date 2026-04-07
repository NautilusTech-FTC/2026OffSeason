package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.testing.LED;

public class MainTeleOp extends OpMode {

    LED led0 = new LED(hardwareMap, "led0");
    LED led1 = new LED(hardwareMap, "led1");


    public void init() {
        led0.setColor(LED.colors.ORANGE);
    }

    public void loop() {

    }

}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.OffseasonLib.LED;
import org.firstinspires.ftc.teamcode.OffseasonLib.MecanumDrive;

@TeleOp
public class MainTeleOp extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    LED led0 = new LED();

    public void init() {
        //Drive Init:
        drive.mapMotors(hardwareMap, "Motor0", "Motor1", "Motor2", "Motor3");
        drive.setMotorDirections( //set drive motor directions
                DcMotorSimple.Direction.REVERSE, //Front left
                DcMotorSimple.Direction.FORWARD, //Front right
                DcMotorSimple.Direction.FORWARD, //Back right
                DcMotorSimple.Direction.REVERSE //Back left
        );

        //led0 init:
        led0.mapLED(hardwareMap, "LED0");
        led0.setMode(LED.mode.CYCLE);
        led0.setRange(0.333, 0.666);
        led0.setCycle(LED.cycleMode.SINE, 10);


    }

    public void loop() {
        drive.RobotCentric(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, 0.1);
        led0.cycleLoop(getRuntime());

    }

}

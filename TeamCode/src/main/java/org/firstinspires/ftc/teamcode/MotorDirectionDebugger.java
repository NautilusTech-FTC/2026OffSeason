package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Motor Direction Debugger")
public class MotorDirectionDebugger extends OpMode {
    private DcMotor fLMotor, fRMotor, bLMotor, bRMotor;
    @Override
    public void init() {
        fLMotor = hardwareMap.get(DcMotor.class, "Motor0");
        fRMotor = hardwareMap.get(DcMotor.class, "Motor1");
        bRMotor = hardwareMap.get(DcMotor.class, "Motor2");
        bLMotor = hardwareMap.get(DcMotor.class, "Motor3");

        bLMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        fLMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        fLMotor.setPower(0.1);
        fRMotor.setPower(0.1);
        bRMotor.setPower(0.1);
        bLMotor.setPower(0.1);
    }
}

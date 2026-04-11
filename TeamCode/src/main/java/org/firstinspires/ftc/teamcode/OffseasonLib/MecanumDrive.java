package org.firstinspires.ftc.teamcode.OffseasonLib;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDrive {
    private DcMotor fLMotor, fRMotor, bRMotor, bLMotor;

    private IMU imu;



    public void mapMotors(HardwareMap hardwareMap, String fL, String fR, String bR, String bL) {
        fLMotor = hardwareMap.get(DcMotor.class, fL);
        fRMotor = hardwareMap.get(DcMotor.class, fR);
        bRMotor = hardwareMap.get(DcMotor.class, bR);
        bLMotor = hardwareMap.get(DcMotor.class, bL);

    }

    public void setMotorDirections(DcMotorSimple.Direction fLDirection, DcMotorSimple.Direction fRDirection, DcMotorSimple.Direction bRDirection, DcMotorSimple.Direction bLDirection) {
        fLMotor.setDirection(fLDirection);
        fRMotor.setDirection(fRDirection);
        bLMotor.setDirection(bLDirection);
        bRMotor.setDirection(bRDirection);
    }

    private void setPower(double fL, double fR, double bL, double bR) {
        fLMotor.setPower(fL);
        fRMotor.setPower(fR);
        bLMotor.setPower(bL);
        bRMotor.setPower(bR);
    }

    public void RobotCentric(double lx,double ly, double rx, double speed) {
        double motorSpeed = speed;
        //NOTES: Stick directions are inverted for whatever reason
        //ly (left_stick_y), lx (left_stick_x), rx (right_stick_x)
        double denominator = Math.max(Math.abs(ly) + Math.abs(lx) + Math.abs(rx), 1); //this prevents us from using a value >1
        double powerFL = motorSpeed * (-ly + lx + rx)/denominator;
        double powerFR = motorSpeed * (-ly - lx - rx)/denominator;
        double powerBL = motorSpeed * (-ly - lx + rx)/denominator;
        double powerBR = motorSpeed * (-ly + lx - rx)/denominator;

        setPower(powerFL, powerFR, powerBL, powerBR);
    }

    public void FieldCentric(double lx, double ly, double rx, double speed, boolean IMUReset, double strafeFix, Telemetry telemetry) {
        //TODO: REDO EVERYTHING HERE
        double motorSpeed = (0.25 + 0.75 * speed);

        // Sets the botHeading to the IMU yaw angle in radians
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);


        telemetry.addLine(String.valueOf(botHeading));


        double rotX = lx * Math.cos(botHeading) - ly * Math.sin(botHeading) * 1.1;
        double rotY = lx * Math.sin(botHeading) + ly * Math.cos(botHeading);

        telemetry.addData("lx",lx);
        telemetry.addData("ly",ly);
        telemetry.addData("rotX",rotX);
        telemetry.addData("rotY",rotY);
        telemetry.addData("rot",-botHeading);

        // Dominator makes sure all Motor values move proportionally and doesn't exceed 1.
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);

        //lx (left_stick_x), ly (left_stick_y), rx (right_stick_x), IMUReset (options button)
        double powerFL = (rotY + rx - rotX) / denominator * motorSpeed;
        double powerFR = (-rotY + rx - rotX) / denominator * motorSpeed;
        double powerBL = (rotY + rx + rotX) / denominator * motorSpeed;
        double powerBR = (-rotY + rx + rotX) / denominator * motorSpeed;

        setPower(powerFL, powerFR, powerBL, powerBR);
    }

}

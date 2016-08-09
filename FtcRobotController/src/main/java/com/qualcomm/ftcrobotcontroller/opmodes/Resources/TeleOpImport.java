//Blank TeleOp mode to copy when starting a new TeleOp

package com.qualcomm.ftcrobotcontroller.opmodes.Resources;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


public class TeleOpImport extends OpMode {

    DcMotor tRightMotor;
    DcMotor tLeftMotor;
    boolean tRightMotorReady = false;
    boolean tLeftMotorReady = false;
    boolean tUseBasicDrive = false;


    @Override
    public void init() {
    }


    @Override
    public void loop() {
        if (tUseBasicDrive) {

        }
    }

    @Override
    public void stop() {

    }

    public void backgrounddrive(boolean useBasicDrive) {
        tUseBasicDrive = useBasicDrive;
    }

    public void motorSetRight(DcMotor rightMotor) {
        tRightMotor = rightMotor;
        tRightMotorReady = true;
    }

    public void motorSetLeft(DcMotor leftMotor) {
        tLeftMotor = leftMotor;
        tLeftMotorReady = true;
    }

    public void motorSetDrive(DcMotor rightMotor, DcMotor leftMotor) {
        motorSetRight(rightMotor);
        motorSetLeft(leftMotor);
    }


    public void motorDrive(double bothPower) {
        motorDrive(bothPower, bothPower);
    }


    public void motorDrive(double rightPower, double leftPower) {
        if (tRightMotorReady && tLeftMotorReady) {
            motorPower(tRightMotor, rightPower);
            motorPower(tLeftMotor, leftPower);
        }
    }


    public void motorDrive(DcMotor rightMotor, DcMotor leftMotor, double bothPower) {
        motorPower(rightMotor, bothPower);
        motorPower(leftMotor, bothPower);
    }


    public void motorDrive(DcMotor rightMotor, DcMotor leftMotor, double rightPower, double leftPower) {
        motorPower(rightMotor, rightPower);
        motorPower(leftMotor, leftPower);
    }


    public void motorPower(DcMotor inputMotor, double power) {
        motorPower(inputMotor, power, true);
    }


    public void motorPower(DcMotor inputMotor, double power, boolean doscale) {
        if (doscale) {
            power = scaleInput(power);
        } else {
            power = Range.clip(power, 1, -1);
        }
        inputMotor.setPower(power);
    }


    // used to create easy dead area and to make it easier to drive strait
    //scale motor inputs

    //clips the range to get rid of to high and to low numbers
    public double scaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }


        dScale = Range.clip(dScale, -1, 1);
        // return scaled value.
        return dScale;
    }
}

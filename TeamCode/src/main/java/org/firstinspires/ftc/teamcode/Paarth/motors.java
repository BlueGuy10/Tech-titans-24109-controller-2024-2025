package org.firstinspires.ftc.teamcode.Paarth;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Config. File:
 * Control Hub:
 * Motor Part 00: motorOne
 * Motor Port 01: motorTwo
 * FYI: motorThree & motorFour are based
 * off motorOne and motorTwo respectively
 *
 *
 */



@Disabled
@TeleOp(group = "Motors")

public class motors extends LinearOpMode {

    //PLEASE MODIFY / UPDATE VARIABLES FOR THE COMPETITION!  THESE ARE NOT CONSTANTS!

    private DcMotor motorOne;
    private double motorOneZeroPower = 0.0;
    //private double motorOnePower = 1.0;
    private double motorOneSensitivity = 0.5;

    private DcMotor motorTwo;
    private double motorTwoZeroPower = 0.0;
    //private double motorTwoPower = 1.0;
    private double motorTwoSensitivity = 0.5;

    private DcMotor motorThree;
    private double motorThreeZeroPower = 0.0;
    //private double motorThreePower = 1.0;
    private double motorThreeSensitivity = 0.5;

    private DcMotor motorFour;
    private double motorFourZeroPower = 0.0;
    //private double motorFourPower = 1.0;
    private double motorFourSensitivity = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while (!isStarted()) {

            motorTelemetry();

        }

        waitForStart();
        while (opModeIsActive()) {

            teleOpControls();
            motorTelemetry();

        }

    }

    public void initHardware() {

        initMotorOne();
        initMotorTwo();
        initMotorThree();
        initMotorFour();

    }

    public void initMotorOne() {

        motorOne = hardwareMap.get(DcMotor.class, "left_front");
        motorOne.setDirection(DcMotor.Direction.FORWARD);
        motorOne.setPower(motorOneZeroPower);
        motorOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void initMotorTwo() {

        motorTwo = hardwareMap.get(DcMotor.class, "right_front");
        motorTwo.setDirection(DcMotor.Direction.REVERSE);
        motorTwo.setPower(motorTwoZeroPower);
        motorTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void initMotorThree() {

        motorThree = hardwareMap.get(DcMotor.class, "left_rear");
        motorThree.setDirection(DcMotor.Direction.REVERSE);
        motorThree.setPower(motorThreeZeroPower);
        motorThree.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorThree.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorThree.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void initMotorFour() {

        motorFour = hardwareMap.get(DcMotor.class, "right_rear");
        motorFour.setDirection(DcMotor.Direction.REVERSE);
        motorFour.setPower(motorFourZeroPower);
        motorFour.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorFour.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFour.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void teleOpControls() {

        /*
        if (gamepad2.x) {

            motorOne.setPower(motorOnePower * (-1));

        }

        if (gamepad2.a) {

            motorOne.setPower(motorOneZeroPower);

        }

        if (gamepad2.b) {

            motorOne.setPower(motorOnePower);

        }
        */

        //setting the variables
        double forwardOne = (gamepad2.right_stick_y * motorOneSensitivity);
        double forwardTwo = (gamepad2.right_stick_y * motorTwoSensitivity);
        double forwardThree = (gamepad2.right_stick_y * motorThreeSensitivity);
        double forwardFour = (gamepad2.right_stick_y * motorFourSensitivity);

        double motorOneControl = (gamepad2.right_stick_x * motorOneSensitivity);
        double motorTwoControl = (gamepad2.right_stick_x * motorTwoSensitivity);
        double motorThreeControl = (gamepad2.right_stick_x * motorThreeSensitivity);
        double motorFourControl = (gamepad2.right_stick_x * motorFourSensitivity);

        //forward & backward
        motorOne.setPower(forwardOne);
        motorTwo.setPower(forwardTwo);
        motorThree.setPower(forwardThree);
        motorFour.setPower(forwardFour);

        //turning
        //(push left for left, vice versa)
        motorOne.setPower(motorOneControl);
        motorTwo.setPower(motorTwoControl * (-1));
        motorThree.setPower(motorThreeControl);
        motorFour.setPower(motorFourControl * (-1));

    }

    public void motorTelemetry() {

        //Tap "Y" to reset encoders

        telemetry.addData("Left Front", "Encoder: %2d, Power: %.2f", motorOne.getCurrentPosition(), motorOne.getPower());
        telemetry.addData("Right Front", "Encoder: %2d, Power: %.2f", motorTwo.getCurrentPosition(), motorTwo.getPower());
        telemetry.addData("Left Rear", "Encoder: %2d, Power: %.2f", motorThree.getCurrentPosition(), motorThree.getPower());
        telemetry.addData("Right Rear", "Encoder: %2d, Power: %.2f", motorFour.getCurrentPosition(), motorFour.getPower());
        telemetry.update();

    }



}








//          //(@v@)\\ <---- Jonah
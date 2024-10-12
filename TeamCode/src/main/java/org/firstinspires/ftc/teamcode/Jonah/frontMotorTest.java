package org.firstinspires.ftc.teamcode.Jonah;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/** Configuration File
 *Control Hub:
 *Motor ort 00: motorOne
 * Motor Port 01: motorTwo
 */

@TeleOp(group="Motors")
//@Disabled
public class frontMotorTest extends LinearOpMode {

    private DcMotor motorRF;
    private double motorZeroPower = 0.0;
    private double motorSensetivity = 0.5;

    private DcMotor motorLF;

    private DcMotor motorRR;

    private DcMotor motorLR;

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {
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
        motorRF = hardwareMap.get(DcMotor.class, "right_front");
        motorRF.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRF.setPower(motorZeroPower);
        motorRF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorRF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void initMotorTwo() {
        motorLF = hardwareMap.get(DcMotor.class, "left_front");
        motorLF.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLF.setPower(motorZeroPower);
        motorLF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorLF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void initMotorThree() {
        motorRR = hardwareMap.get(DcMotor.class, "right_rear");
        motorRR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRR.setPower(motorZeroPower);
        motorRR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorRR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void initMotorFour() {
        motorLR = hardwareMap.get(DcMotor.class, "left_rear");
        motorLR.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLR.setPower(motorZeroPower);
        motorLR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorLR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void teleOpControls() {
        double motorRFPower = gamepad2.right_stick_y * motorSensetivity;
        double motorLFPower = gamepad2.right_stick_y * motorSensetivity;
        double motorRRPower = gamepad2.right_stick_y * motorSensetivity;
        double motorLRPower = gamepad2.right_stick_y * motorSensetivity;
        motorLF.setPower(gamepad2.right_stick_y * motorSensetivity);
        motorRF.setPower(gamepad2.right_stick_y * motorSensetivity);
        motorRR.setPower(gamepad2.right_stick_y * motorSensetivity);
        motorLR.setPower(gamepad2.right_stick_y * motorSensetivity);

    }

    public void motorTelemetry() {

        //tap "Y" to reset encoders |8 } <---- Paarth

        telemetry.addData("motorRF", "Encoder: %2d, Power: %.2f", motorRF.getCurrentPosition(), motorRF.getPower());
        telemetry.addData("motorLF", "Encoder: %2d, Power: %.2f", motorLF.getCurrentPosition(), motorLF.getPower());
        telemetry.addData("motorRR", "Encoder: %2d, Power: %.2f", motorRR.getCurrentPosition(), motorRR.getPower());
        telemetry.addData("motorLR", "Encoder: %2d, Power: %.2f", motorLR.getCurrentPosition(), motorLR.getPower());
        telemetry.update();
    }
}
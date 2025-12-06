package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * The NeveRest Classic 40 GearMotor, has a 40:1 output-to-input ratio.
 * So it would be 280:7 pulses per revolution
 * It also has 1120 ticks per revolution
 */
public class MecanumWheelsController {

    private static final float SCALE_POWER = 0.8F;
    private static final float WHEEL_CIRCUMFERENCE = 31.4159F; // cm
    private static final int TICKS_PER_REVOLUTION = 1120; // pulses is div by 4

    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;


    public MecanumWheelsController (HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotor.class, "LeftFront");
        leftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        rightFront = hardwareMap.get(DcMotor.class, "RightFront");
        rightBack = hardwareMap.get(DcMotor.class, "RightBack");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    /**
     * This method stops all motors and then resets all encoders one by one
     */
    public void resetEncoders() {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runWithEncoders() {
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Uses an average of all the motor encoders' values to get the distance
     * @return distance that's been traversed since the last reset of the
     * encoders                                                        :)
     */
    public double getDistance() {
        double lf = Math.abs(leftFront.getCurrentPosition());
        double lb = Math.abs(leftBack.getCurrentPosition());
        double rf = Math.abs(rightFront.getCurrentPosition());
        double rb = Math.abs(rightBack.getCurrentPosition());
        return (((lf + lb + rf + rb) / 4) / TICKS_PER_REVOLUTION) * WHEEL_CIRCUMFERENCE;
    }

    public void applyPower(float x, float y, float turn) {
        x *= -1;
        turn *= -1;
        if (Math.abs(x) < 0.01) {
            x = 0;
        }
        if (Math.abs(y) < 0.01) {
            y = 0;
        }
        if (Math.abs(turn) < 0.01) {
            turn = 0;
        }

        float leftFrontPower  = (y + turn + x) * SCALE_POWER;
        float leftBackPower   = (y + turn - x) * SCALE_POWER;
        float rightFrontPower = (y - turn - x) * SCALE_POWER;
        float rightBackPower  = (y - turn + x) * SCALE_POWER;
        float[] a = new float[]{Math.abs(leftFrontPower), Math.abs(leftBackPower), Math.abs(rightFrontPower), Math.abs(rightBackPower), 1};

        float max = a[0];

        for (int i = 1; i <a.length; i++) {
            max = Math.max(max, a[i]);
        }

        if (max > 1) {
            leftFrontPower /= max;
            leftBackPower /= max;
            rightFrontPower /= max;
            rightBackPower /= max;
        }

        leftFront.setPower(leftFrontPower);
        leftBack.setPower(leftBackPower);
        rightFront.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);
    }

    public void autoDrive(double leftFrontPower, double leftBackPower, double rightFrontPower, double rightBackPower) {
        leftFront.setPower(leftFrontPower);
        leftBack.setPower(leftBackPower);
        rightFront.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);
    }
}

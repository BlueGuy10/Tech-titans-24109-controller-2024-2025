package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumWheelsController {
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;
    private static final float scalePower = 0.6F;

    public MecanumWheelsController (HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotor.class, "LeftFront");
        leftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        rightFront = hardwareMap.get(DcMotor.class, "RightFront");
        rightBack = hardwareMap.get(DcMotor.class, "RightBack");

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void applyPower(float x, float y, float turn) {
        x = -x;
        turn = -turn;

        float leftFrontPower  = (y + turn + x) * scalePower;
        float leftBackPower   = (y + turn - x) * scalePower;
        float rightFrontPower = (y - turn - x) * scalePower;
        float rightBackPower  = (y - turn + x) * scalePower;
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
}

package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumWheelsController {
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;

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

    public void applyPower(float x, float y, float yaw) {
        if (0.2 < x && x > -0.2 && yaw == 0 && y != 0) {
            leftFront.setPower(y);
            leftBack.setPower(y);
            rightFront.setPower(y);
            rightBack.setPower(y);
        } else if (x != 0 && y != 0 && yaw == 0) {
            leftFront.setPower(y - x);
            leftBack.setPower(y + x);
            rightFront.setPower(y + x);
            rightBack.setPower(y - x);
        } else if (yaw != 0 && x == 0 && y == 0) {
            leftFront.setPower(-yaw);
            leftFront.setPower(-yaw);
            leftBack.setPower(-yaw);
            rightFront.setPower(yaw);
            rightBack.setPower(yaw);
        } else if (y > 0 && yaw != 0 && x == 0) {
            leftFront.setPower();
            leftBack.setPower();
            rightFront.setPower();
            rightBack.setPower();
        }
    }
}

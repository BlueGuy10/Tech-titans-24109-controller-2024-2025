package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmController {
    private static final float motorPower = 0.5F;



    private DcMotor pitch;
    private DcMotor extension;
    //jonathan u made it package private
    public ArmController (HardwareMap hardwareMap) {
        pitch = hardwareMap.get(DcMotor.class, "PitchMotor");
        extension = hardwareMap.get(DcMotor.class, "ExtensionMotor");

        pitch.setDirection(DcMotorSimple.Direction.REVERSE);
        extension.setDirection(DcMotorSimple.Direction.REVERSE);

        pitch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        pitch.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        extension.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        pitch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void pitchPower(int power) {
        pitch.setTargetPosition(pitch.getCurrentPosition() + power);
        pitch.setPower(motorPower);
        while(pitch.isBusy()) {}
    }
    public void extensionPower(int power) {
        extension.setTargetPosition(extension.getCurrentPosition() + power);
        extension.setPower(motorPower);
        while(extension.isBusy()) {}
    }
}

package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmController {
    private static final float motorPower = 1;

    private DcMotor pitch;
    private DcMotor extension;

    public ArmController (HardwareMap hardwareMap) {
        pitch = hardwareMap.get(DcMotor.class, "PitchMotor");
        extension = hardwareMap.get(DcMotor.class, "ExtensionMotor");

        pitch.setDirection(DcMotorSimple.Direction.REVERSE);
        extension.setDirection(DcMotorSimple.Direction.FORWARD);

        pitch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        pitch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void pitchPower(int power) {
        int target = extension.getCurrentPosition() + power;
        if (target <= 1050) {
            target = 1050;
        }
        pitch.setTargetPosition(target);
        pitch.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pitch.setPower(motorPower);
        while(pitch.isBusy()) {}
    }

    public void setPitchPower(int power) {
        int target = power;
        if (target <= 1050) {
            target = 1050;
        }
        pitch.setTargetPosition(target);
        pitch.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pitch.setPower(motorPower);
        while(pitch.isBusy()) {}
    }

//    public void pitchPower2(int power) {
//        pitch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        pitch.setPower(power);
//    }
    public void extensionPower(int power) {
        extension.setTargetPosition(power);
        extension.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        extension.setPower(motorPower);
        while(extension.isBusy()) {}
    }

    public void setExtensionPower(int power) {
        extension.setTargetPosition(extension.getCurrentPosition() + power);
        extension.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        extension.setPower(motorPower);
        while(extension.isBusy()) {}
    }

//    public void extensionPower2(int power) {
//        extension.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        extension.setPower(power);
//    }

    public int getPitchPosition() {
        return pitch.getCurrentPosition();
    }

    public int getExtensionPosition() {
        return extension.getCurrentPosition();
    }

}

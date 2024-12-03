package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmController {
    private static final float motorPower = 1;

    private static final int maxPitchEncoder = 535;
    private static final double encoderToRad = (Math.PI/2)/maxPitchEncoder;
    private static final int MAX_HORIZ_EXT = 7300;
    private static final int MAX_VER_EXT = 9200;

    private DcMotor pitch;
    private DcMotor extension;

    public ArmController (HardwareMap hardwareMap) {
        pitch = hardwareMap.get(DcMotor.class, "PitchMotor");
        extension = hardwareMap.get(DcMotor.class, "ExtensionMotor");

        pitch.setDirection(DcMotorSimple.Direction.REVERSE);
        extension.setDirection(DcMotorSimple.Direction.FORWARD);

        pitch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        pitch.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        pitch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        pitch.setPower(motorPower);
//        extension.setPower(motorPower);

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

    public void rawPitchPower(float power) {
        pitch.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pitch.setPower(power);
    }

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

    public void rawExtensionPower(float power) {
        extension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if (getExtensionPosition() <= getMaxExtension(getPitchPosition()) && power < 0) extension.setPower(0);
        else extension.setPower(power);
    }

    public int getMaxExtension(int pitch) {
        double radians = Math.abs(pitch * encoderToRad);
        double cosinus = Math.cos(radians);
        if (cosinus <= 0) {
            return -MAX_VER_EXT;
        }
        double calculated = MAX_HORIZ_EXT / cosinus;
        if (calculated > MAX_VER_EXT) {
            calculated = MAX_VER_EXT;
        }
        return -((int)Math.round(calculated));
    }

    public int getPitchPosition() {
        return pitch.getCurrentPosition();
    }

    public int getExtensionPosition() {
        return extension.getCurrentPosition();
    }

}

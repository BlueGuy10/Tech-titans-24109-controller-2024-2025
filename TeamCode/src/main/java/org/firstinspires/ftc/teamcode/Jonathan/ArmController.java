package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmController {
    private static final float motorPower = 1;

    private static final int maxPitchEncoder = 535;
    private static final double encoderToRad = (Math.PI/2)/maxPitchEncoder;
    private static final int MAX_HORIZ_EXT = 6000;
    private static final int MAX_VER_EXT = 9200;
    private static final int maxPitchLimit = 620;
    private static final int minExtLimit = 0;

    private DcMotor pitch;
    private DcMotor extension;

    public ArmController (HardwareMap hardwareMap) {
        pitch = hardwareMap.get(DcMotor.class, "PitchMotor");
        extension = hardwareMap.get(DcMotor.class, "ExtensionMotor");

        pitch.setDirection(DcMotorSimple.Direction.FORWARD);
        extension.setDirection(DcMotorSimple.Direction.REVERSE);

        pitch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        pitch.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        pitch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        pitch.setPower(motorPower);
//        extension.setPower(motorPower);

    }

    public void changePitch(int power) {
        int target = pitch.getCurrentPosition() + power;
        if (target < getMinPitchLimit()) { //min limit changes as arm extends
            target = getMinPitchLimit();
        } else if (target > maxPitchLimit) {
            target = maxPitchLimit;
        }
        pitch.setTargetPosition(target);
        pitch.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pitch.setPower(motorPower);
//        while(pitch.isBusy()) {}
    }

    public void setPitch(int power ) {
        int target = power;
        if (target < getMinPitchLimit()) {
            target = getMinPitchLimit();
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


    public void setExtension(int power) {
        int target = power;
        if (target < minExtLimit) {
            target = minExtLimit;
        }
        extension.setTargetPosition(target);
        extension.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        extension.setPower(motorPower);
        while(extension.isBusy()) {}
    }

    public void changeExtension(int power) {
        int target = extension.getCurrentPosition() + power;
        int maxTarget = getMaxExtension1(); //max limit changes as arm angle changes
        if (target < minExtLimit) {
            target = minExtLimit;
        } else if (target > maxTarget) {
            target = maxTarget;
        }

        extension.setTargetPosition(target);
        extension.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        extension.setPower(motorPower);
        while(extension.isBusy()) {}
    }

    public void rawExtensionPower(float power) {
        extension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if (getExtensionPosition() >= getMaxExtension(getPitchPosition()) && power > 0 && getExtensionPosition() >= -50) extension.setPower(0);
        else extension.setPower(power);
    }

    public int getMaxExtension(int pitch) {
        double radians = Math.abs(pitch * encoderToRad);
        double cosinus = Math.cos(radians);
        if (cosinus <= 0) {
            return MAX_VER_EXT;
        }
        double calculated = MAX_HORIZ_EXT / cosinus;
        if (calculated > MAX_VER_EXT) {
            calculated = MAX_VER_EXT;
        }
        return ((int)Math.round(calculated));
    }


    public int getMaxExtension1 () {
        int pitchPosition = pitch.getCurrentPosition();
        double pitchAngle = (Math.PI/1250) * pitchPosition;
        double cos_pitchAngle = Math.cos(pitchAngle);
        if ( cos_pitchAngle <= 0) {
            cos_pitchAngle = 0.001;
        }
        double maxLength = (42/cos_pitchAngle);
        return ((int)Math.round(maxLength * 90.66 * 1.5));
    }

    public int getMinPitchLimit () {
        int extPosition = extension.getCurrentPosition();
        double minPitchLimit = 100-(0.025*extPosition);
        return (int)Math.round(minPitchLimit);
    }
    public int getPitchPosition() {
        return pitch.getCurrentPosition();
    }

    public int getExtensionPosition() {
        return extension.getCurrentPosition();
    }

}

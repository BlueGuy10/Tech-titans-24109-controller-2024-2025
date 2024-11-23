package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmController {
    private DcMotor pitch;
    private DcMotor extension;

    ArmController (HardwareMap hardwareMap) {
        pitch = hardwareMap.get(DcMotor.class, "PitchMotor");
        extension = hardwareMap.get(DcMotor.class, "ExtensionMotor");

        pitch.setDirection(DcMotorSimple.Direction.FORWARD);
        extension.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void pitchPower(float power) {
        pitch.setPower(power);
    }
    public void extensionPower(float power) {
        extension.setPower(power);
    }
}

package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ShooterWheelController {

    private DcMotor shooterWheel;

    public ShooterWheelController (HardwareMap hardwareMap) {
        shooterWheel = hardwareMap.get(DcMotor.class, "Shooter");
        shooterWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void spinWheel(double power) {
        shooterWheel.setPower(power);
    }
}

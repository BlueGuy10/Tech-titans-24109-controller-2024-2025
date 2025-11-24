package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeController{
    private DcMotor intakeWheel;
    private Servo bootKicker;

    public IntakeController (HardwareMap hardwareMap) {
        intakeWheel = hardwareMap.get(DcMotor.class, "Intake");
        bootKicker = hardwareMap.get(Servo.class, "BootKicker2");
        intakeWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void intake(double power) {
        intakeWheel.setPower(power);
    }

    public void bootKick(double power) {
        bootKicker.setPosition(power);
    }
}


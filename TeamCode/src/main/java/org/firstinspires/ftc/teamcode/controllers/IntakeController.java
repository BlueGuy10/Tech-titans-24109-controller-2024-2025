package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeController{
    private DcMotor intakeWheel;
    private Servo bootKicker;
    private Servo bootKickerAlt;

    public IntakeController (HardwareMap hardwareMap) {
        intakeWheel = hardwareMap.get(DcMotor.class, "Intake");
        bootKicker = hardwareMap.get(Servo.class, "BootKicker2");
        bootKickerAlt = hardwareMap.get(Servo.class, "BootKicker");
        intakeWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void intake(double power) {
        intakeWheel.setPower(power);
    }

    public void bootKick(double pos) {
        bootKicker.setPosition(pos);
    }

    public void bootKickAlt(double pos) {
        bootKickerAlt.setPosition(pos);
    }

}


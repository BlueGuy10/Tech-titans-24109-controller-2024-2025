package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeController{
    private DcMotor intakeWheel;
    private Servo gate;
//    private Servo bootKickerAlt;

    public IntakeController (HardwareMap hardwareMap) {
        intakeWheel = hardwareMap.get(DcMotor.class, "Intake");
        gate = hardwareMap.get(Servo.class, "Gate");
        intakeWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void intake(double power) {
        intakeWheel.setPower(power);
    }

    public void setGate(double pos) {
        gate.setPosition(pos);
    }
    public double getServoPos(){
        return gate.getPosition();
    }

}


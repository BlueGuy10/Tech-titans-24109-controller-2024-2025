package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShooterWheelController {

    private DcMotorEx shooterWheel;
    private ElapsedTime runtime = new ElapsedTime();
    double prevTime = 0;
    double prevPosition = 0;


    public ShooterWheelController(HardwareMap hardwareMap) {
        shooterWheel = hardwareMap.get(DcMotorEx.class, "Shooter");
        shooterWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void resetEncoders() {
        shooterWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runWithEncoders() {
        shooterWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void spinWheel(double power) {
        shooterWheel.setPower(power);
    }

    public double getShooterPosition() {
        return shooterWheel.getCurrentPosition();
    }

    public double getRPM() {
        //double TPR = shooterWheel.getMotorType().getTicksPerRev();
        double revolutions;
        double currentRPM;
        double position = shooterWheel.getCurrentPosition();
        double time = runtime.milliseconds() / 1000;
        revolutions = Math.abs(position - prevPosition) / 24;
        currentRPM = (revolutions / (time - prevTime)) * 60;
        prevPosition = position;
        prevTime = time;
        return currentRPM;
    }

    public double getElapsedTime() {
        return runtime.milliseconds() / 1000;
    }

    public double getTicksPerSecond() {
        return shooterWheel.getVelocity();
    }

    public double getTicksPerRevolution() {
        return shooterWheel.getMotorType().getTicksPerRev();
    }

    public String getMotorName() {
        return shooterWheel.getMotorType().getName();
    }

    public double getAltRPM() {
        //double ticksPerRev = shooterWheel.getMotorType().getTicksPerRev();
        double ticksPerSecond = shooterWheel.getVelocity();
        return (ticksPerSecond / 24) * 60;
    }
}
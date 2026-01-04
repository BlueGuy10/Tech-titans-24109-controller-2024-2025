package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.ShooterWheelController;
import org.firstinspires.ftc.teamcode.motions.PidController;
import org.firstinspires.ftc.teamcode.sensor.util.DistanceToPowerConverter;

public class ShooterAction2 implements IAction{
    private final ShooterWheelController shooterWheelController;
    private final Telemetry telemetry;
    private final double power;
    private boolean isFinished = false;
    int iterates = 0;

    public ShooterAction2(ShooterWheelController shooterWheelController, Telemetry telemetry, double power) {
        this.shooterWheelController = shooterWheelController;
        this.telemetry = telemetry;
        this.power = power;
    }

    @Override
    public boolean init() {
        shooterWheelController.resetEncoders();
        shooterWheelController.runWithEncoders();
        return isInitialized();
    }

    @Override
    public boolean isInitialized() {
        return true;
    }

    @Override
    public boolean iterate() {
        if (iterates >= 10000) {
            isFinished = true;
            return true;
        }else {
            shooterWheelController.spinWheel(1);
            telemetry.addData("rpm", shooterWheelController.getRPM(telemetry));
            telemetry.addData("Current Position", shooterWheelController.getShooterPosition());
            telemetry.update();
            isFinished = false;
            return false;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public boolean stop() {
        return false;
    }

    @Override
    public boolean isStopped() {
        return false;
    }
}

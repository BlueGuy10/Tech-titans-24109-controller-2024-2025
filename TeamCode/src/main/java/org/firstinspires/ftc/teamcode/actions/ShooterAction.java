package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.ShooterWheelController;

public class ShooterAction implements IAction{
    private final ShooterWheelController shooterWheelController;
    private final Telemetry telemetry;
    private final double power;
    private boolean isFinished = false;


    public ShooterAction(Telemetry telemetry, ShooterWheelController shooterWheelController, double power) {
        this.telemetry = telemetry;
        this.shooterWheelController = shooterWheelController;
        this.power = power;
    }

    @Override
    public boolean init() {
        return isInitialized();
    }

    @Override
    public boolean isInitialized() {
        return true;
    }

    @Override
    public boolean iterate() {
        shooterWheelController.spinWheel(-power);
        isFinished = true;
        return true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public boolean stop() {
        // TODO - implement
        return false;
    }

    @Override
    public boolean isStopped() {
        // TODO - implement
        return false;
    }
}

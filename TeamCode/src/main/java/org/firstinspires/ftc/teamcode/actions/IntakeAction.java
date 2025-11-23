package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.IntakeController;

public class IntakeAction implements IAction{
    int iterates = 0;
    private final IntakeController intakeController;
    private final Telemetry telemetry;
    private final double power;

    public IntakeAction(IntakeController intakeController, Telemetry telemetry, double power) {
        this.intakeController = intakeController;
        this.telemetry = telemetry;
        this.power = power;
    }


    @Override
    public boolean init() {
        return false;
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public boolean iterate() {
        intakeController.intake(power);
        iterates++;
        return true;
    }

    @Override
    public boolean isFinished() {
        if (iterates >= 100000) {
            return true;
        }
        else {
            return false;
        }
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

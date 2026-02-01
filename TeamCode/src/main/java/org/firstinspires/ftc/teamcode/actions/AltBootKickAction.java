package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.IntakeController;

public class AltBootKickAction implements IAction{
    int iterates = 0;
    private final IntakeController intakeController;
    private final Telemetry telemetry;
    private final double position;

    public AltBootKickAction(IntakeController intakeController, Telemetry telemetry, double position) {
        this.intakeController = intakeController;
        this.telemetry = telemetry;
        this.position = position;
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
        //intakeController.bootKickAlt(position);
        telemetry.addLine("running BootKickAlt");
        iterates++;
        return false;
    }

    @Override
    public boolean isFinished() {
        if (iterates >= 50000) {
            return true;
        } else {
            telemetry.update();
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

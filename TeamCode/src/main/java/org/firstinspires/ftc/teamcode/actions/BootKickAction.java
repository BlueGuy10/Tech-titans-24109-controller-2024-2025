package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.IntakeController;

public class BootKickAction implements IAction {
    int iterates = 0;
    private final IntakeController intakeController;
    private final Telemetry telemetry;
    private final double position;

    public BootKickAction(IntakeController intakeController, Telemetry telemetry, double position) {
        this.intakeController = intakeController;
        this.telemetry = telemetry;
        this.position = position;
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public boolean isInitialized() {
        return true;
    }

        @Override
        public boolean iterate () {
            intakeController.bootKick(position);
            telemetry.addLine("running bootkick");
            iterates++;
            return true;
        }

        @Override
        public boolean isFinished () {
            if (iterates >= 50000) {
                return true;
            } else {
                telemetry.update();
                return false;
            }
        }

        @Override
        public boolean stop () {
            return true;
        }

        @Override
        public boolean isStopped () {
        return true;
        }
    }

package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.IntakeController;

public class GateAction implements IAction {
    private final IntakeController intakeController;
    private final Telemetry telemetry;
    private final double position;

    public GateAction(IntakeController intakeController, Telemetry telemetry, double position) {
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
            intakeController.setGate(position);
            telemetry.addLine("running bootkick");
            return true;
        }

        @Override
        public boolean isFinished () {
            if (intakeController.getServoPos() == position) {
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

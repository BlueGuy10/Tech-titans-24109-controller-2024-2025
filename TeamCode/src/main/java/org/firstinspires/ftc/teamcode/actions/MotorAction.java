package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Jonah.ImuUtility;
import org.firstinspires.ftc.teamcode.Jonathan.MecanumWheelsController;
import org.firstinspires.ftc.teamcode.motions.PidController;
import org.firstinspires.ftc.teamcode.motions.TimeService;

public class MotorAction implements IAction {
    public static final double MOVEMENT_ERROR = 1;

    private final MecanumWheelsController wheels;
    private final Telemetry telemetry;
    private final double targetDistance;
    private final PidController pidController;

    private boolean isInitialized = false;

    public MotorAction(ImuUtility imuUtility, MecanumWheelsController wheels, Telemetry telemetry, double distance) {
        this.wheels = wheels;
        this.telemetry = telemetry;
        this.targetDistance = distance;
        this.pidController = new PidController(0.03, 0, 0, new TimeService());
    }

    @Override
    public boolean init() {
        wheels.resetEncoders();
        wheels.runWithEncoders();
        isInitialized = true;
        return isInitialized();
    }

    @Override
    public boolean isInitialized() {
        return this.isInitialized;
    }

    @Override
    public boolean iterate() {
        double currentDistance = wheels.getDistance();
        double remainingDistance = targetDistance - currentDistance;

        double powerValue = pidController.calculatePower(remainingDistance) * -1;
        wheels.autoDrive(powerValue, powerValue, powerValue, powerValue);
        telemetry.addData("power", powerValue);
        telemetry.addData("current distance", currentDistance);
        telemetry.addData("remaining distance", remainingDistance);
        return true;
    }

    @Override
    public boolean isFinished() {
        double currentDistance = wheels.getDistance();
        double remainingDistance = targetDistance - currentDistance;
        if (targetDistance < 0) {
            remainingDistance = -remainingDistance;
        }
        if (Math.abs(remainingDistance) < MOVEMENT_ERROR) {
            wheels.autoDrive(0, 0, 0, 0);
            telemetry.addLine("stopped");
            return true;
        } else {
            return false;
        }
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

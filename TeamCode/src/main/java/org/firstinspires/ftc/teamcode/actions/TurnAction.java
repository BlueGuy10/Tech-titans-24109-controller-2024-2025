package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Jonah.ImuUtility;
import org.firstinspires.ftc.teamcode.Jonathan.MecanumWheelsController;
import org.firstinspires.ftc.teamcode.motions.PidController;
import org.firstinspires.ftc.teamcode.motions.TimeService;

public class TurnAction implements IAction {

    public static final double ANGLE_ERROR = 5; // degrees

    private final MecanumWheelsController wheels;
    private final ImuUtility imuCalculator;
    private final double turnAngle;
    private double targetAngle;
    private final Telemetry telemetry;
    private final PidController pidController;

    private boolean isInitialized = false;

    public TurnAction(ImuUtility imuUtility, double turnAngle,
                      MecanumWheelsController wheels, Telemetry telemetry) {
        this.imuCalculator = imuUtility;
        this.turnAngle = turnAngle;
        this.targetAngle = 0;
        this.wheels = wheels;
        this.telemetry = telemetry;
        this.pidController = new PidController(0.03, 0, 0, new TimeService());
    }

    @Override
    public boolean init() {
        imuCalculator.reset();
        targetAngle = imuCalculator.getCurrentAngle() + turnAngle;
        isInitialized = true;
        return isInitialized();
    }

    @Override
    public boolean isInitialized() {
        return this.isInitialized;
    }

    @Override
    public boolean iterate() {
        double currentAngle = imuCalculator.getCurrentAngle();
        double remainingAngle = targetAngle - currentAngle;

        double powerValue = pidController.calculatePower(remainingAngle);
        double leftPower = powerValue;
        double rightPower = -powerValue;
        wheels.autoDrive(leftPower, leftPower, rightPower, rightPower);

        telemetry.addData("current angle!", currentAngle);
        telemetry.addData("remaining angle", remainingAngle);
        telemetry.addData("target. ", targetAngle);
        telemetry.update();

        return true;
    }

    @Override
    public boolean isFinished() {
        double currentAngle = imuCalculator.getCurrentAngle();
        double remainingAngle = targetAngle - currentAngle;

        if (Math.abs(remainingAngle) < ANGLE_ERROR) {
            wheels.autoDrive(0, 0, 0, 0);
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

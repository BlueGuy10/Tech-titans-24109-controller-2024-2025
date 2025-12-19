package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.ShooterWheelController;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.motions.PidController;
import org.firstinspires.ftc.teamcode.motions.TimeService;
import org.firstinspires.ftc.teamcode.sensor.apriltag.AprilTagDetector;
import org.firstinspires.ftc.teamcode.sensor.apriltag.DecodeAprilTagDetector;
import org.firstinspires.ftc.teamcode.sensor.util.DistanceToPowerConverter;

public class ShooterAction implements IAction{
    private final ShooterWheelController shooterWheelController;
    private final Telemetry telemetry;
    private final double power;
    private boolean isFinished = false;
    private final PidController pidController;
    private final AprilTagDetector aprilTagDetector;
    private final Alliance alliance;
    private final DecodeAprilTagDetector decodeAprilTagDetector;


    public ShooterAction(Telemetry telemetry, ShooterWheelController shooterWheelController, double power, AprilTagDetector aprilTagDetector, Alliance alliance) {
        this.telemetry = telemetry;
        this.shooterWheelController = shooterWheelController;
        this.power = power;
        this.pidController = new PidController(0.03, 0, 0, new TimeService());
        this.aprilTagDetector = aprilTagDetector;
        this.alliance = alliance;
        this.decodeAprilTagDetector = new DecodeAprilTagDetector(aprilTagDetector);
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
        if (decodeAprilTagDetector.isGoalDetected(alliance)) {
            shooterWheelController.spinWheel(-DistanceToPowerConverter.convert(aprilTagDetector.getAprilTags().get(0).ftcPose.range));
            telemetry.addData("rpm", shooterWheelController.getRPM(telemetry));
            telemetry.addData("Current Position", shooterWheelController.getShooterPosition());
            telemetry.update();
            isFinished = true;
        }
        return true;
    }

    @Override
    public boolean isFinished() {
            return  isFinished;
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

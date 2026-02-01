package org.firstinspires.ftc.teamcode.actions;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.ShooterWheelController;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.motions.PidController;
import org.firstinspires.ftc.teamcode.motions.TimeService;
import org.firstinspires.ftc.teamcode.sensor.apriltag.AprilTagDetector;
import org.firstinspires.ftc.teamcode.sensor.apriltag.DecodeAprilTagDetector;

public class ShooterAction2 implements IAction {
    private ElapsedTime elapsedTime;
    private final ShooterWheelController shooterWheelController;
    private final JoinedTelemetry telemetry;
    private final double targetRPM;
    private boolean isFinished = false;
    private final int maxTime;
    private final PidController pidController;
    private final AprilTagDetector aprilTagDetector;
    private final Alliance alliance;
    private final DecodeAprilTagDetector decodeAprilTagDetector;

    public ShooterAction2(ShooterWheelController shooterWheelController, Telemetry telemetry, double targetRPM, int maxTime, AprilTagDetector aprilTagDetector, Alliance alliance) {
        this.shooterWheelController = shooterWheelController;
        this.telemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);
        this.targetRPM = targetRPM;
        this.maxTime = maxTime;
        this.aprilTagDetector = aprilTagDetector;
        this.alliance = alliance;
        this.pidController = new PidController(0.03, 0, 0.01, new TimeService());
        this.decodeAprilTagDetector = new DecodeAprilTagDetector(aprilTagDetector);
    }

    @Override
    public boolean init() {
      //  shooterWheelController.resetEncoders();
        shooterWheelController.runWithoutEncoders();
        elapsedTime = new ElapsedTime();
        return isInitialized();
    }

    @Override
    public boolean isInitialized() {
        return true;
    }

    @Override
    public boolean iterate() {
        if (elapsedTime.milliseconds() >= maxTime) {
            isFinished = true;
            return true;
        } else {
            double distanceFromNet = decodeAprilTagDetector.getDistance();
            double targetRPM2 = distanceFromNet * 49.19; //cm * revolutions per cm, no base for 0 dist (still needs rpm)
            double currentRPM = shooterWheelController.getRPM();
            double remainingRPM = targetRPM2 - currentRPM;
            double power = pidController.calculatePower(remainingRPM);
            shooterWheelController.spinWheel(power);
            telemetry.addData("Power", power);
            telemetry.addData("rpm", shooterWheelController.getRPM());
            telemetry.addData("Current Position", shooterWheelController.getShooterPosition());
            telemetry.addData("Elapsed Time", shooterWheelController.getElapsedTime());
            telemetry.addData("TicksPerSecond", shooterWheelController.getTicksPerSecond());
            telemetry.addData("TicksPerRevolution", shooterWheelController.getTicksPerRevolution());
            telemetry.addData("Motor Name", shooterWheelController.getMotorName());
            telemetry.addData("distance", distanceFromNet); //cm
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

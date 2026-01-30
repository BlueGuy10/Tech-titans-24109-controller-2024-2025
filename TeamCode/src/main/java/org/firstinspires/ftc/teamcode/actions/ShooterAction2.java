package org.firstinspires.ftc.teamcode.actions;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.ShooterWheelController;

public class ShooterAction2 implements IAction {
    private ElapsedTime elapsedTime;
    private final ShooterWheelController shooterWheelController;
    private final JoinedTelemetry telemetry;
    private final double power;
    private boolean isFinished = false;
    private final int maxTime;

    public ShooterAction2(ShooterWheelController shooterWheelController, Telemetry telemetry, double power, int maxTime) {
        this.shooterWheelController = shooterWheelController;
        this.telemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);
        this.power = power;
        this.maxTime = maxTime;
    }

    @Override
    public boolean init() {
        shooterWheelController.resetEncoders();
        shooterWheelController.runWithEncoders();
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
            shooterWheelController.spinWheel(-power);
            telemetry.addData("Power", power);
            telemetry.addData("rpm", shooterWheelController.getRPM());
            telemetry.addData("Current Position", shooterWheelController.getShooterPosition());
            telemetry.addData("Elapsed Time", shooterWheelController.getElapsedTime());
            telemetry.addData("TicksPerSecond", shooterWheelController.getTicksPerSecond());
            telemetry.addData("TicksPerRevolution", shooterWheelController.getTicksPerRevolution());
            telemetry.addData("Motor Name", shooterWheelController.getMotorName());
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

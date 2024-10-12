package org.firstinspires.ftc.teamcode.Jonah;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/** Configuration File
 *Control Hub:
 *Servo Port 03: wrist
 */

@Disabled
@TeleOp(group="Servos")

public class ServoOne extends LinearOpMode{

    private Servo wrist;
    private double wristInitPosition = 0.5;
    private double wristPositionOne = 0.0;
    private double wristPositionTwo = 1.0;
    private int wristDelay = 10;

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {
            wristTelemetry();
        }
        waitForStart();
        while (opModeIsActive()){
            wristTelemetry();
            teleOpControls();
        }
    }

    public void initHardware() {
        initWrist();
    }

    public void initWrist() {
        wrist = hardwareMap.get(Servo.class, "wrist");
        wrist.setDirection(Servo.Direction.FORWARD);
        wrist.setPosition(wristInitPosition);
    }

    public void teleOpControls() {

        if (gamepad1.a) {
            wrist.setPosition(wristPositionOne);
        }
        if (gamepad1.b) {
            wrist.setPosition(wristPositionTwo);
        }
    }

    public void wristTelemetry() {
        telemetry.log().clear();
        telemetry.addData("Position", wrist.getPosition());
        telemetry.addData("Direction", wrist.getDirection());
        telemetry.addData("Device Name", wrist.getDeviceName());
        telemetry.update();
    }
}
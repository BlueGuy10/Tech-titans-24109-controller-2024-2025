package org.firstinspires.ftc.teamcode.testopmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.sensor.apriltag.AprilTagDetector;
import org.firstinspires.ftc.teamcode.sensor.apriltag.DecodeAprilTagDetector;

public class DetectMotif extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DecodeAprilTagDetector detecter = new DecodeAprilTagDetector(new AprilTagDetector(hardwareMap.get(WebcamName.class, "Webcam 1")));
        waitForStart();
//        detecter.start();
        while (opModeIsActive()) {
            telemetry.addData("Detected: ", detecter.getMotif());
        }
    }
}

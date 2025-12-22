package org.firstinspires.ftc.teamcode.testopmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.sensor.apriltag.AprilTagDetector;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous
public class AprilTagDataDetection extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        CameraName cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");
        AprilTagDetector detector = new AprilTagDetector(cameraName);

        waitForStart();
        while (opModeIsActive()) {
//            if (detector.isStopped()) {
//                detector.start();
//            }
            List<AprilTagDetection> currentDetections = detector.getAprilTags();

            if (!currentDetections.isEmpty()) {
                for (AprilTagDetection detection : currentDetections) {
                    if (detection.id >= 20 && detection.id <= 24) {
                        double angleToTag = Math.toDegrees(Math.atan2(detection.ftcPose.x, detection.ftcPose.y));
                        telemetry.addData("Tag ID", detection.id);
                        telemetry.addData("Tag Pose X", detection.ftcPose.x);
                        telemetry.addData("Tag Pose Y", detection.ftcPose.y);
                        telemetry.addData("Tag Pose Z", detection.ftcPose.z);
                        telemetry.addData("Tag Pose Bearing", detection.ftcPose.bearing);
                        telemetry.addData("Tag Pose Yaw", detection.ftcPose.yaw); //
                        telemetry.addData("Tag Pose Elevation", detection.ftcPose.elevation);
                        telemetry.addData("Tag Pose Pitch", detection.ftcPose.pitch);
                        telemetry.addData("Tag Pose Range", detection.ftcPose.range);
                        telemetry.addData("Tag Pose Roll", detection.ftcPose.roll);
                        if (detection.id == 20) {
                            telemetry.addLine(String.format("turn %s degrees", angleToTag));
                        }
                    } else {
                        telemetry.addLine("Unknown april tag detected");
                    }
                }
            }
            telemetry.update();
        }

//        detector.stop();
    }
}

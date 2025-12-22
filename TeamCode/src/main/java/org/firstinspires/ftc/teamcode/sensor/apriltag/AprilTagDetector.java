package org.firstinspires.ftc.teamcode.sensor.apriltag;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.Collections;
import java.util.List;

public class AprilTagDetector implements IAprilTagDetector {

    private final CameraName camera;
    private boolean started = false;

    private final VisionPortal visionPortal;
    private final AprilTagProcessor aprilTagProcessor;

    public AprilTagDetector(CameraName camera) {
        this.camera = camera;
        this.aprilTagProcessor = new AprilTagProcessor.Builder()
                .setTagLibrary(AprilTagGameDatabase.getCurrentGameTagLibrary())
                .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES)
                .build();
        this.visionPortal = new VisionPortal.Builder()
                .setCamera(camera)
                .addProcessor(aprilTagProcessor)
                .build();
        //this.stop();
        started = true;
    }

    @Override
    public List<AprilTagDetection> getAprilTags() {
        if (started) {
            return aprilTagProcessor.getDetections();
        }
        return Collections.emptyList();
    }

//    @Override
//    public boolean start() {
//        visionPortal.resumeStreaming();
//        started = true;
//        return started;
//    }
//
//    @Override
//    public boolean isStarted() {
//        return started;
//    }
//
//    @Override
//    public boolean stop() {
//        visionPortal.stopStreaming();
//        started = false;
//        return started;
//    }
//
//    @Override
//    public boolean isStopped() {
//        return !started;
//    }
}

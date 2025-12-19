package org.firstinspires.ftc.teamcode.sensor.apriltag;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.decode.Motif;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

public class DecodeAprilTagDetector implements IDecodeAprilTagDetector {

    public static final int RED_GOAL_APRIL_TAG_ID = 24;
    public static final int BLUE_GOAL_APRIL_TAG_ID = 20;

    private final IAprilTagDetector aprilTagDetector;

    public DecodeAprilTagDetector(IAprilTagDetector aprilTagDetector) {
        this.aprilTagDetector = aprilTagDetector;
    }

    @Override
    public boolean isRedGoalDetected() {
        if (isStarted()) {
            List<AprilTagDetection> aprilTags = aprilTagDetector.getAprilTags();
            for (AprilTagDetection aprilTag : aprilTags) {
                if (aprilTag.id == RED_GOAL_APRIL_TAG_ID) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isBlueGoalDetected() {
        if (isStarted()) {
            List<AprilTagDetection> aprilTags = aprilTagDetector.getAprilTags();
            for (AprilTagDetection aprilTag : aprilTags) {
                if (aprilTag.id == BLUE_GOAL_APRIL_TAG_ID) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isMotifDetected() {
        if (isStarted()) {
            List<AprilTagDetection> aprilTags = aprilTagDetector.getAprilTags();
            for (AprilTagDetection aprilTag : aprilTags) {
                if (aprilTag.id >= 21 && aprilTag.id <= 23) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Motif getMotif() {
        if (isStarted()) {
            List<AprilTagDetection> aprilTags = aprilTagDetector.getAprilTags();
            for (AprilTagDetection aprilTag : aprilTags) {
                if (Motif.getByAprilTagId(aprilTag.id) != Motif.Unknown) {
                    return Motif.getByAprilTagId(aprilTag.id);
                }
            }
        }
        return Motif.Unknown;
    }

    @Override
    public boolean isGoalDetected(Alliance alliance) {
        return (alliance == Alliance.RED && isRedGoalDetected())
                || (alliance == Alliance.BLUE && isBlueGoalDetected());
    }

    @Override
    public double getDistance() {
        return 0;
    }

    @Override
    public boolean start() {
        return aprilTagDetector.start();
    }

    @Override
    public boolean isStarted() {
        return aprilTagDetector.isStarted();
    }

    @Override
    public boolean stop() {
        return aprilTagDetector.stop();
    }

    @Override
    public boolean isStopped() {
        return aprilTagDetector.isStopped();
    }
}

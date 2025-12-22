package org.firstinspires.ftc.teamcode.sensor.apriltag;

import org.firstinspires.ftc.teamcode.Startable;
import org.firstinspires.ftc.teamcode.Stoppable;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

public interface IAprilTagDetector {

    /**
     * Get the current list of detected april tags
     *
     * @return the current list of detected april tags
     */
    List<AprilTagDetection> getAprilTags();

}

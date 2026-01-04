package org.firstinspires.ftc.teamcode.sensor.util;

public class DistanceToPowerConverter {

    public static final double POWER_CONSTANT = 0.3;

    public static final double TO_REAL_DISTANCE_SLOPE = 1.115;

    public static final double TO_REAL_DISTANCE_Y_INTERCEPT = 3.6;

    private static double toRealDistance(double distance) {
        return TO_REAL_DISTANCE_SLOPE * distance + TO_REAL_DISTANCE_Y_INTERCEPT;
    }

    public static double convert(double distance) {
        return distance * POWER_CONSTANT;
    }
}

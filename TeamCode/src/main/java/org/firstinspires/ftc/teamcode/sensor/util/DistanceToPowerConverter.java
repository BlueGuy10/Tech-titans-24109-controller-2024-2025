package org.firstinspires.ftc.teamcode.sensor.util;

public class DistanceToPowerConverter {

    public static final double POWER_CONSTANT = 0.3;

    public static double convert(double distance) {
        return distance * POWER_CONSTANT;
    }
}

package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SensorController {

    private DigitalChannel ballSensor;



    public SensorController(HardwareMap hardwareMap) {
        ballSensor = hardwareMap.get(DigitalChannel.class, "ball_sensor");
        ballSensor.setMode(DigitalChannel.Mode.INPUT);
    }

    public boolean getTouchSensorState() {
        return ballSensor.getState();
    }

}

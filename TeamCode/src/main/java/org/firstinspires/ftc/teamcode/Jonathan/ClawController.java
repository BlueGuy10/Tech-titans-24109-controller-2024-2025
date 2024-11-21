package org.firstinspires.ftc.teamcode.Jonathan;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawController {
    private static final double servoOpen = 1;
    private static final double servoClosed = 0;
    
    private Servo servo;
    
    public void initializeClaw(@NonNull HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "Claw servo");
        servo.setDirection(Servo.Direction.FORWARD);
        servo.setPosition(servoOpen);
    }

    public void closeClaw() {
        servo.setPosition(servoClosed);
    }

    public void openClaw() {
        servo.setPosition(servoOpen);
    }

    public void toggleClaw() {
        if (servo.getPosition() == servoClosed) {
            servo.setPosition(servoOpen);
        } else {
            servo.setPosition(servoClosed);
        }
    }
}

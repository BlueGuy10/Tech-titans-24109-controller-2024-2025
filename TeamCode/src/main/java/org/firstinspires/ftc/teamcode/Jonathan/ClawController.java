package org.firstinspires.ftc.teamcode.Jonathan;

import androidx.annotation.NonNull;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawController {
    private static final double servoOpen = 0.85;
    private static final double servoClosed = 0.6;
    
    private Servo servo;

    public ClawController(@NonNull HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "Claw");
        servo.setDirection(Servo.Direction.FORWARD);
        servo.setPosition(servoClosed);
    }

    public void closeClaw() {
        servo.setPosition(servoClosed);
    }

    public void openClaw() {
        servo.setPosition(servoOpen);
    }

    public double getClawPosition() {
        return servo.getPosition();
    }

    public void toggleClaw() {
        if (servo.getPosition() == servoClosed) {
            servo.setPosition(servoOpen);
        } else {
            servo.setPosition(servoClosed);
        }
    }
}

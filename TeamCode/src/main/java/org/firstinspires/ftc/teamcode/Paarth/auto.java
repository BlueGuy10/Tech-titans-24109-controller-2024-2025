package org.firstinspires.ftc.teamcode.Paarth;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@Autonomous

public class auto extends LinearOpMode {
    private DcMotor slide;
    private Servo claw;
    private DcMotor pitch;
    private static final int servoOpen = 1;
    private static final int servoClosed = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {
        }
        waitForStart();
        while (opModeIsActive()) {

            //raiseArm();


        }
    }

    public void initHardware() {
        initClaw();
        initSlide();
        initPitch();
    }

    public void initClaw() {
        claw = hardwareMap.get(Servo.class, "Claw");
        claw.setDirection(Servo.Direction.FORWARD);
        claw.setPosition(servoClosed);
    }

    public void initSlide() {
        slide = hardwareMap.get(DcMotor.class, "ExtensionMotor");
        slide.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void initPitch() {
        pitch = hardwareMap.get(DcMotor.class, "PitchMotor");
        pitch.setDirection(DcMotorSimple.Direction.FORWARD);
    }


}

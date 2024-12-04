package org.firstinspires.ftc.teamcode.Paarth;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Jonathan.ArmController;
import org.firstinspires.ftc.teamcode.Jonathan.ClawController;
import org.firstinspires.ftc.teamcode.Jonathan.MecanumWheelsController;

//@Disabled
@Autonomous

public class auto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ClawController claw = new ClawController(hardwareMap);
        MecanumWheelsController wheels = new MecanumWheelsController(hardwareMap);
        ArmController arm = new ArmController(hardwareMap);
        waitForStart();
        sleep(500);
        arm.setPitchPower(700);
        sleep(500);
        arm.extensionPower(6000);
        sleep(500);
        claw.openClaw();
        sleep(500);
        arm.extensionPower(0);
        sleep(500);
        arm.setPitchPower(0);
        sleep(2000);

        terminateOpModeNow();
    }
}

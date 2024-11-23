package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "1 driver control", group = "Driver control")
public class RobotController extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ClawController claw = new ClawController(hardwareMap);
        MecanumWheelsController wheels = new MecanumWheelsController(hardwareMap);
        ArmController arm = new ArmController(hardwareMap);

        while (opModeIsActive()) {
            oneDriver(claw, wheels, arm);
        }
    }

    private void oneDriver(ClawController claw, MecanumWheelsController wheels, ArmController arm) {
        if (gamepad1.right_bumper) {
            claw.closeClaw();
        } else if (gamepad1.left_bumper) {
            claw.openClaw();
        }

        wheels.horizontalPower(gamepad1.left_stick_x);
        wheels.verticalPower(gamepad1.left_stick_y);
        wheels.turn(gamepad1.right_stick_x);

        arm.pitchPower(gamepad1.right_stick_y);
        arm.extensionPower(gamepad1.left_trigger - gamepad1.right_trigger);
    }
}

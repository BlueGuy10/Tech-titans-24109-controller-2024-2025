package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "1 driver control", group = "Driver control")
public class RobotController extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ClawController claw = new ClawController(hardwareMap);
        MecanumWheelsController wheels = new MecanumWheelsController(hardwareMap);
        ArmController arm = new ArmController(hardwareMap);
        waitForStart();
        arm.pitchPower(-300);
        while (opModeIsActive()) {
            oneDriver(claw, wheels, arm);
        }
    }

    private void oneDriver(ClawController claw, MecanumWheelsController wheels, ArmController arm) {
        if (gamepad2.right_bumper) {
            claw.closeClaw();
        } else if (gamepad2.left_bumper) {
            claw.openClaw();
        }

        wheels.applyPower(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        telemetry.addData("rB", gamepad2.right_bumper);
        telemetry.addData("lB", gamepad2.left_bumper);
        telemetry.addData("lY", Math.round(gamepad2.left_stick_y * 50));
        telemetry.addData("rY", Math.round(gamepad2.right_stick_y * 50));
        telemetry.update();
        arm.pitchPower(Math.round(gamepad2.left_stick_y * 50));
        arm.extensionPower(Math.round(gamepad2.right_stick_y * 50));
    }
}

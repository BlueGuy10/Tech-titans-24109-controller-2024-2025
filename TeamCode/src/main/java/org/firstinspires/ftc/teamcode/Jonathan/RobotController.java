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
        //arm.pitchPower(-300);
        waitForStart();
        while (opModeIsActive()) {
            oneDriver(claw, wheels, arm);
        }
    }

    private void oneDriver(ClawController claw, MecanumWheelsController wheels, ArmController arm) {
        double servoState;
        if (gamepad2.right_bumper) {
            claw.closeClaw();
            telemetry.addData("rB", gamepad2.right_bumper);
        } else if (gamepad2.left_bumper) {
            claw.openClaw();
            telemetry.addData("rB", gamepad2.right_bumper);
        }

        wheels.applyPower(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        arm.pitchPower(Math.round(gamepad2.left_stick_y * 50));
        arm.extensionPower(Math.round(gamepad2.right_stick_y * 150));

        if (gamepad2.a) {
            arm.setPitchPower(-1000); //-1000
            arm.setExtensionPower(-8870); //-8870
        }

        telemetry.addData("Servo State", claw.getClawPosition());

        telemetry.addData("Pitch position", arm.getPitchPosition());
        telemetry.addData("Extension position", arm.getExtensionPosition());

        telemetry.addData("lY", Math.round(gamepad2.left_stick_y * 50));
        telemetry.addData("rY", Math.round(gamepad2.right_stick_y * 50));
        telemetry.update();

    }
}

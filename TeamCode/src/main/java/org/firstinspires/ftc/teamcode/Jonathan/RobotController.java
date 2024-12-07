package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "1 driver control", group = "Driver control")
public class RobotController extends LinearOpMode {
    private boolean raisedArm = false;
    @Override

    public void runOpMode() throws InterruptedException {
        ClawController claw = new ClawController(hardwareMap);
        MecanumWheelsController wheels = new MecanumWheelsController(hardwareMap);
        ArmController arm = new ArmController(hardwareMap);
        arm.setPitch(75);
        claw.openClaw();
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

            arm.changePitch(Math.round(gamepad2.left_stick_y * 160));
//            telemetry.addData("Gpad2, LY", Math.round(gamepad2.left_stick_y * 200));
            arm.rawExtensionPower(gamepad2.right_stick_y);
//            telemetry.addData("Gpad2, RY", Math.round(gamepad2.right_stick_y * 200));
            telemetry.update();

        if (gamepad2.a) {
            raisedArm=true;
            arm.setPitch(600); //-1000
            arm.setExtension(7500); //-8870
        }
        if (gamepad2.b) {
            arm.setExtension(0);
            arm.setPitch(75);
            raisedArm=false;
        }

        telemetry.addData("Servo State", claw.getClawPosition());

        telemetry.addData("Pitch position", arm.getPitchPosition());
        telemetry.addData("Extension position", arm.getExtensionPosition());

        telemetry.addData("lY", Math.round(gamepad2.left_stick_y * 50));
        telemetry.addData("rY", gamepad2.right_stick_y);

        telemetry.addData("max", arm.getMaxExtension(arm.getPitchPosition()));

        telemetry.update();

    }
}

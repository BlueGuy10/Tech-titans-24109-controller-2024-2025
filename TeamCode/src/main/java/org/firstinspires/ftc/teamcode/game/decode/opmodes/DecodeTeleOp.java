package org.firstinspires.ftc.teamcode.game.decode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Jonathan.MecanumWheelsController;
import org.firstinspires.ftc.teamcode.controllers.IntakeController;
import org.firstinspires.ftc.teamcode.controllers.ShooterWheelController;

@TeleOp(name = "Decode TeleOp")
public class DecodeTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumWheelsController mecanumWheelsController = new MecanumWheelsController(hardwareMap);
        IntakeController intakeController = new IntakeController(hardwareMap);
        ShooterWheelController shooterWheelController = new ShooterWheelController(hardwareMap);
        intakeController.bootKick(1);
        waitForStart();
        while (opModeIsActive()) {
            float wheelsFineControlValue = 1;
            if (gamepad1.left_bumper) {
                wheelsFineControlValue = 0.5f;
            }
            mecanumWheelsController.applyPower(gamepad1.left_stick_x * wheelsFineControlValue, gamepad1.left_stick_y * wheelsFineControlValue, gamepad1.right_stick_x * wheelsFineControlValue);

            if (gamepad1.left_trigger > 0.1) {
                intakeController.intake(0.75);
            } else {
                intakeController.intake(0);
            }

            if (gamepad1.x) {
                shooterWheelController.spinWheel(0.05);
            } else if (gamepad1.right_bumper) {
                shooterWheelController.spinWheel(-1);
            } else {
                shooterWheelController.spinWheel(0);
            }

            if (gamepad1.right_trigger > 0.1) {
                intakeController.bootKick(-1);
            } else {
                intakeController.bootKick(1);
            }
        }
    }
}

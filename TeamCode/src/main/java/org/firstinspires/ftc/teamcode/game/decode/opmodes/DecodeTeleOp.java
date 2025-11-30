package org.firstinspires.ftc.teamcode.game.decode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

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
        boolean twoControllers = false;
        while (opModeInInit()) {
            if (gamepad1.dpad_up) twoControllers = true;
            if (gamepad1.dpad_down) twoControllers = false;
        }
        waitForStart();
        Gamepad movePad = gamepad1;
        Gamepad shootPad = (twoControllers) ? gamepad2 : gamepad1;
        while (opModeIsActive()) {
            float wheelsFineControlValue = 1;
            if (shootPad.left_bumper) {
                wheelsFineControlValue = 0.5f;
            }
            mecanumWheelsController.applyPower(movePad.left_stick_x * wheelsFineControlValue, movePad.left_stick_y * wheelsFineControlValue, movePad.right_stick_x * wheelsFineControlValue);

            if (shootPad.left_trigger > 0.1) {
                intakeController.intake(0.75);
            } else {
                intakeController.intake(0);
            }

            if (shootPad.x) {
                shooterWheelController.spinWheel(0.05);
            } else if (shootPad.right_bumper) {
                shooterWheelController.spinWheel(-1);
            } else {
                shooterWheelController.spinWheel(0);
            }

            if (shootPad.right_trigger > 0.1) {
                intakeController.bootKick(-1);
            } else {
                intakeController.bootKick(1);
            }
        }
    }
}

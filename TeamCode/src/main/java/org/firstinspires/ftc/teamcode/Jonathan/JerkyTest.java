package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class JerkyTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        ArmController arm = new ArmController(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            arm.rawExtensionPower(gamepad1.left_stick_y);

            telemetry.addData("lSY", gamepad1.left_stick_y);
            telemetry.addData("max", arm.getMaxExtension(arm.getPitchPosition()));
            telemetry.addData("pitch", arm.getPitchPosition());
            telemetry.addData("extension", arm.getExtensionPosition());
            telemetry.update();
        }
    }
}

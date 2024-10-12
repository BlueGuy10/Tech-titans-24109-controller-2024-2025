package org.firstinspires.ftc.teamcode.Paarth;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


/**
 * Config. File
 *
 *
 */



@Disabled
@TeleOp(group = "Primary")

public class FirstClass extends LinearOpMode {
    
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while (!isStarted()) {

        }

        waitForStart();
        while (opModeIsActive()) {

        }

    }

    public void initHardware() {
        initVoltageSensor();
    }

    public void initVoltageSensor() {}


}

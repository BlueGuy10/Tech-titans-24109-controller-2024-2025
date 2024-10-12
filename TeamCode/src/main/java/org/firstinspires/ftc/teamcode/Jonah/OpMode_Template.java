package org.firstinspires.ftc.teamcode.Jonah;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/** Configuration File
 *
 *
 */

@Autonomous(name="myTest")
@Disabled
public class OpMode_Template extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {
        }
        waitForStart();
        while (opModeIsActive()) {
        }
    }

    public void initHardware() {

    }

}
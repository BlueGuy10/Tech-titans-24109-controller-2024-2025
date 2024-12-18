package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp(name="color sensor shower", group="gpio")
public class ColorSensorLedShower extends LinearOpMode {
    private DigitalChannel redLed;
    private DigitalChannel blueLed;
    private DigitalChannel yellowLed;

    private ColorSensor colorSensor;

    @Override
    public void runOpMode() {
        redLed = hardwareMap.get(DigitalChannel.class, "redLed");
        blueLed = hardwareMap.get(DigitalChannel.class, "blueLed");
        yellowLed = hardwareMap.get(DigitalChannel.class, "yellowLed");

        redLed.setMode(DigitalChannel.Mode.OUTPUT);
        blueLed.setMode(DigitalChannel.Mode.OUTPUT);
        yellowLed.setMode(DigitalChannel.Mode.OUTPUT);

        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");

        waitForStart();

        while (opModeIsActive()) {
            // Get the color values
            int red = colorSensor.red();
            int green = colorSensor.green();
            int blue = colorSensor.blue();
            int alpha = colorSensor.alpha(); // Combined color intensity

            // Display color values on telemetry
            telemetry.addData("Red", red);
            telemetry.addData("Green", green);
            telemetry.addData("Blue", blue);
            telemetry.addData("Alpha", alpha);
            telemetry.update();

            resetLeds();

            if (red > 200) {
                yellowLed.setState(true);
            } else if (red > 100) {
                redLed.setState(true);
            } else {
                blueLed.setState(true);
            }
        }

        resetLeds();
    }

    private void resetLeds() {
        redLed.setState(false);
        blueLed.setState(false);
        yellowLed.setState(false);
    }
}

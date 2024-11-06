package org.firstinspires.ftc.teamcode.Jonathan;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

/*
* https://revrobotics.ca/content/docs/REV-31-1595-UM.pdf
* page 4 shows where the pins are located
*
* Digital pin 0 ----- LED ----- 220 ohm resistor -----.
*                                                     |
* GND ------------------------------------------------'
*
* */

@TeleOp(name="Gpio Led Blink Test", group="gpio")
public class GpioLedBlinkTest extends LinearOpMode {

    // Declare the digital channel variable
    private DigitalChannel led;

    @Override
    public void runOpMode() {
        // Initialize the digital channel for the LED using hardwareMap
        led = hardwareMap.get(DigitalChannel.class, "redLed");

        // Set the channel mode to OUTPUT for controlling the LED
        led.setMode(DigitalChannel.Mode.OUTPUT);

        waitForStart();

        // Main loop: Toggle the LED on and off with a delay
        while (opModeIsActive()) {
            // Turn the LED on
            led.setState(true); // HIGH
            telemetry.addData("LED State", "ON");
            telemetry.update();

            // Wait for half a second
            sleep(1000);

            // Turn the LED off
            led.setState(false); // LOW
            telemetry.addData("LED State", "OFF");
            telemetry.update();

            // Wait for half a second
            sleep(1000);
        }
        // Turn the LED off
        led.setState(false);
    }
}


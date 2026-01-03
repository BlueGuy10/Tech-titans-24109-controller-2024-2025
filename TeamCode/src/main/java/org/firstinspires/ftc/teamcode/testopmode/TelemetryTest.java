package org.firstinspires.ftc.teamcode.testopmode;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class TelemetryTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        JoinedTelemetry joinedTelemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);
        waitForStart();
        int n = 0;
        while (opModeIsActive()) {
            joinedTelemetry.addData("n", n);
            n++;
            joinedTelemetry.update();
            joinedTelemetry.addLine(TestConfigurables.logMessage);
            Thread.sleep(1000);
            if (n >= TestConfigurables.finalValue) {
                break;
            }
        }
    }
}

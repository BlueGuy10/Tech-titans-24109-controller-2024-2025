package org.firstinspires.ftc.teamcode.game.decode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Jonah.ImuUtility;
import org.firstinspires.ftc.teamcode.Jonathan.MecanumWheelsController;
import org.firstinspires.ftc.teamcode.actions.BootKickAction;
import org.firstinspires.ftc.teamcode.actions.IntakeAction;
import org.firstinspires.ftc.teamcode.actions.MotorAction;
import org.firstinspires.ftc.teamcode.actions.ShooterAction;
import org.firstinspires.ftc.teamcode.actions.TelemetryAction;
import org.firstinspires.ftc.teamcode.actions.TimeWaitAction;
import org.firstinspires.ftc.teamcode.actions.TurnAction;
import org.firstinspires.ftc.teamcode.controllers.IntakeController;
import org.firstinspires.ftc.teamcode.controllers.ShooterWheelController;

@Autonomous(name = "Auto test Match 2", group = "Match Opmodes")
public class AutoTest_2_OpMode extends DecodeOpmode {

    @Override
    protected void createMatch() {
        IMU imu = hardwareMap.get(IMU.class, "imu");
        ImuUtility imuCalculator = new ImuUtility(imu);

        addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, -114.3));
        addAutoAction(new ShooterAction(telemetry, new ShooterWheelController(hardwareMap), 1));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, 1));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, -1));
        addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 1));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, 1));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, -1));
        addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 1));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, 1));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, -1));
        addAutoAction(new TimeWaitAction(1000));
        addAutoAction(new ShooterAction(telemetry, new ShooterWheelController(hardwareMap), 0));
    }
}

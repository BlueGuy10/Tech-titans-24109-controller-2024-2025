package org.firstinspires.ftc.teamcode.game.decode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.IMU;


import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Jonah.ImuUtility;
import org.firstinspires.ftc.teamcode.actions.AltBootKickAction;
import org.firstinspires.ftc.teamcode.actions.BootKickAction;
import org.firstinspires.ftc.teamcode.actions.IntakeAction;
import org.firstinspires.ftc.teamcode.actions.ShooterAction;
import org.firstinspires.ftc.teamcode.actions.TimeWaitAction;
import org.firstinspires.ftc.teamcode.controllers.IntakeController;
import org.firstinspires.ftc.teamcode.controllers.ShooterWheelController;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.decode.DecodeRobot;
import org.firstinspires.ftc.teamcode.sensor.apriltag.AprilTagDetector;

@Autonomous(name = "Match 1", group = "Match Opmodes")
public class Match_1_Opmode extends DecodeOpmode {
    private static final Pose2D START_POS = new Pose2D(DistanceUnit.CM, 0, 0, AngleUnit.DEGREES, 0);
    private static final Alliance ALLIANCE = Alliance.RED;


    @Override
    protected void createMatch() {
        IMU imu = hardwareMap.get(IMU.class, "imu");
        ImuUtility imuCalculator = new ImuUtility(imu);
        CameraName camera = hardwareMap.get(WebcamName.class, "Webcam 1");

        DecodeRobot robot = getRobot();
        robot.setStartPosition(START_POS);
        robot.setAlliance(ALLIANCE);
        // create autonomous actions
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, 0.5));
        addAutoAction(new AltBootKickAction(new IntakeController(hardwareMap), telemetry, 0.7));
        addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 1));
        addAutoAction(new TimeWaitAction(1500));
        addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0));
        addAutoAction(new TimeWaitAction(500));
        addAutoAction(new ShooterAction(telemetry, new ShooterWheelController(hardwareMap), 1, new AprilTagDetector(camera), robot.getAlliance()));
        addAutoAction(new TimeWaitAction(500));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, -1));
        addAutoAction(new AltBootKickAction(new IntakeController(hardwareMap), telemetry, 0.8));
        addAutoAction(new TimeWaitAction(500));
        addAutoAction(new AltBootKickAction(new IntakeController(hardwareMap), telemetry, 0.5));
        //addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0.7));
        //addAutoAction(new TimeWaitAction(2000));

    }
}
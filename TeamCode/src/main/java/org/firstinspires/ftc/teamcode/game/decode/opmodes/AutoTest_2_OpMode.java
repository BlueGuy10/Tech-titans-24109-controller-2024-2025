package org.firstinspires.ftc.teamcode.game.decode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Jonah.ImuUtility;
import org.firstinspires.ftc.teamcode.Jonathan.MecanumWheelsController;
import org.firstinspires.ftc.teamcode.actions.BootKickAction;
import org.firstinspires.ftc.teamcode.actions.IntakeAction;
import org.firstinspires.ftc.teamcode.actions.MotorAction;
import org.firstinspires.ftc.teamcode.actions.ShooterAction;
import org.firstinspires.ftc.teamcode.actions.TimeWaitAction;
import org.firstinspires.ftc.teamcode.controllers.IntakeController;
import org.firstinspires.ftc.teamcode.controllers.ShooterWheelController;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.decode.DecodeRobot;
import org.firstinspires.ftc.teamcode.sensor.apriltag.AprilTagDetector;

@Autonomous(name = "Auto test Match 2", group = "Match Opmodes")
public class AutoTest_2_OpMode extends DecodeOpmode {
    private static final Pose2D START_POS = new Pose2D(DistanceUnit.CM, 0, 0, AngleUnit.DEGREES, 0);
    private static final Alliance ALLIANCE = Alliance.BLUE;

    @Override
    protected void createMatch() {
        IMU imu = hardwareMap.get(IMU.class, "imu");
        ImuUtility imuCalculator = new ImuUtility(imu);
        CameraName camera = hardwareMap.get(WebcamName.class, "Webcam 1");

        DecodeRobot robot = getRobot();
        robot.setStartPosition(START_POS);
        robot.setAlliance(ALLIANCE);

        addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, -228.6));
        addAutoAction(new ShooterAction(telemetry, new ShooterWheelController(hardwareMap), 1, new AprilTagDetector(camera), robot.getAlliance()));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, 1));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, -1));
        addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 1));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, 1));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, -1));
        addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 1));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, 1));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, -1));
        addAutoAction(new TimeWaitAction(1000));
        addAutoAction(new ShooterAction(telemetry, new ShooterWheelController(hardwareMap), 0, new AprilTagDetector(camera), robot.getAlliance()));
    }
}

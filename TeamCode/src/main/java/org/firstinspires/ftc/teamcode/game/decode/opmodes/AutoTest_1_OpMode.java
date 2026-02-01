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
import org.firstinspires.ftc.teamcode.actions.AltBootKickAction;
import org.firstinspires.ftc.teamcode.actions.GateAction;
import org.firstinspires.ftc.teamcode.actions.IntakeAction;
import org.firstinspires.ftc.teamcode.actions.MotorAction;
import org.firstinspires.ftc.teamcode.actions.ShooterAction;
import org.firstinspires.ftc.teamcode.actions.StrafeAction;
import org.firstinspires.ftc.teamcode.actions.TimeWaitAction;
import org.firstinspires.ftc.teamcode.actions.TurnAction;
import org.firstinspires.ftc.teamcode.controllers.IntakeController;
import org.firstinspires.ftc.teamcode.controllers.ShooterWheelController;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.decode.DecodeRobot;
import org.firstinspires.ftc.teamcode.sensor.apriltag.AprilTagDetector;

@Autonomous(name = "Auto test Match 1", group = "Match Opmodes")
public class AutoTest_1_OpMode extends DecodeOpmode {

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
        // create autonomous actions
            addAutoAction(new GateAction(new IntakeController(hardwareMap), telemetry, 0.4)); // close gate
            addAutoAction(new AltBootKickAction(new IntakeController(hardwareMap), telemetry, 0.6));//bootKicker down
            addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, (robot.getAlliance() == Alliance.RED) ?  195 : 180));
            addAutoAction(new ShooterAction(telemetry, new ShooterWheelController(hardwareMap), 1, new AprilTagDetector(camera), robot.getAlliance())); //spin up shooter
            imuCalculator.reset();
            addAutoAction(new TurnAction(imuCalculator, (robot.getAlliance() == Alliance.RED) ? -45 : 45, new MecanumWheelsController(hardwareMap), telemetry));
            addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, 45));
            addAutoAction(new GateAction(new IntakeController(hardwareMap), telemetry, -0.5));//open
            addAutoAction(new AltBootKickAction(new IntakeController(hardwareMap), telemetry, 0.9));// up
            addAutoAction(new AltBootKickAction(new IntakeController(hardwareMap), telemetry, 0.6));//down
            addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0.75));//intake
            addAutoAction(new TimeWaitAction(1000));
            addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0));
            addAutoAction(new TimeWaitAction(500));
            addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0.75));//intake
            addAutoAction(new TimeWaitAction(2000));
            addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0));
            addAutoAction(new GateAction(new IntakeController(hardwareMap), telemetry, 0.4));//close
            addAutoAction(new ShooterAction(telemetry, new ShooterWheelController(hardwareMap), 0, new AprilTagDetector(camera), robot.getAlliance()));
            imuCalculator.reset();
            addAutoAction(new TurnAction(imuCalculator, (robot.getAlliance() == Alliance.RED) ? -45 : 45, new MecanumWheelsController(hardwareMap), telemetry));
            addAutoAction(new StrafeAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, (robot.getAlliance() == Alliance.RED) ? 6 : -6));
            addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 1));
            addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, 70));
            addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0));
            addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, -70));
            addAutoAction(new StrafeAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, (robot.getAlliance() == Alliance.RED) ? -6 : 6));
            addAutoAction(new ShooterAction(telemetry, new ShooterWheelController(hardwareMap), 1, new AprilTagDetector(camera), robot.getAlliance()));
            addAutoAction(new TurnAction(imuCalculator, (robot.getAlliance() == Alliance.RED) ? 45 : -45, new MecanumWheelsController(hardwareMap), telemetry));
            addAutoAction(new TimeWaitAction(500));
            addAutoAction(new GateAction(new IntakeController(hardwareMap), telemetry, -0.5));//open
            addAutoAction(new AltBootKickAction(new IntakeController(hardwareMap), telemetry, 0.9));// up
            addAutoAction(new AltBootKickAction(new IntakeController(hardwareMap), telemetry, 0.6));//down
            addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0.75));//intake
            addAutoAction(new TimeWaitAction(1000));
            addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0));
            addAutoAction(new ShooterAction(telemetry, new ShooterWheelController(hardwareMap), 0, new AprilTagDetector(camera), robot.getAlliance()));
            addAutoAction(new TurnAction(imuCalculator, (robot.getAlliance() == Alliance.RED) ? -45 : 45, new MecanumWheelsController(hardwareMap), telemetry));
            addAutoAction(new StrafeAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, (robot.getAlliance() == Alliance.RED) ? -4 : 4));
    }
}

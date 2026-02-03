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
import org.firstinspires.ftc.teamcode.actions.ShooterAction2;
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
    private static final Alliance ALLIANCE = Alliance.RED;

    @Override
    protected void createMatch() {
        IMU imu = hardwareMap.get(IMU.class, "imu");
        ImuUtility imuCalculator = new ImuUtility(imu);
        CameraName camera = hardwareMap.get(WebcamName.class, "Webcam 1");
        AprilTagDetector aprilTagDetector = new AprilTagDetector(camera);

        DecodeRobot robot = getRobot();
        robot.setStartPosition(START_POS);
        robot.setAlliance(ALLIANCE);
        // create autonomous actions
            //addAutoAction(new GateAction(new IntakeController(hardwareMap), telemetry, 0));
            addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, (robot.getAlliance() == Alliance.RED) ?  195 : 180));
            //addAutoAction(new ShooterAction2(new ShooterWheelController(hardwareMap), telemetry, 6000, aprilTagDetector, Alliance.RED));//shooter spinup
            addAutoAction(new TurnAction(imuCalculator, (robot.getAlliance() == Alliance.RED) ? -45 : 45, new MecanumWheelsController(hardwareMap), telemetry));
            addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, 45));
            addAutoAction(new TimeWaitAction(500));//shoot 3 balls
            //addAutoAction(new ShooterAction2(new ShooterWheelController(hardwareMap), telemetry, 6000, aprilTagDetector, Alliance.RED));//turn off shooter
            addAutoAction(new TurnAction(imuCalculator, (robot.getAlliance() == Alliance.RED) ? -45 : 45, new MecanumWheelsController(hardwareMap), telemetry));
            addAutoAction(new StrafeAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, (robot.getAlliance() == Alliance.RED) ? 25 : -25));
            addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 1));
            addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, 70));
            addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0));
            addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, -70));
            addAutoAction(new StrafeAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, (robot.getAlliance() == Alliance.RED) ? -25 : 25));
            //addAutoAction(new ShooterAction2(new ShooterWheelController(hardwareMap), telemetry, 6000, aprilTagDetector, Alliance.RED));//shooter spinup
            addAutoAction(new TurnAction(imuCalculator, (robot.getAlliance() == Alliance.RED) ? 45 : -45, new MecanumWheelsController(hardwareMap), telemetry));
            addAutoAction(new TimeWaitAction(500));//shoot 3 balls
            //addAutoAction(new ShooterAction2(new ShooterWheelController(hardwareMap), telemetry, 6000, aprilTagDetector, Alliance.RED));//turn off shooter
            addAutoAction(new TurnAction(imuCalculator, (robot.getAlliance() == Alliance.RED) ? -45 : 45, new MecanumWheelsController(hardwareMap), telemetry));
            addAutoAction(new StrafeAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, (robot.getAlliance() == Alliance.RED) ? 25 : -25));
    }
}
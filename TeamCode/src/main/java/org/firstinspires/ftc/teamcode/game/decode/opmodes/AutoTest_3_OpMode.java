package org.firstinspires.ftc.teamcode.game.decode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Jonah.ImuUtility;
import org.firstinspires.ftc.teamcode.Jonathan.MecanumWheelsController;
import org.firstinspires.ftc.teamcode.actions.MotorAction;
import org.firstinspires.ftc.teamcode.actions.StrafeAction;
import org.firstinspires.ftc.teamcode.actions.TurnAction;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.decode.DecodeRobot;

@Autonomous(name = "Auto test Match 3", group = "Match Opmodes")
public class AutoTest_3_OpMode extends DecodeOpmode {
    private static final Pose2D START_POS = new Pose2D(DistanceUnit.CM, 0, 0, AngleUnit.DEGREES, 0);
    private static final Alliance ALLIANCE = Alliance.BLUE;

    @Override
    protected void createMatch() {
        IMU imu = hardwareMap.get(IMU.class, "imu");
        ImuUtility imuCalculator = new ImuUtility(imu);

        DecodeRobot robot = getRobot();
        robot.setStartPosition(START_POS);
        robot.setAlliance(ALLIANCE);

        addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, 195));
        addAutoAction(new TurnAction(imuCalculator, (robot.getAlliance() == Alliance.RED) ? -45 : 45, new MecanumWheelsController(hardwareMap), telemetry));
        addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, 40));
        //shoot
        addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, -20));
        addAutoAction(new TurnAction(imuCalculator, (robot.getAlliance() == Alliance.RED) ? -45 : 45, new MecanumWheelsController(hardwareMap), telemetry));
        addAutoAction(new StrafeAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, 20));
    }
}

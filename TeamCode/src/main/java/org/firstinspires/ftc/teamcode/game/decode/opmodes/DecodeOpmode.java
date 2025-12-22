package org.firstinspires.ftc.teamcode.game.decode.opmodes;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.teamcode.ExtendableOpmode;
import org.firstinspires.ftc.teamcode.Jonah.ImuUtility;
import org.firstinspires.ftc.teamcode.Jonathan.MecanumWheelsController;
import org.firstinspires.ftc.teamcode.Jonathan.autodriving.Motor;
import org.firstinspires.ftc.teamcode.actions.AltBootKickAction;
import org.firstinspires.ftc.teamcode.actions.BootKickAction;
import org.firstinspires.ftc.teamcode.actions.IAction;
import org.firstinspires.ftc.teamcode.actions.IntakeAction;
import org.firstinspires.ftc.teamcode.actions.MotorAction;
import org.firstinspires.ftc.teamcode.actions.ShooterAction;
import org.firstinspires.ftc.teamcode.actions.StrafeAction;
import org.firstinspires.ftc.teamcode.actions.TimeWaitAction;
import org.firstinspires.ftc.teamcode.actions.TurnAction;
import org.firstinspires.ftc.teamcode.controllers.IntakeController;
import org.firstinspires.ftc.teamcode.controllers.ShooterWheelController;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Game;
import org.firstinspires.ftc.teamcode.game.decode.DecodeGame;
import org.firstinspires.ftc.teamcode.game.decode.DecodeRobot;
import org.firstinspires.ftc.teamcode.game.decode.Motif;
import org.firstinspires.ftc.teamcode.sensor.apriltag.AprilTagDetector;

import java.util.ArrayList;
import java.util.List;

public abstract class DecodeOpmode extends ExtendableOpmode {

    private DecodeRobot robot;

    private Game game;

    // TODO - should this move to Game? Or Robot as it is the auto strategy?
    private List<IAction> actions = new ArrayList<IAction>();

    protected void addAutoAction(IAction action) {
        actions.add(action);
    }

    @Override
    protected List<IAction> getAutoActions() {
        return actions;
    }

    @Override
    protected void createGame() {
        this.game = new DecodeGame();
    }

    @Override
    protected void createRobot() {
        this.robot = new DecodeRobot(this.hardwareMap, this.telemetry);
    }

    public DecodeRobot getRobot() {
        return robot;
    }

    protected void addAuto2Strategy(CameraName camera, Alliance alliance, ImuUtility imuUtility) {
        //random message: hi
        Motif motif = Motif.PPG;
        addShootAction(camera);
        addAlignSpikeMarkFromC6(alliance, motif, imuUtility);
        addIntakeSpikeMark(imuUtility);
        addAlignWithNetFromSpikeMark(motif, alliance, imuUtility);
        addShootAction(camera);
        //strafe
    }

    protected void addAlignSpikeMarkFromC6(Alliance alliance, Motif motif, ImuUtility imuCalculator) {
        addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, 10));
        int strafeDist = 0;
        switch (motif) {
            case PPG:
                strafeDist = 240;
                break;
            case PGP:
                strafeDist = 360;
                break;
            case GPP:
                strafeDist = 480;
                break;
            case Unknown:
                strafeDist = 240;
                break;
        }
        if (alliance == Alliance.RED) {
            strafeDist *= -1;
        }
        addAutoAction(new StrafeAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, strafeDist));
    }

    protected void addShootAction(CameraName camera) {
        addAutoAction(new ShooterAction(telemetry, new ShooterWheelController(hardwareMap), 1, new AprilTagDetector(camera), robot.getAlliance()));addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, -0.5));//open
        addAutoAction(new AltBootKickAction(new IntakeController(hardwareMap), telemetry, 0.9));// up
        addAutoAction(new AltBootKickAction(new IntakeController(hardwareMap), telemetry, 0.6));//down
        addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0.75));//intake
        addAutoAction(new TimeWaitAction(1000));
        addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0));
        addAutoAction(new TimeWaitAction(500));
        addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0.75));//intake
        addAutoAction(new TimeWaitAction(2000));
        addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0));
        addAutoAction(new BootKickAction(new IntakeController(hardwareMap), telemetry, 0.4));//close
        addAutoAction(new ShooterAction(telemetry, new ShooterWheelController(hardwareMap), 0, new AprilTagDetector(camera), robot.getAlliance()));
    }

    protected void addIntakeSpikeMark(ImuUtility imuCalculator) {
        addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 1));
        addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, 40));
        addAutoAction(new IntakeAction(new IntakeController(hardwareMap), telemetry, 0));
        addAutoAction(new MotorAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, -40));
    }

    protected void addAlignWithNetFromSpikeMark(Motif motif, Alliance alliance, ImuUtility imuCalculator) {
        int strafeDist = 0;
        switch (motif) {
            case PPG:
                strafeDist = 240;
                break;
            case PGP:
                strafeDist = 360;
                break;
            case GPP:
                strafeDist = 480;
                break;
            case Unknown:
                strafeDist = 240;
                break;
        }
        if (alliance == Alliance.BLUE) {
            strafeDist *= -1;
        }
        addAutoAction(new StrafeAction(imuCalculator, new MecanumWheelsController(hardwareMap), telemetry, strafeDist));
        addAutoAction(new TurnAction(imuCalculator, 45, new MecanumWheelsController(hardwareMap), telemetry));
        addAutoAction(new TurnAction(imuCalculator, (robot.getAlliance() == Alliance.RED) ? 45 : -45, new MecanumWheelsController(hardwareMap), telemetry));
    }
}

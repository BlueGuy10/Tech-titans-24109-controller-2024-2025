package org.firstinspires.ftc.teamcode.game.decode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.actions.IAction;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.component.Imu;
import org.firstinspires.ftc.teamcode.sensor.apriltag.AprilTagDetector;
import org.firstinspires.ftc.teamcode.sensor.apriltag.DecodeAprilTagDetector;

public class DecodeRobot extends Robot {

    // Hardware

    // - Wheels
    // - IMU
    // - Telemetry

    private Motif motif = Motif.Unknown;

    public Motif getMotif() {
        if (motif == Motif.Unknown) return DecodeConfigurables.defaultMotif;
        else return motif;
    }

    private final Telemetry telemetry;
    private final Imu imu;

    private final CameraName camera;

    public DecodeRobot(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.imu = new Imu(hwMap.get(IMU.class, "imu"));
        this.camera = hwMap.get(WebcamName.class, "Webcam 1");
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }

    public IMU getImu() {
        return imu.createProxy();
    }

    public IAction getMotifAction = new IAction() {
        private DecodeAprilTagDetector decodeAprilTagDetector;
        private boolean isInitialized = false;
        private boolean isFinished = false;

        @Override
        public boolean init() {
            decodeAprilTagDetector = new DecodeAprilTagDetector(new AprilTagDetector(camera));
            isInitialized = true;
            return true;
        }

        @Override
        public boolean isInitialized() {
            return isInitialized;
        }

        @Override
        public boolean iterate() {
            if (!isInitialized) throw new IllegalStateException();
            Motif detection = decodeAprilTagDetector.getMotif();
            if (detection != Motif.Unknown) {
                motif = detection;
                isFinished = true;
            }
            return !isFinished;
        }

        @Override
        public boolean isFinished() {
            return isFinished;
        }

        @Override
        public boolean stop() {
            return false;
        }

        @Override
        public boolean isStopped() {
            return false;
        }
    };
}

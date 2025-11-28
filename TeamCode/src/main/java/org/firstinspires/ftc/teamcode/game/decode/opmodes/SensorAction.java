package org.firstinspires.ftc.teamcode.game.decode.opmodes;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.actions.IAction;
import org.firstinspires.ftc.teamcode.mechanisms.SensorController;

public class SensorAction implements IAction {
    private final SensorController sensorController;

    public SensorAction(SensorController sensorController) {
        this.sensorController = sensorController;
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public boolean isInitialized() {
        return true;
    }

    @Override
    public boolean iterate() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return !sensorController.getTouchSensorState();
    }

    @Override
    public boolean stop() {
        return false;
    }

    @Override
    public boolean isStopped() {
        return false;
    }
}


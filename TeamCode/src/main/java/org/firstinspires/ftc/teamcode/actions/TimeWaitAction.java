package org.firstinspires.ftc.teamcode.actions;

import com.qualcomm.robotcore.util.ElapsedTime;

public class TimeWaitAction implements IAction {
    private ElapsedTime elapsedTime;
    private double maxTime;
    private boolean isFinished = false;

    public TimeWaitAction(double maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public boolean init() {
        elapsedTime = new ElapsedTime();
        return false;
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public boolean iterate() {
        if (elapsedTime.milliseconds() >= maxTime) {
            isFinished = true;
            return true;
        } else {
            return false;
        }
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
}

package org.firstinspires.ftc.teamcode.actions;

// TODO - implement - Enable LinearActionTest first!
public class LinearAction implements ICompositeAction {

    private final IAction[] linearActions;
    private int currentActionIndex = 0;
    private boolean isInitialized = false;
    private boolean isFinished = false;
    private boolean isStopped = false;

    public LinearAction(IAction... linearActions) {
        if (linearActions.length == 0) {
            isFinished = true;
            isStopped = true;
        }
        this.linearActions = linearActions;
    }

    @Override
    public IAction[] getActions() {
        return linearActions;
    }

    @Override
    public boolean init() {
        if (isInitialized()) throw new IllegalStateException("Can not reinitialize after initialization");
        if (linearActions.length != 0) {
            linearActions[currentActionIndex].init();
        }
        isInitialized = true;
        return true;
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public boolean iterate() {
        if (!isInitialized()) throw new IllegalStateException("Can not iterate before initialization");
        if (linearActions.length == 0) {
            isFinished = true;
            throw new IllegalStateException("Can not iterate with a empty list of actions");
        }
        if (isFinished()) throw new IllegalStateException("Can not iterate after finished");
        if (isStopped()) throw new IllegalStateException("Can not iterate after stopped");
        if (linearActions[currentActionIndex].isFinished()) {
            currentActionIndex++;
            if (linearActions.length <= currentActionIndex) {
                isFinished = true;
                return false;
            }
            linearActions[currentActionIndex].init();
        }
        linearActions[currentActionIndex].iterate();
        if (linearActions[currentActionIndex].isFinished() && currentActionIndex+1 == linearActions.length) {
            isFinished = true;
            return false;
        }
        return !isFinished;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public boolean stop() {
        if (isStopped) return true;
        isStopped = true;
        for (IAction action :
                linearActions) {
            action.stop();
        }
        return true;
    }

    @Override
    public boolean isStopped() {
        return isStopped;
    }
}

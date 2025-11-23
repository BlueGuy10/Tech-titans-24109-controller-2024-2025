package org.firstinspires.ftc.teamcode.actions;

// TODO - implement
public class ParallelAction implements ICompositeAction {
    private boolean isInitialized = false;
    private boolean isFinished = false;
    private boolean isStopped = false;

    private final IAction[] parallelActions;

    public ParallelAction(IAction... parallelActions) {
        this.parallelActions = parallelActions;
        if (parallelActions.length == 0) {
            isFinished = true;
            isStopped = true;
        };
    }

    @Override
    public IAction[] getActions() {
        return parallelActions;
    }

    @Override   // TODO implement
    public boolean init() {
        if (isInitialized()) throw new IllegalStateException("Can not reinitialize after initialization");
        for (IAction action :
                parallelActions) {
            action.init();
        }
        isInitialized = true;
        return true;
    }

    @Override   // TODO implement
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override   // TODO implement
    public boolean iterate() {
        if (!isInitialized()) throw new IllegalStateException("Can not iterate before initialization");
        if (parallelActions.length == 0) throw new IllegalStateException("Can not iterate with a empty list of actions");
        for (IAction action :
                parallelActions) {
            action.iterate();
        }
        return true;
    }

    @Override   // TODO implement
    public boolean isFinished() {
        return isFinished;
    }

    @Override   // TODO implement
    public boolean stop() {
        for (IAction action :
                parallelActions) {
            action.stop();
        }
        isStopped = true;
        return true;
    }

    @Override   // TODO implement
    public boolean isStopped() {
        return isStopped;
    }
}
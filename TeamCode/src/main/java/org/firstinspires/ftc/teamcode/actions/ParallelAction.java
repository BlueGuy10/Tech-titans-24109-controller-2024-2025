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
        }
    }

    @Override
    public IAction[] getActions() {
        return parallelActions;
    }

    @Override
    public boolean init() {
        if (isInitialized()) throw new IllegalStateException("Can not reinitialize after initialization");
        for (IAction action :
                parallelActions) {
            action.init();
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
        if (parallelActions.length == 0) throw new IllegalStateException("Can not iterate with a empty list of actions");
        if (isFinished()) throw new IllegalStateException("Can not iterate after finished");
        if (isStopped()) throw new IllegalStateException("Can not iterate after stopped");
        for (IAction action :
                parallelActions) {
            if (!action.isFinished()) action.iterate();
        }
        for (IAction action :
                parallelActions) {
            if (!action.isFinished()) return true;
        }
        isFinished = true;
        return false;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public boolean stop() {
        for (IAction action :
                parallelActions) {
            action.stop();
        }
        isStopped = true;
        return true;
    }

    @Override
    public boolean isStopped() {
        return isStopped;
    }
}
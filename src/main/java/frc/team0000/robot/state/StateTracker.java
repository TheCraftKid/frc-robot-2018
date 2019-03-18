package frc.team0000.robot.state;

public class StateTracker {

    private boolean inTurboMode = false;

    public boolean isInTurboMode() {
        return inTurboMode;
    }

    public void toggleTurboMode(boolean turboModeEnabled) {
        inTurboMode = turboModeEnabled;
    }
}

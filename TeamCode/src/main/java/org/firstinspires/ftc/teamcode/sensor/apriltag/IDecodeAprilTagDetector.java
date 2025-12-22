package org.firstinspires.ftc.teamcode.sensor.apriltag;

import org.firstinspires.ftc.teamcode.Startable;
import org.firstinspires.ftc.teamcode.Stoppable;
import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.decode.Motif;

public interface IDecodeAprilTagDetector {

    boolean isRedGoalDetected();

    boolean isBlueGoalDetected();

    boolean isMotifDetected();

    Motif getMotif();

    boolean isGoalDetected(Alliance alliance);

    double getDistance();
    /*
    TODO - define other methods that can be used by Aimer
    getX, getY, getDistance
    getPose()
     */
}

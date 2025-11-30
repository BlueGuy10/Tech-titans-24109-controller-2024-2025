package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.teamcode.motions.ElapsedTimeSupplier;
import org.firstinspires.ftc.teamcode.motions.TimeService;

/**
 * Class that allows checking whether a certain amount of time has elapsed. The
 * internal timer can either be explicitly started via {@link MaxTime#start()}
 * or implicitly started when calling {@link MaxTime#isExceeded()} for the first
 * time.
 * <p>
 * Typical usage in an {@link IAction} implementation would be
 * <pre>
 * {@code
 *      // through constructor of IAction or creation explicit creation
 *      maxTime = ...
 *
 *      public boolean init() {
 *          maxTime.start();
 *      }
 *
 *      public boolean iterate() {
 *          if (maxTime.isExceeded() {
 *              isFinished = true;
 *              return false;
 *          }
 *          ...
 *      }
 * }
 * </pre>
 */
public class MaxTime {

    private final long maxTimeMs;
    private final ElapsedTimeSupplier timeService;
    private long startTime = -1;

    /**
     * Constructor using the default {@link ElapsedTimeSupplier}
     *
     * @param maxTimeMs the maximum allowed elapsed time in ms after starting the
     *                  created instance
     */
    public MaxTime(long maxTimeMs) {
        this(maxTimeMs, new TimeService());
    }

    /**
     * Constructor using a specific {@link ElapsedTimeSupplier}
     *
     * @param maxTimeMs the maximum allowed elapsed time in ms after starting the
     *                  created instance
     * @param timeService the {@link TimeService} that should be used
     */
    public MaxTime(long maxTimeMs, ElapsedTimeSupplier timeService) {
        this.maxTimeMs = maxTimeMs;
        this.timeService = timeService;
    }

    /**
     * Explicitly start the timer
     */
    public void start() {
        startTime = timeService.getAsLong();
    }

    /**
     * Check whether the maximum allowed elapsed time has been exceeded
     *
     * @return true when the maximum allowed elapsed time has been exceeded, false
     * otherwise
     */
    public boolean isExceeded() {
        if (startTime < 0) {
            start();
            return false;
        }
        long currentTime = timeService.getAsLong();
        return (currentTime - startTime) >= maxTimeMs;
    }
}

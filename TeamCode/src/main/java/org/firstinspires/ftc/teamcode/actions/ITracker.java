package org.firstinspires.ftc.teamcode.actions;

/**
 * Spec for an action that will try to achieve and maintain a target. Examples
 * for this would be
 * <ul>
 *     <li>an action that would maintain a motor at a constant speed</li>
 *     <li>an action that could automatically aim</li>
 * </ul>
 * If the tracker does not automatically finish when achieving its target, it will
 * have to be explicitly stopped by calling {@link #stop()}.
 * <p>
 * During the lifetime of the tracker the target can be changed by calling
 * {@link #setTarget(Object)} with a new target value. E.g. when having
 * a shooter tracker spin at its default RPM, but aiming indicates being closer or
 * further away, the target RPM can be adjusted and the tracker achieve and
 * maintain this new target.
 *
 * @param <T> the type of the target. This could be a distance expressed as
 *           {@code Double} or RPMs expressed as {@code Integer}.
 */
public interface ITracker<T> extends IAction {

    /**
     * Set a new target for the tracker.
     *
     * @param target the new target that should be tracked
     */
    void setTarget(T target);

    /**
     * Get the currently tracked target
     *
     * @return the currently tracked target
     */
    T getTarget();

    /**
     * Test whether the tracker has achieved/reached the target.
     *
     * @return true when the target was achieved, otherwise false
     */
    boolean isLockedIn();

    /**
     * Get the type of the target. Should be implemented like
     *
     * <pre>
     *     {@code
     *     public class Blah implements ITracker<Foo> {
     *
     *     ...
     *
     *         public Class<Foo> getTargetType() {
     *             return Foo.class;
     *         }
     *     }
     *     }
     * </pre>
     *
     * This method allows the user of the tracker to check whether the tracker
     * is using the target type that the user expects.
     *
     * @return the type of the target
     */
    Class<T> getTargetType();
}

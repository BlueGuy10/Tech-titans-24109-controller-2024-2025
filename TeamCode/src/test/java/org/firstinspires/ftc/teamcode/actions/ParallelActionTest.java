package org.firstinspires.ftc.teamcode.actions;

import static org.firstinspires.ftc.teamcode.actions.IActionVerifier.verifyNonGetterInteraction;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ParallelActionTest {

    @Nested
    @DisplayName("Empty Parallel Action")
    public class EmptyParallelAction {

        private ParallelAction parallelAction;

        @BeforeEach
        public void setup() {
            parallelAction = new ParallelAction();
        }

        @Test
        @DisplayName("WHEN getting actions EXPECT empty array")
        void WHEN_gettingActions_EXPECT_emptyArray() {
            assertNotNull(parallelAction.getActions());
            assertThat(parallelAction.getActions().length, is(0));
        }

        @Test
        @DisplayName("WHEN init empty parallel action EXPECT initialized/true")
        void WHEN_initEmptyParallelAction_EXPECT_initializedTrue() {
            assertTrue(parallelAction.init());
        }

        @Test
        @DisplayName("WHEN init empty parallel action twice EXPECT IllegalStateException")
        void WHEN_initEmptyParallelActionTwice_EXPECT_illegalStateException() {
            parallelAction.init();
            assertThrows(IllegalStateException.class, parallelAction::init);
        }

        @Test
        @DisplayName("WHEN iterate empty parallel action before init EXPECT IllegalStateException")
        void WHEN_iterateEmptyParallelActionBeforeInit_EXPECT_illegalStateException() {
            assertThrows(IllegalStateException.class, parallelAction::iterate);
        }

        @Test
        @DisplayName("WHEN iterate empty parallel action EXPECT IllegalStateException")
        void WHEN_interateEmptyParallelAction_EXPECT_IllegalStateException() {
            parallelAction.init();
            assertThrows(IllegalStateException.class, parallelAction::iterate);
            assertTrue(parallelAction.isFinished());
        }

        @Test
        @DisplayName("WHEN empty parallel action EXPECT isFinished")
        void WHEN_emptyParallelAction_EXPECT_isFinished() {
            assertTrue(parallelAction.isFinished());
        }

        @Test
        @DisplayName("WHEN empty parallel action EXPECT isStopped")
        void WHEN_emptyParallelAction_EXPECT_isStopped() {
            assertTrue(parallelAction.isStopped());
        }

        @Test
        @DisplayName("WHEN stopping empty parallel action EXPECT stopped/true")
        void WHEN_emptyParallelAction_EXPECT_outcome() {
            assertTrue(parallelAction.stop());
            assertTrue(parallelAction.isStopped());
        }
    }

    @Nested
    @DisplayName("Non-empty Parallel Action operation")
    public class NonEmptyParallelAction {

        // Note regarding why not using Mockito verify/verifyNoMoreInvocations
        //
        // We want to verify non-getter interactions as they modify state.
        // Interactions with getters like isXXX() are not relevant as they do
        // not modify state. But as implementations of the non-getters could use
        // these getters we cannot use Mockito.verifyNoMoreInvocations() as they
        // would incorrectly flag them (or we would have to know more about the
        // implementation).

        private ParallelAction parallelAction;
        private IAction actionOne;
        private IAction actionTwo;
        private IAction actionThree;
        private List<IAction> subActions;

        @BeforeEach
        public void setup() {
            actionOne = spy(new TestAction("One", 1));
            actionTwo = spy(new TestAction("Two", 2));
            actionThree = spy(new TestAction("Three", 1));

            parallelAction = new ParallelAction(actionOne, actionTwo, actionThree);

            subActions = Arrays.asList(actionOne, actionTwo, actionThree);
        }

        @Test
        @DisplayName("WHEN action created EXPECT not initialized/finished/stopped")
        void WHEN_actionCreated_EXPECT_notFinishedNotStopped() {
            assertFalse(parallelAction.isInitialized());
            assertFalse(parallelAction.isFinished());
            assertFalse(parallelAction.isStopped());
        }

        @Test
        @DisplayName("WHEN action created EXPECT can get array of sub actions")
        void WHEN_actionCreated_EXPECT_canGetArrayOfSubActions() {
            IAction[] actions = parallelAction.getActions();
            assertThat(actions.length, is(3));
            assertThat(actions[0], is(actionOne));
            assertThat(actions[1], is(actionTwo));
            assertThat(actions[2], is(actionThree));
        }

        @Test
        @DisplayName("WHEN init action EXPECT init called on all subactions")
        void WHEN_initAction_EXPECT_initCalledOnActionOne() {
            assertTrue(parallelAction.init());

            assertFalse(parallelAction.isFinished());
            assertFalse(parallelAction.isStopped());

            // See note regarding why not using Mockito verify/verifyNoMoreInvocations
            for (IAction action : subActions) {
                verifyNonGetterInteraction(action)
                        .init(1)
                        .noOtherInteractions();
                // check state of sub action
                assertTrue(action.isInitialized(), action.toString());
                assertFalse(action.isFinished(), action.toString());
                assertFalse(action.isStopped(), action.toString());
            }
        }

        @Test
        @DisplayName("WHEN init action twice EXPECT IllegalStateException")
        void WHEN_initActionTwice_EXPECT_illegalStateException() {
            parallelAction.init();
            reset(actionOne);
            reset(actionTwo);
            reset(actionThree);

            assertThrows(IllegalStateException.class, parallelAction::init);
            for (IAction action : subActions) {
                verifyNoInteractions(action);
            }
        }

        @Test
        @DisplayName("WHEN iterate action before init EXPECT IllegalStateException")
        void WHEN_iterateActionBeforeInit_EXPECT_illegalStateException() {
            assertThrows(IllegalStateException.class, parallelAction::iterate);
            for (IAction action : subActions) {
                verifyNoInteractions(action);
            }
        }

        @Test
        @DisplayName("WHEN init/iterate action EXPECT iterate called on all subactions")
        void WHEN_initIterateAction_EXPECT_iterateCalledOnActionOne() {
            parallelAction.init();
            assertTrue(parallelAction.iterate());

            assertFalse(parallelAction.isFinished());
            // actionTwo only action that has not finished
            assertTrue(actionOne.isFinished());
            assertFalse(actionTwo.isFinished());
            assertTrue(actionThree.isFinished());
            // See note regarding why not using Mockito verify/verifyNoMoreInvocations
            for (IAction action : subActions) {
                verifyNonGetterInteraction(action)
                        .init(1)
                        .iterate(1)
                        .noOtherInteractions();
            }
        }

        @Test
        @DisplayName("WHEN init/iterate(2) action EXPECT action should be finished")
        void WHEN_initIterate2Action_EXPECT_iterateToFinishOnActionOne() {
            parallelAction.init();
            parallelAction.iterate();
            assertFalse(parallelAction.iterate());

            assertTrue(parallelAction.isFinished());
            // all subactions finished
            for (IAction action : subActions) {
                assertTrue(action.isFinished());
            }

            // See note regarding why not using Mockito verify/verifyNoMoreInvocations
            verifyNonGetterInteraction(actionOne)
                    .init(1)
                    .iterate(1)
                    .noOtherInteractions();
            verifyNonGetterInteraction(actionTwo)
                    .init(1)
                    .iterate(2)
                    .noOtherInteractions();
            verifyNonGetterInteraction(actionThree)
                    .init(1)
                    .iterate(1)
                    .noOtherInteractions();
        }

        @Test
        @DisplayName("WHEN iterate while finished EXPECT IllegalStateException")
        void WHEN_iterateWhileFinished_EXPECT_illegalStateException() {
            parallelAction.init();
            for (int i = 0; i < 2; i++) {
                parallelAction.iterate();
            }

            assertThrows(IllegalStateException.class, parallelAction::iterate);
        }

        @Test
        @DisplayName("WHEN stopping action EXPECT stopping all non-stopped action")
        void WHEN_stoppingAction_EXPECT_stoppingAllNonStoppedAction() {
            parallelAction.init();
            parallelAction.iterate();

            assertTrue(parallelAction.stop());
            assertTrue(parallelAction.isStopped());

            for (IAction action : subActions) {
                assertTrue(action.isStopped());
            }
        }

        @Test
        @DisplayName("WHEN iterating on stopped action EXPECT IllegalStateException")
        void WHEN_iteratingOnStoppedAction_EXPECT_illegalStateException() {
            parallelAction.init();
            parallelAction.iterate();
            parallelAction.stop();

            assertThrows(IllegalStateException.class, parallelAction::iterate);
        }
    }
}

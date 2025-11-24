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

public class LinearActionTest {

    @Nested
    @DisplayName("Empty Linear Action")
    public class EmptyLinearAction {

        private LinearAction linearAction;

        @BeforeEach
        public void setup() {
            linearAction = new LinearAction();
        }

        @Test
        @DisplayName("WHEN getting actions EXPECT empty array")
        void WHEN_gettingActions_EXPECT_emptyArray() {
            assertNotNull(linearAction.getActions());
            assertThat(linearAction.getActions().length, is(0));
        }

        @Test
        @DisplayName("WHEN init empty linear action EXPECT initialized/true")
        void WHEN_initEmptyLinearAction_EXPECT_initializedTrue() {
            assertTrue(linearAction.init());
        }

        @Test
        @DisplayName("WHEN init empty linear action twice EXPECT IllegalStateException")
        void WHEN_initEmptyLinearActionTwice_EXPECT_illegalStateException() {
            linearAction.init();
            assertThrows(IllegalStateException.class, linearAction::init);
        }

        @Test
        @DisplayName("WHEN iterate empty linear action before init EXPECT IllegalStateException")
        void WHEN_iterateEmptyLinearActionBeforeInit_EXPECT_illegalStateException() {
            assertThrows(IllegalStateException.class, linearAction::iterate);
        }

        @Test
        @DisplayName("WHEN iterate empty linear action EXPECT IllegalStateException")
        void WHEN_interateEmptyLinearAction_EXPECT_IllegalStateException() {
            linearAction.init();
            assertThrows(IllegalStateException.class, linearAction::iterate);
            assertTrue(linearAction.isFinished());
        }

        @Test
        @DisplayName("WHEN empty linear action EXPECT isFinished")
        void WHEN_emptyLinearAction_EXPECT_isFinished() {
            assertTrue(linearAction.isFinished());
        }

        @Test
        @DisplayName("WHEN empty linear action EXPECT isStopped")
        void WHEN_emptyLinearAction_EXPECT_isStopped() {
            assertTrue(linearAction.isStopped());
        }

        @Test
        @DisplayName("WHEN stopping empty linear action EXPECT stopped/true")
        void WHEN_emptyLinearAction_EXPECT_outcome() {
            assertTrue(linearAction.stop());
            assertTrue(linearAction.isStopped());
        }
    }

    @Nested
    @DisplayName("Non-empty Linear Action operation")
    public class NonEmptyLinearAction {

        // Note regarding why not using Mockito verify/verifyNoMoreInvocations
        //
        // We want to verify non-getter interactions as they modify state.
        // Interactions with getters like isXXX() are not relevant as they do
        // not modify state. But as implementations of the non-getters could use
        // these getters we cannot use Mockito.verifyNoMoreInvocations() as they
        // would incorrectly flag them (or we would have to know more about the
        // implementation).

        private LinearAction linearAction;
        private IAction actionOne;
        private IAction actionTwo;

        @BeforeEach
        public void setup() {
            actionOne = spy(new TestAction("One", 2));
            actionTwo = spy(new TestAction("Two", 2));
            linearAction = new LinearAction(actionOne, actionTwo);
        }

        @Test
        @DisplayName("WHEN action created EXPECT not initialized/finished/stopped")
        void WHEN_actionCreated_EXPECT_notFinishedNotStopped() {
            assertFalse(linearAction.isInitialized());
            assertFalse(linearAction.isFinished());
            assertFalse(linearAction.isStopped());
        }

        @Test
        @DisplayName("WHEN action created EXPECT can get array of sub actions")
        void WHEN_actionCreated_EXPECT_canGetArrayOfSubActions() {
            IAction[] actions = linearAction.getActions();
            assertThat(actions.length, is(2));
            assertThat(actions[0], is(actionOne));
            assertThat(actions[1], is(actionTwo));
        }

        @Test
        @DisplayName("WHEN init action EXPECT init called on actionOne")
        void WHEN_initAction_EXPECT_initCalledOnActionOne() {
            assertTrue(linearAction.init());

            assertFalse(linearAction.isFinished());
            assertFalse(linearAction.isStopped());

            // See note regarding why not using Mockito verify/verifyNoMoreInvocations
            verifyNonGetterInteraction(actionOne)
                    .init(1)
                    .noOtherInteractions();
            verifyNoInteractions(actionTwo);
            // check state of sub actions
            assertTrue(actionOne.isInitialized());
            assertFalse(actionOne.isFinished());
            assertFalse(actionOne.isStopped());
            assertFalse(actionTwo.isInitialized());
       }

        @Test
        @DisplayName("WHEN init action twice EXPECT IllegalStateException")
        void WHEN_initActionTwice_EXPECT_illegalStateException() {
            linearAction.init();
            reset(actionOne);
            reset(actionTwo);

            assertThrows(IllegalStateException.class, linearAction::init);
            verifyNoInteractions(actionOne);
            verifyNoInteractions(actionTwo);
        }

        @Test
        @DisplayName("WHEN iterate action before init EXPECT IllegalStateException")
        void WHEN_iterateActionBeforeInit_EXPECT_illegalStateException() {
            assertThrows(IllegalStateException.class, linearAction::iterate);
            verifyNoInteractions(actionOne);
            verifyNoInteractions(actionTwo);
        }

        @Test
        @DisplayName("WHEN init/iterate action EXPECT iterate called on actionOne")
        void WHEN_initIterateAction_EXPECT_iterateCalledOnActionOne() {
            linearAction.init();
            assertTrue(linearAction.iterate());

            assertFalse(linearAction.isFinished());
            assertFalse(actionOne.isFinished());
            // See note regarding why not using Mockito verify/verifyNoMoreInvocations
            verifyNonGetterInteraction(actionOne)
                    .init(1)
                    .iterate(1)
                    .noOtherInteractions();
            verifyNoInteractions(actionTwo);
        }

        @Test
        @DisplayName("WHEN init/iterate(2) action EXPECT iterate to finish on actionOne")
        void WHEN_initIterate2Action_EXPECT_iterateToFinishOnActionOne() {
            linearAction.init();
            linearAction.iterate();
            assertTrue(linearAction.iterate());     // linear action still show it is not finished

            assertFalse(linearAction.isFinished());
            assertTrue(actionOne.isFinished());
            // See note regarding why not using Mockito verify/verifyNoMoreInvocations
            verifyNonGetterInteraction(actionOne)
                    .init(1)
                    .iterate(2)
                    .noOtherInteractions();
            verifyNoInteractions(actionTwo);
        }

        @Test
        @DisplayName("WHEN init/iterate(3) action EXPECT finish on actionOne, init & start iter on actionTwo")
        void WHEN_initIterate3Action_EXPECT_finishOnActionOneStartIterOnActionTwo() {
            linearAction.init();
            linearAction.iterate();
            linearAction.iterate();
            assertTrue(linearAction.iterate());

            assertFalse(linearAction.isFinished());
            assertTrue(actionOne.isFinished());
            assertFalse(actionTwo.isFinished());
            // See note regarding why not using Mockito verify/verifyNoMoreInvocations
            verifyNonGetterInteraction(actionOne)
                    .init(1)
                    .iterate(2)
                    .noOtherInteractions();
            verifyNonGetterInteraction(actionTwo)
                    .init(1)
                    .iterate(1)
                    .noOtherInteractions();
        }

        @Test
        @DisplayName("WHEN init/iterate(4) action EXPECT finish on action, actionOne & actionTwo")
        void WHEN_initIterate4Action_EXPECT_finishOnActionActionOneActionTwo() {
            linearAction.init();
            linearAction.iterate();
            linearAction.iterate();
            linearAction.iterate();
            assertFalse(linearAction.iterate());

            assertTrue(linearAction.isFinished());
            assertTrue(actionOne.isFinished());
            assertTrue(actionTwo.isFinished());
            // See note regarding why not using Mockito verify/verifyNoMoreInvocations
            verifyNonGetterInteraction(actionOne)
                    .init(1)
                    .iterate(2)
                    .noOtherInteractions();
            verifyNonGetterInteraction(actionTwo)
                    .init(1)
                    .iterate(2)
                    .noOtherInteractions();
        }

        @Test
        @DisplayName("WHEN iterate while finished EXPECT IllegalStateException")
        void WHEN_iterateWhileFinished_EXPECT_illegalStateException() {
            linearAction.init();
            for (int i = 0; i < 4; i++) {
                linearAction.iterate();
            }

            assertThrows(IllegalStateException.class, linearAction::iterate);
        }

        @Test
        @DisplayName("WHEN stopping action EXPECT stopping all non-stopped action")
        void WHEN_stoppingAction_EXPECT_stoppingAllNonStoppedAction() {
            linearAction.init();
            linearAction.iterate();

            assertTrue(linearAction.stop());
            assertTrue(linearAction.isStopped());

            assertTrue(actionOne.isStopped());
            assertTrue(actionTwo.isStopped());
        }

        @Test
        @DisplayName("WHEN iterating on stopped action EXPECT IllegalStateException")
        void WHEN_iteratingOnStoppedAction_EXPECT_illegalStateException() {
            linearAction.init();
            linearAction.iterate();
            linearAction.stop();

            assertThrows(IllegalStateException.class, linearAction::iterate);
        }
    }
}

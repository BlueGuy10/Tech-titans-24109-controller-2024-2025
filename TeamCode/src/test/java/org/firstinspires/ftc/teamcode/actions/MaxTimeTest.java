package org.firstinspires.ftc.teamcode.actions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.firstinspires.ftc.teamcode.motions.MockedTimeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MaxTimeTest {

    @Test
    @DisplayName("WHEN explicitly started EXPECT exceed after 30 ms")
    void WHEN_explicitlyStarted_EXPECT_exceedAfter30Ms() {
        MaxTime maxTime = new MaxTime(30L,
                new MockedTimeService(10L, 20L, 30L, 40L, 50L));

        maxTime.start();                    // at 10L
        assertFalse(maxTime.isExceeded());  // at 20L - 10ms elapsed
        assertFalse(maxTime.isExceeded());  // at 30L - 20ms elapsed
        assertTrue(maxTime.isExceeded());   // at 40L - 30ms elapsed
        assertTrue(maxTime.isExceeded());   // at 50L - 30ms elapsed
    }

    @Test
    @DisplayName("WHEN implicitly started EXPECT exceed after 30ms")
    void WHEN_implicitlyStarted_EXPECT_exceedAfter30Ms() {
        MaxTime maxTime = new MaxTime(30L,
                new MockedTimeService(10L, 20L, 30L, 40L, 50L));

        assertFalse(maxTime.isExceeded());  // at 10L
        assertFalse(maxTime.isExceeded());  // at 20L - 10ms elapsed
        assertFalse(maxTime.isExceeded());  // at 30L - 20ms elapsed
        assertTrue(maxTime.isExceeded());   // at 40L - 30ms elapsed
        assertTrue(maxTime.isExceeded());   // at 50L - 30ms elapsed
    }
}

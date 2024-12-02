package tat.mukhutdinov.lesson14racetracker

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import tat.mukhutdinov.lesson14racetracker.ui.RaceParticipant
import tat.mukhutdinov.lesson14racetracker.ui.progressFactor


class RaceParticipantTest {

    /**
     * Case: Happy Path
     * Scenario:
     * - Given progress is zero,
     * - When reset is called,
     * - Then progress remains zero.
     */
    @Test
    fun givenProgressIsZero_whenResetCalled_thenProgressIsZero() {
        val participant = RaceParticipant(
            name = "Player 1",
            initialProgress = 0
        )

        participant.reset()

        assertEquals(0, participant.currentProgress)
    }

    /**
     * Case: Happy Path
     * Scenario:
     * - Given progress is non-zero,
     * - When reset is called,
     * - Then progress resets to zero.
     */
    @Test
    fun givenProgressIsNonZero_whenResetCalled_thenProgressIsZero() {
        val participant =
            RaceParticipant(
                name = "Player 1",
                initialProgress = 50,
                maxProgress = 100
            )

        participant.reset()

        assertEquals(0, participant.currentProgress)
    }

    /**
     * Case: Happy Path
     * Scenario:
     * - Given progress is half of maxProgress,
     * - When progressFactor is computed,
     * - Then it should return 0.5.
     */
    @Test
    fun givenProgressIsHalfOfMax_whenProgressFactorComputed_thenReturnsHalf() {
        val participant =
            RaceParticipant(
                name = "Player 1",
                maxProgress = 100,
                initialProgress = 50
            )

        assertEquals(0.5f, participant.progressFactor, 0.01f)
    }

    /**
     * Case: Happy Path
     * Scenario:
     * - Given progress is zero,
     * - When run is executed,
     * - Then progress reaches maxProgress.
     */
    @Test
    fun givenProgressIsZero_whenRunExecuted_thenReachesMaxProgress() = runBlocking {
        val participant = RaceParticipant(
            name = "Player 1",
            maxProgress = 10,
            progressIncrement = 2,
            progressDelayMillis = 10
        )

        participant.run()

        assertEquals(10, participant.currentProgress)
    }

    /**
     * Case: Boundary Case
     * Scenario:
     * - Given progress is zero,
     * - When progressFactor is computed,
     * - Then it should return 0.0.
     */
    @Test
    fun givenProgressIsZero_whenProgressFactorComputed_thenReturnsZero() {
        val participant = RaceParticipant(
            name = "Player 1",
            maxProgress = 100,
            initialProgress = 0
        )

        assertEquals(0.0f, participant.progressFactor, 0.01f)
    }

    /**
     * Case: Boundary Case
     * Scenario:
     * - Given progress equals maxProgress,
     * - When progressFactor is computed,
     * - Then it should return 1.0.
     */
    @Test
    fun givenProgressEqualsMax_whenProgressFactorComputed_thenReturnsOne() {
        val participant =
            RaceParticipant(
                name = "Player 1",
                maxProgress = 100,
                initialProgress = 100
            )

        assertEquals(1.0f, participant.progressFactor, 0.01f)
    }

    /**
     * Case: Error Case
     * Scenario:
     * - Given maxProgress is zero,
     * - When constructing a participant,
     * - Then an IllegalArgumentException is thrown.
     */
    @Test
    fun givenMaxProgressZero_whenConstructing_thenThrowsException() {
        var exceptionThrown = false
        try {
            RaceParticipant(name = "Player 1", maxProgress = 0)
        } catch (e: IllegalArgumentException) {
            exceptionThrown = true
        }
        assertTrue(exceptionThrown)
    }

    /**
     * Case: Error Case
     * Scenario:
     * - Given progressIncrement is zero,
     * - When constructing a participant,
     * - Then an IllegalArgumentException is thrown.
     */
    @Test
    fun givenProgressIncrementZero_whenConstructing_thenThrowsException() {
        var exceptionThrown = false
        try {
            RaceParticipant(name = "Player 1", progressIncrement = 0)
        } catch (e: IllegalArgumentException) {
            exceptionThrown = true
        }
        assertTrue(exceptionThrown)
    }
}

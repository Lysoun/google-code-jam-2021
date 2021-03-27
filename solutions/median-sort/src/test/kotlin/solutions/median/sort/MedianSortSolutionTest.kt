package solutions.cheating.detection

import findCheater
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.io.File

internal class MedianSortSolutionTest {
    @Test
    fun `given input sample, should find that cheater is 59`() {
        // Given
        val playersAnswers = File("src/test/resources/input_sample.txt").readLines().drop(2)

        // When
        val cheater = findCheater(playersAnswers)

        // Then
        expectThat(cheater).isEqualTo(59)
    }
}